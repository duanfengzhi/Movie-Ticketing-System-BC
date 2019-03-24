package com.hgd.ebp.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.OrderMng;
import com.hgd.ebp.service.OrderMngService;
import com.hgd.ebp.state.OrderMngQueryState;
import com.hgd.ebp.state.OrderMngQueryState2;

@Controller
@RequestMapping(value="/admin")
public class OrderMngController {
	@Resource
	private OrderMngService orderMngService;
	@RequestMapping(value="/ListOrderMngCtrl", method=RequestMethod.GET)
	public String listAll(Model model, HttpSession session, String page) {
		OrderMngQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("OrderMngQueryState");
			state = new OrderMngQueryState();
		} else {
			state = (OrderMngQueryState)
					session.getAttribute("OrderMngQueryState");
			if (state == null) {
				state = new OrderMngQueryState();
			}
		}
		
		List<OrderMng> list = null;
		try {
			int lastPage = orderMngService.getLastPage(state);
			state.setLastPage(lastPage);
			list= orderMngService.getOrdersByPage(state,page);
			session.setAttribute("OrderMngQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listOrderMng", list); 
	        return "admin/ListOrderMng";
	}
	
	@RequestMapping(value="/ListOrderMngCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session,String  begin_time,String end_time) {
		Map<String, String> errMap = new HashMap<String, String>();
		boolean error1=false;
		boolean error2=false;
		
		if((begin_time==null && end_time==null)||(begin_time=="" && end_time=="")){
			errMap.put("date", "请输入日期");
			model.addAttribute("errMap", errMap);
	        return "admin/ListOrderMng";
		}
		
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(begin_time);
		} catch (ParseException e) {
			begin_time = "2001-01-01";
			error1=true;
		}

		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(end_time);
		} catch (ParseException e) {
			end_time = "2030-12-31";
			error2=true;
		}
		
		if(error1||error2){
			errMap.put("date", "日期格式不正确，请检查后重新输入");
			model.addAttribute("errMap", errMap);
	        return "admin/ListOrderMng";
		}
		
		String begin=begin_time+" 00:00:00";
		String end=end_time+" 00:00:00";
		
		Timestamp ts_begin=Timestamp.valueOf(begin);
		Timestamp ts_end=Timestamp.valueOf(end);
		
        session.removeAttribute("OrderMngQueryState");
       
        OrderMngQueryState state = new OrderMngQueryState(0,ts_begin, ts_end);
        List<OrderMng> list = null;
		
		try {
			int lastPage = orderMngService.getLastPage(state);
			state.setLastPage(lastPage);
			
			list= orderMngService.getOrders(state);
			session.setAttribute("OrderMngQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("listOrderMng", list); 
        return "admin/ListOrderMng";
	}
	@RequestMapping(value="/ListOrderMngCtrl2", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session,Integer oid,String name,String idCard) {
       session.removeAttribute("OrdersQueryState2");
       
       int o=0;
       if(oid!=null){
    	   o=oid;
       }
       OrderMngQueryState2 state =  new OrderMngQueryState2(0,o,name,idCard,name);
       
       List<OrderMng> list = null;
		try {
			int lastPage = orderMngService.getLastPage(state);
			state.setLastPage(lastPage);
			
			list= orderMngService.getOrders(state);
			session.setAttribute("OrdersQueryState2", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("listOrderMng", list); 
        return "admin/ListOrderMng";
	}
	
}
	
	