package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.Admin;

public interface AdminMapper {
	public List<Admin> queryByNamePassword(Map<String, Object> map);
	
}
