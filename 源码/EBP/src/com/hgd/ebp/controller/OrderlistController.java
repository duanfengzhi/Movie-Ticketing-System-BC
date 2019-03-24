package com.hgd.ebp.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Orderlist;
import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.exception.OrderlistException;
import com.hgd.ebp.service.OrderlistService;

@Controller
@RequestMapping
public class OrderlistController {
	@Resource
    private OrderlistService orderlistService;

	@RequestMapping(value="/user/ListOrderlistsCtrl", method=RequestMethod.GET)
	public String listAll(Model model, HttpSession session) {
		List<Orderlist> list = null;
		try {
			list = orderlistService.getOrderlists();
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<Orderlist>();
		}
		model.addAttribute("orderlists", list); 
        return "user/getOrderlist";
	}

	@RequestMapping(value="/user/AddOrderlistCtrl")
    public String add(Model model,HttpSession session, int oid,Timestamp commitTime) {
		@SuppressWarnings("unchecked")
		List<Integer> ownlist = (List<Integer>)session.getAttribute("ownticket");
		@SuppressWarnings("unchecked")
		List<Integer> numList = (List<Integer>)session.getAttribute("numList");
		@SuppressWarnings("unchecked")
		List<Ticket> list=(List<Ticket>)session.getAttribute("tickets");
		
		
		if(ownlist!=null){
			for(int i=0;i<ownlist.size();i++){
				int p=0;
				for(;p<list.size();p++){
					if(ownlist.get(i)==list.get(p).getTid())
						break;
				}
				int lid=ownlist.get(i);
				String descr=list.get(p).getDescr();
				double price=list.get(p).getPrice();
				int quantity=numList.get(i);
				double amount=quantity*price;
				try {
					
					@SuppressWarnings("unused")
					Orderlist orderlist=orderlistService.addOderlist(lid, descr, price, quantity, amount, oid);
				} catch (OrderlistException e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("oid", oid);
		model.addAttribute("commitTime", commitTime);
		return "redirect:/user/ClearChartCtrl";
	}	 
}

