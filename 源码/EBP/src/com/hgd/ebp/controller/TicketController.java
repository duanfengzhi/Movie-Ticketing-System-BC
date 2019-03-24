package com.hgd.ebp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.service.TicketService;
import com.hgd.ebp.state.TicketQueryState;

@Controller
@RequestMapping
@SuppressWarnings("unchecked")
public class TicketController {
	@Resource
    private TicketService ticketService;
	
	@RequestMapping(value="/user/ListTicketsCtrl", method=RequestMethod.GET)
	public String listAll(Model model, HttpSession session, String page) {
		
		List<Ticket> tickets = (List<Ticket>)session.getAttribute("tickets");
		if(tickets==null){
			try {
				tickets=ticketService.getAllTickets();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.setAttribute("tickets", tickets); 
		
		TicketQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("TicketQueryState");
			state = new TicketQueryState();
		} else {
			state = (TicketQueryState)
					session.getAttribute("TicketQueryState");
			if (state == null) {
				state = new TicketQueryState();
			}
		}
		
		List<Ticket> list =null;
		try {
			int lastPage = ticketService.getLastPage(state);
			state.setLastPage(lastPage);
				
			list =ticketService.getTicketsByPage(state, page);
			session.setAttribute("TicketQueryState", state);
			model.addAttribute("lastPage", lastPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("listTickets", list); 
		return "user/BookingTicket";
	}
	
	@RequestMapping(value="/user/ListTicketsCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session, String date, String price) {		
		session.removeAttribute("TicketQueryState");
		
		List<Ticket> list=null;
		TicketQueryState state= new TicketQueryState(0,date,price);
		
		try {
				int lastPage = ticketService.getLastPage(state);
				state.setLastPage(lastPage);
				list = ticketService.getTickets(state);
				session.setAttribute("TicketQueryState", state);
				model.addAttribute("lastPage", lastPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("listTickets", list); 
		return "user/BookingTicket";
	}
	
	
	@RequestMapping(value="/user/reduceTicketCtrl")
	public String reduceTicket(Model model, HttpSession session) {
		List<Integer> ownlist = (List<Integer>)session.getAttribute("ownticket");
		List<Integer> numList = (List<Integer>)session.getAttribute("numList");
		List<Ticket> list=(List<Ticket>)session.getAttribute("tickets");
		
		double amount=0;
		if(numList!=null){
			for(int i=0;i<numList.size();i++){
				int p=0;
				for(;p<list.size();p++){
					if(list.get(p).getTid()==ownlist.get(i)){
						ticketService.reduceTicket(ownlist.get(i), numList.get(i));
						break;
					}
				}
				amount+=numList.get(i)*list.get(p).getPrice();
			}
		}
		model.addAttribute("amount", amount);
		
		return "redirect:/user/reduceBalanceCtrl";
	}
	
	

	@RequestMapping(value="/admin/ListTicketsCtrl", method=RequestMethod.GET)
	public String alistAll(Model model, HttpSession session, String page) {
	
		TicketQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("TicketQueryState");
			state = new TicketQueryState();
		} else {
			state = (TicketQueryState)
					session.getAttribute("TicketQueryState");
			if (state == null) {
				state = new TicketQueryState();
			}
		}
		
		List<Ticket> list =null;
		try {

			int lastPage = ticketService.getLastPage(state);
			state.setLastPage(lastPage);
				
			list =ticketService.getTicketsByPage(state, page);
			session.setAttribute("TicketQueryState", state);
			model.addAttribute("lastPage", lastPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listTickets", list); 
		return "admin/ListTicket";
	}
	
	@RequestMapping(value="/admin/ListTicketsCtrl", method=RequestMethod.POST)
	public String alistBy(Model model, HttpSession session,
							String  begin_time, String end_time) {
		Map<String, String> errMap = new HashMap<String, String>();
		boolean error1=false;
		boolean error2=false;
		
		if((begin_time==null && end_time==null)||(begin_time=="" && end_time=="")){
			errMap.put("date", "请输入日期");
			model.addAttribute("errMap", errMap);
	        return "admin/ListTicket";
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
	        return "admin/ListTicket";
		}

		String begin=begin_time+" 00:00:00";
		String end=end_time+" 00:00:00";
		
		Timestamp ts_begin=Timestamp.valueOf(begin);
		Timestamp ts_end=Timestamp.valueOf(end);
		
		session.removeAttribute("TicketQueryState");
		
		List<Ticket> list=null;
		TicketQueryState state= new TicketQueryState(0,ts_begin, ts_end);
		
		try {
				int lastPage = ticketService.getLastPage(state);
				state.setLastPage(lastPage);
				list = ticketService.getTickets(state);
				
				for(int i=0;i<list.size();i++){
					System.out.println("-----------------"+list.get(i).getDescr());
				}
				
				session.setAttribute("TicketQueryState", state);
				model.addAttribute("lastPage", lastPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listTickets", list);
		return "admin/ListTicket";
	}
	
	@RequestMapping(value="/admin/AddTicketsCtrl", method=RequestMethod.POST)
	public String addTickects(Model model, HttpSession session,String descr,
								String startTime,String amount,String price) {
		
		Map<String, String> errMap = new HashMap<String, String>();
		if (descr == null || "".equals(descr)) {
			errMap.put("descr","请输入电影名称");
		}		
		if (startTime == null || "".equals(startTime)) {
			errMap.put("startTime","请输入电影上映时间");
		}		
		if (amount == null || "".equals(amount)) {
			errMap.put("amount","请输入电影总票数");
		}		
		if (price == null || "".equals(price)) {
			errMap.put("price","请输入每张票的价格");
		}
		
		if (errMap.size() != 0) {
			model.addAttribute("errMap", errMap);
	        return "/admin/AddTicket";
		}
		
		String begin=startTime+" 00:00:00";
		Timestamp start=Timestamp.valueOf(begin);
		
		int am=Integer.parseInt(amount);
		double pr=Double.parseDouble(price);
		
		try {
			Ticket ticket = ticketService.addTicket(-1,descr, start,am,am,pr,1);
			model.addAttribute("ticket", ticket);
			
		} catch (Exception e) {
			e.printStackTrace();
			errMap.put("GLOBAL", "发生了非预期错误，请联系平台管理员进行解决。");
			model.addAttribute("errMap", errMap);
	        return "/admin/AddTicket";
		}
		return "admin/AddTicketSucc";
	}
		
	@RequestMapping(value="/admin/UpdateTicketsCtrl")
	public String updateTicket(Model model, HttpSession session, Integer tid){
		try{
			Ticket ticket=ticketService.getTicket(tid);
			model.addAttribute("updateTicket", ticket);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "admin/UpdateTicket";
	}
	
	@RequestMapping(value="/admin/UpdateTicketsCtrl", method=RequestMethod.POST)
		 public String updateTicket(@Valid @ModelAttribute("updateTicket")Ticket ticket,
		   		 Errors errors, Model model, HttpSession session) {
				try {
					ticketService.updateTicket(ticket);
					System.out.println(ticket.getStatus());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "redirect:/admin/ListTicketsCtrl";
	}

	@RequestMapping(value="/admin/UpdateTicketCtrl", method=RequestMethod.GET)
	public String update(Model model, HttpSession session, Integer tid){
		
		int status,newstatus;
		String str=null;
		String but=null;
		try{
			Ticket ticket=ticketService.getTicket(tid);
			status=ticket.getStatus();
			newstatus=1-status;
			ticketService.UpdateStatus(tid);
			
			if(newstatus==0){
				str="已停售";
				but="售票";
			}
			else{
				str="售票中";
				but="停售";
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		model.addAttribute("ticketmsg", "" + tid + ":" +  str+ ":" + but);
		return "admin/AjaxTicketStatus";
	}
	
	@RequestMapping(value="/user/guessCtrl")
	public String ajaxGuessLike(Model model,HttpSession session) {
		
		ArrayList<Integer>Guesslist=(ArrayList<Integer>)session.getAttribute("Guesslist");
		
		int index=0;
		String msg=new  String();
		for(int i=0;i<3;i++){
			index=i%Guesslist.size();
			msg+="/"+Guesslist.get(index)+".jpg:"+Guesslist.get(index)+"\n";
		}
		msg+="/"+Guesslist.get((index+1)%Guesslist.size())+".jpg:"+Guesslist.get((index+1)%Guesslist.size());

		//msg数据由Hive计算中获得
		model.addAttribute("msg", msg);
		return "user/AjaxGuessLike";
	}
	
	@RequestMapping(value="/user/ListTicketsFromCtrl", method=RequestMethod.GET)
	public String listFrom(Model model, HttpSession session, int starttid) {
		session.removeAttribute("TicketQueryState");
		
		TicketQueryState state = new TicketQueryState();
		List<Ticket> list = null;
		try {
			state = ticketService.calcPageState(starttid);
			list = ticketService.getTicketsByPage(state, "cur");
			session.setAttribute("TicketQueryState", state);
			model.addAttribute("lastPage", state.getLastPage());
			model.addAttribute("starttid", starttid);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<Ticket>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
		}

		model.addAttribute("listTickets", list); 
		return "user/BookingTicket";
	}
}