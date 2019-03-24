package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.OrderMng;

public interface OrderMngMapper {
	public List<OrderMng> queryAll1(Map<String, Object> map);
	public List<OrderMng> queryAll2(Map<String, Object> map);
	

}
