package com.hgd.ebp.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.OrderMapper;
import com.hgd.ebp.domain.Order;
import com.hgd.ebp.exception.OrderException;
import com.hgd.ebp.state.OrderQueryState;
import com.hgd.ebp.util.WebUtil;

@Service
@Scope("singleton")
public class OrderService {

		@Resource
		private OrderMapper orderDAO;
		public int getLastPage(OrderQueryState state) {
			
			Map<String,Object> map=new HashMap<>();
			int count = orderDAO.queryMaxCount(map);
			
			int maxPage = (count + WebUtil.MAX_PAGE_LINES - 1) / WebUtil.MAX_PAGE_LINES;
			int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
	        return lastPage;
		}
		
		
		public List<Order> getOrdersByPage(OrderQueryState state, 
				String page) throws OrderException {
			int curPage = state.getCurPage();
			switch (page) {
				case "0":
					curPage = 0;
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
			
			List<Order> list = orderDAO.queryByPage(map);
	        return list;
		}
		
		public List<Order> getOrders(OrderQueryState state) throws OrderException {
			Map<String,Object> map=new HashMap<>();
			map.put("startRow", state.getCurPage() * WebUtil.MAX_PAGE_LINES);
			map.put("rowCount", WebUtil.MAX_PAGE_LINES);
			List<Order> list = orderDAO.queryByPage(map);
			
	        return list;
		}
		public Order addOrder(int oid,Timestamp commitTime,double amount,int uid) throws OrderException {
			Order o = new Order(oid, commitTime, amount, uid);
	        orderDAO.insert(o);
			return o;
		}



}
