package com.hgd.ebp.dao;

import java.util.List;

import com.hgd.ebp.domain.Orderlist;

public interface OrderlistMapper {
	public List<Orderlist>	queryAll();

	public void insert(Orderlist orderlist);
}
