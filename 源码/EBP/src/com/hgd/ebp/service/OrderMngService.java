package com.hgd.ebp.service;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.OrderMngMapper;
import com.hgd.ebp.domain.OrderMng;
import com.hgd.ebp.exception.OrderMngException;
import com.hgd.ebp.state.OrderMngQueryState;
import com.hgd.ebp.state.OrderMngQueryState2;
import com.hgd.ebp.util.WebUtil;

@Service
@Scope("singleton")
public class OrderMngService {
	@Resource
	private OrderMngMapper orderMngDAO;
		
	public int getLastPage(OrderMngQueryState state) throws OrderMngException {
		Map<String,Object> map=new HashMap<>();
		map.put("begin", state.getBegin());
		map.put("end", state.getEnd());
		
		List<OrderMng> list=orderMngDAO.queryAll1(map);
		
		
		int count = list.size();
		
		int maxPage = (count + WebUtil.MAX_PAGE_LINES - 1) / WebUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	public int getLastPage(OrderMngQueryState2 state) throws OrderMngException {
		Map<String,Object> map=new HashMap<>();
		map.put("oid", state.getOid());
		map.put("name", state.getName());
		map.put("username", state.getUsername());
		map.put("idCard", state.getIdCard());
		
		List<OrderMng> list=orderMngDAO.queryAll2(map);
		
		
		int count = list.size();
		
		int maxPage = (count + WebUtil.MAX_PAGE_LINES - 1) / WebUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	public List<OrderMng> getOrdersByPage(OrderMngQueryState state, 
			String page) throws OrderMngException {
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
		map.put("begin", state.getBegin());
		map.put("end", state.getEnd());
		
		
		List<OrderMng> list = orderMngDAO.queryAll1(map);
		
		List<OrderMng> list_Order_Mng = new ArrayList<OrderMng>(); 
		
		
		
		list_Order_Mng.add((OrderMng)list.get(0).clone());
		for(int i=1;i<list.size();i++){
			int oid=list.get(i).getOid();
			int flag=1;
			for(int j=0;j<list_Order_Mng.size();j++){
				if(oid==list_Order_Mng.get(j).getOid()){
					String descr=list_Order_Mng.get(j).getDescr();
					list_Order_Mng.get(j).setDescr(descr+" \n "+list.get(i).getDescr());
					flag=0;
					break;
				}
			}
			if(flag==1){
					OrderMng mm=(OrderMng)list.get(i).clone();
					list_Order_Mng.add(mm);
			}
		}
		List<OrderMng> list0 = new ArrayList<>();
		int startRow=state.getCurPage() * WebUtil.MAX_PAGE_LINES;
		for(int i=0;i<WebUtil.MAX_PAGE_LINES&&(startRow+i)<list_Order_Mng.size();i++){
			list0.add(list_Order_Mng.get(startRow+i));
		}
        return list0;
	}
	public List<OrderMng> getOrdersByPage(OrderMngQueryState2 state, 
			String page) throws OrderMngException {
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
		map.put("oid", state.getOid());
		map.put("name", state.getName());
		map.put("username", state.getUsername());
		map.put("idCard", state.getIdCard());
		
		List<OrderMng> list = orderMngDAO.queryAll2(map);
		List<OrderMng> list_Order_Mng = new ArrayList<OrderMng>();
		
		list_Order_Mng.add((OrderMng)list.get(0).clone());
		for(int i=1;i<list.size();i++){
			int oid=list.get(i).getOid();
			int flag=1;
			for(int j=0;j<list_Order_Mng.size();j++){
				if(oid==list_Order_Mng.get(j).getOid()){
					String desc=list_Order_Mng.get(j).getDescr();
					list_Order_Mng.get(j).setDescr(desc+" \n "+list.get(i).getDescr());
					flag=0;
					break;
				}
			}
			if(flag==1){
					list_Order_Mng.add((OrderMng)list.get(i).clone());
			}
		}
		List<OrderMng> list0 = new ArrayList<>();
		int startRow=state.getCurPage() * WebUtil.MAX_PAGE_LINES;
		for(int i=0;i<WebUtil.MAX_PAGE_LINES&&(startRow+i)<list_Order_Mng.size();i++){
			list0.add(list_Order_Mng.get(startRow+i));
		}
		
        return list0;
	}
	public List<OrderMng> getOrders(OrderMngQueryState state) throws OrderMngException {
		Map<String,Object> map=new HashMap<>();
		map.put("begin", state.getBegin());
		map.put("end", state.getEnd());
		List<OrderMng> list = orderMngDAO.queryAll1(map);
		
		List<OrderMng> list_Order_Mng = new ArrayList<OrderMng>(); 
		
		
		
		list_Order_Mng.add((OrderMng)list.get(0).clone());
		for(int i=1;i<list.size();i++){
			int oid=list.get(i).getOid();
			int flag=1;
			for(int j=0;j<list_Order_Mng.size();j++){
				if(oid==list_Order_Mng.get(j).getOid()){
					String descr=list_Order_Mng.get(j).getDescr();
					list_Order_Mng.get(j).setDescr(descr+" \n "+list.get(i).getDescr());
					flag=0;
					break;
				}
			}
			if(flag==1){
					OrderMng mm=(OrderMng)list.get(i).clone();
					list_Order_Mng.add(mm);
			}
		}
		List<OrderMng> list0 = new ArrayList<>();
		int startRow=state.getCurPage() * WebUtil.MAX_PAGE_LINES;
		for(int i=0;i<WebUtil.MAX_PAGE_LINES&&(startRow+i)<list_Order_Mng.size();i++){
			list0.add(list_Order_Mng.get(startRow+i));
		}
        return list0;
	}
	public List<OrderMng> getOrders(OrderMngQueryState2 state) throws OrderMngException {
		Map<String,Object> map=new HashMap<>();
		map.put("oid", state.getOid());
		map.put("name", state.getName());
		map.put("username", state.getUsername());
		map.put("idCard", state.getIdCard());
		List<OrderMng> list = orderMngDAO.queryAll2(map);
		List<OrderMng> list_Order_Mng = new ArrayList<OrderMng>();
		System.out.println("???"+state);
		list_Order_Mng.add((OrderMng)list.get(0).clone());
		for(int i=1;i<list.size();i++){
			int oid=list.get(i).getOid();
			int flag=1;
			for(int j=0;j<list_Order_Mng.size();j++){
				if(oid==list_Order_Mng.get(j).getOid()){
					String desc=list_Order_Mng.get(j).getDescr();
					list_Order_Mng.get(j).setDescr(desc+" \n "+list.get(i).getDescr());
					flag=0;
					break;
				}
			}
			if(flag==1){
					list_Order_Mng.add((OrderMng)list.get(i).clone());
			}
		}
		List<OrderMng> list0 = new ArrayList<>();
		int startRow=state.getCurPage() * WebUtil.MAX_PAGE_LINES;
		for(int i=0;i<WebUtil.MAX_PAGE_LINES&&(startRow+i)<list_Order_Mng.size();i++){
			list0.add(list_Order_Mng.get(startRow+i));
		}
		
		System.out.println(list);
        return list0;
	}
}













