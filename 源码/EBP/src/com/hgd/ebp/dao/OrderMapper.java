package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.Order;

public interface OrderMapper {
	public int queryMaxCount(Map<String, Object> map);
	public List<Order> queryByPage(Map<String, Object> map);
	
	public void insert(Order order);
}
