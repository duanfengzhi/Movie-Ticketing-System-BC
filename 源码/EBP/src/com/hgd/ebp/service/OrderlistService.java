package com.hgd.ebp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.OrderlistMapper;
import com.hgd.ebp.domain.Orderlist;
import com.hgd.ebp.exception.OrderlistException;

@Service
@Scope("singleton")
public class OrderlistService {
	@Resource
	private OrderlistMapper orderlistDAO;
		
	public List<Orderlist> getOrderlists() throws OrderlistException {
		List<Orderlist> list = orderlistDAO.queryAll();
		return list;
	}
	
	public Orderlist addOderlist(int lid, String descr, double price, int quantity,double amount,int oid) throws OrderlistException {
		Orderlist oderlist =new Orderlist( lid, descr, price, quantity,amount,oid);
        orderlistDAO.insert(oderlist);
		return oderlist;
	}
}

	








