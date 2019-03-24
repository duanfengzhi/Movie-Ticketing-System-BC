package com.hgd.ebp.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.domain.User;
import com.hgd.ebp.filter.UserLoginFilter;

@Controller
@RequestMapping(value="/user")
public class ShoppingCartController {
	@RequestMapping(value="/AddShoppingCartCtrl", method=RequestMethod.POST)
	public String add(Model model, HttpSession session,int tid,int num) {
		
		if(Integer.valueOf(num)==null||num==0){
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("numerr", "购票数不能为空");
			model.addAttribute("errMap", errMap);
        	return "user/BookingTicket";
		}
		
		Map<String, String> errMap = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Integer> ownlist = (List<Integer>)session.getAttribute("ownticket");
		@SuppressWarnings("unchecked")
		List<Integer> numList = (List<Integer>)session.getAttribute("numList");
		@SuppressWarnings("unchecked")
		List<Ticket> list=(List<Ticket>)session.getAttribute("tickets");

		if(ownlist==null){
			ownlist=new ArrayList<>();
			numList=new ArrayList<>();
		}
		
		String name = null;
		for(int i=0;i<list.size();i++)
		{
			if(tid==list.get(i).getTid())
				name = list.get(i).getDescr();
		}
		int p=0;
		for(;p<ownlist.size();p++){
			if(ownlist.get(p)==tid)
				break;
		}
		
		int t=0;
		for(;t<list.size();t++){
			if(list.get(t).getTid()==tid)
				break;
		}
		
		if(p<ownlist.size() && numList.get(p)+num<=list.get(t).getBalance()){
			numList.set(p, numList.get(p)+num);
		}
		else if(num<=list.get(t).getBalance()){
			ownlist.add(tid);
			numList.add(num);
		}
		else{
			////////////////////////;;;;;;;;;;;;;;;票不够////////////////////////
			errMap.put("numErr", "您所选的电影：" + name + "\n票数不足，请重新选择");
			model.addAttribute("errMap", errMap);
			return "user/BookingTicket";
		}
		
		session.setAttribute("ownticket", ownlist);
		session.setAttribute("numList", numList);
		System.out.println(ownlist);
		System.out.println(numList);
		return "user/BookingTicket";
	}
	
	@RequestMapping(value="/DeleteTicketCtrl", method=RequestMethod.POST)
    public String delete(Model model, HttpSession session, int point) {
		@SuppressWarnings("unchecked")
		List<Integer> ownlist = (List<Integer>)session.getAttribute("ownticket");
		@SuppressWarnings("unchecked")
		List<Integer> numList = (List<Integer>)session.getAttribute("numList");
			ownlist.remove(point);
			numList.remove(point);
		
		session.setAttribute("ownticket", ownlist);
		session.setAttribute("numList", numList);
		return "user/ShowShoppingCart";
	}
	
	@RequestMapping(value="/ClearChartCtrl")
    public String clear(Model model, HttpSession session,int oid,Timestamp commitTime) {
		@SuppressWarnings("unchecked")
		List<Integer> ownlist = (List<Integer>)session.getAttribute("ownticket");
		@SuppressWarnings("unchecked")
		List<Integer> numList = (List<Integer>)session.getAttribute("numList");
		@SuppressWarnings("unchecked")
		List<Ticket> list=(List<Ticket>)session.getAttribute("tickets");
		int amount=0;
		int allnum=0;
		if(numList!=null){
			for(int i=0;i<numList.size();i++){
				int p=0;
				allnum+=numList.get(i);
				for(;p<list.size();p++){
					if(list.get(p).getTid()==ownlist.get(i))
						break;
				}
				amount+=numList.get(i)*list.get(p).getPrice();
			}
		}
		
		session.removeAttribute("ownticket");
		session.removeAttribute("numList");
		
		model.addAttribute("oid", oid);
		model.addAttribute("commitTime", commitTime);
		model.addAttribute("ownticket", ownlist);
		model.addAttribute("numList", numList);
		model.addAttribute("amount", amount);
		model.addAttribute("allnum", allnum);
		return "user/ShoppingSuc";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/CheckOutCtrl", method=RequestMethod.GET)
	public String checkout(Model model, HttpSession session, HttpServletRequest request) {
		
		Map<String, String> errMap = new HashMap<String, String>();
		User u = (User)session.getAttribute(UserLoginFilter.ATTR_USER);
		double userBalance =u.getBalance();
		List<Integer> ownlist = (List<Integer>)session.getAttribute("ownticket");
		List<Integer> numList = (List<Integer>)session.getAttribute("numList");
		List<Ticket> list=(List<Ticket>)session.getAttribute("tickets");
		
		double amount=0;
		if(numList!=null){
			for(int i=0;i<numList.size();i++){
				int p=0;
				for(;p<list.size();p++){
					if(list.get(p).getTid()==ownlist.get(i))break;
				}
				amount+=numList.get(i)*list.get(p).getPrice();
			}
		}
		if(amount>userBalance){
			///////////////////余额不够/////////////////////
			errMap.put("moneyErr", "您的账户余额已不足，请及时充值");
			model.addAttribute("errMap", errMap);
			return  "user/ShowShoppingCart";
		}
		else
			return "redirect:/user/reduceTicketCtrl";
	}
}
