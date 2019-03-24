package com.hgd.ebp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Order;
import com.hgd.ebp.exception.OrderException;
import com.hgd.ebp.service.OrderService;
import com.hgd.ebp.state.OrderQueryState;

@Controller
@RequestMapping
public class OrderController {
	@Resource
    private OrderService orderService;
	@RequestMapping(value="/user/ListOrdersCtrl", method=RequestMethod.GET)
	public String listAll(Model model, HttpSession session, String page) {
		OrderQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("OrderQueryState");
			state = new OrderQueryState();
		} else {
			state = (OrderQueryState)
					session.getAttribute("OrderQueryState");
			if (state == null) {
				state = new OrderQueryState();
			}
		  }
		List<Order> list =null;
		try {
			int lastPage = orderService.getLastPage(state);
			state.setLastPage(lastPage);
			list =orderService.getOrdersByPage(state, page);
			session.setAttribute("OrderQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		  }
		session.setAttribute("listOrders", list); 
		 return "user/getOrder";
	}
	@RequestMapping(value="/ListOrdersCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session) {
		session.removeAttribute("OrderQueryState");
		List<Order> list=null;
		OrderQueryState state= new OrderQueryState(0);
		try {
				int lastPage = orderService.getLastPage(state);
				state.setLastPage(lastPage);
				list = orderService.getOrders(state);
				session.setAttribute("OrderQueryState", state);
				model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		  }
		session.setAttribute("listOrders", list); 
		return "user/getOrder";
	}
	
	@RequestMapping(value="/user/AddOrderCtrl")
	public String Add(Model model, HttpSession session,double amount)
			throws ServletException, IOException {
			int uid = (int)session.getAttribute("uid");
			Timestamp commitTime=new Timestamp(System.currentTimeMillis());
			Order order=null;
			try {
				order = orderService.addOrder(-1, commitTime, amount, uid);
			} catch (OrderException e) {
				e.printStackTrace();
			  }
			model.addAttribute("oid", order.getOid());
			model.addAttribute("commitTime", order.getCommitTime());
        return "redirect:/user/AddOrderlistCtrl";
	}

}
