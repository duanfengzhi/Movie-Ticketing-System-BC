package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.Ticket;

public interface TicketMapper {
	public int queryMaxCount(Map<String, Object> map);
	public List<Ticket>	queryAll();
	public List<Ticket> queryByPage(Map<String, Object> map);
	public int queryLessThenCount(int tid);
	public Ticket getTicket(int tid);
	public void reduceTicket(int tid,int num);
	public void UpdateStatus(int tid);
	public void updateTicket(Ticket ticket);
	public void insert(Ticket ticket);
}
