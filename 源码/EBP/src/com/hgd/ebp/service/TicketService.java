package com.hgd.ebp.service;

import static com.hgd.ebp.util.WebUtil.MAX_PAGE_LINES;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.TicketMapper;
import com.hgd.ebp.domain.Ticket;
import com.hgd.ebp.exception.TicketException;
import com.hgd.ebp.state.TicketQueryState;
import com.hgd.ebp.util.WebUtil;

@Service
@Scope("singleton")
public class TicketService {
	
	@Resource
	private TicketMapper ticketDAO;
	
	public int getLastPage(TicketQueryState state) {
		Map<String,Object> map=new HashMap<>();
		map.put("date", state.getDate());
		map.put("edate", state.getEdate());
		map.put("qBegin",state.getBegin());
		map.put("qEnd", state.getEnd());
		
		int count = ticketDAO.queryMaxCount(map);
		
		int maxPage = (count + WebUtil.MAX_PAGE_LINES - 1) / WebUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	

	public List<Ticket> getTicketsByPage(TicketQueryState state, 
			String page)  {
		int curPage = state.getCurPage();
		switch (page) {
			case "0":
				curPage = 0;
				break;
			case "cur":
				break;
			case "prev":
				if (curPage > 0) curPage--;
				break;
			case "next":
				if (curPage < state.getLastPage()) curPage++;
				break;
			default:
				curPage = state.getLastPage();
				break;
		}
		state.setCurPage(curPage);
		
		Map<String,Object> map=new HashMap<>();
		map.put("startRow", state.getCurPage() * WebUtil.MAX_PAGE_LINES);
		map.put("rowCount", WebUtil.MAX_PAGE_LINES);
		map.put("date", state.getDate());
		map.put("edate", state.getEdate());
		map.put("price",state.getPrice());
		map.put("qBegin",state.getBegin());
		map.put("qEnd", state.getEnd());
		List<Ticket> list = ticketDAO.queryByPage(map);
        return list;
	}
	public TicketQueryState calcPageState(int tid) {
		int maxCount = ticketDAO.queryMaxCount(new HashMap<String, Object>());
		int count = ticketDAO.queryLessThenCount(tid);
		int maxPage = (maxCount + MAX_PAGE_LINES - 1) / MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;
		int curPage = (maxCount == count) ? 0 : (maxCount - count - 1) / MAX_PAGE_LINES;
		TicketQueryState state = new TicketQueryState();
		state.setCurPage(curPage);
		state.setLastPage(lastPage);
		return state;
	}
	
public List<Ticket> getTickets(TicketQueryState state) {
		
		Map<String,Object> map=new HashMap<>();
		map.put("startRow", state.getCurPage() * WebUtil.MAX_PAGE_LINES);
		map.put("rowCount", WebUtil.MAX_PAGE_LINES);
		map.put("date", state.getDate());
		map.put("edate", state.getEdate());
		map.put("price",state.getPrice());
		map.put("qBegin",state.getBegin());
		map.put("qEnd", state.getEnd());
		
		List<Ticket> list = ticketDAO.queryByPage(map);
		
        return list;
	}
public List<Ticket> getAllTickets()  {
	List<Ticket> list = ticketDAO.queryAll();
	
    return list;
}
	
public Ticket addTicket(int tid,String descr,Timestamp startTime,int amount,int balance,double price,int status) throws TicketException {
	Ticket t=new Ticket(tid, descr, startTime, amount, balance, price, status);
	ticketDAO.insert(t);
	System.out.println(t);
    return t;
}
public void reduceTicket(int tid,int num){
	
	ticketDAO.reduceTicket(tid,num);
	
}
	
public void UpdateStatus(int tid){
	
	ticketDAO.UpdateStatus(tid);
	
}
public void updateTicket(Ticket ticket){

	ticketDAO.updateTicket(ticket);

}

public Ticket getTicket(int tid){

	Ticket ticket=ticketDAO.getTicket(tid);
	return ticket;

}

	
}
