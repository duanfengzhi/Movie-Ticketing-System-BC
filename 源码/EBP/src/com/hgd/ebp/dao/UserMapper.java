package com.hgd.ebp.dao;

import java.util.List;
import java.util.Map;

import com.hgd.ebp.domain.User;

public interface UserMapper {
	public void AddMoney(int money,String username);
	
	public int queryMaxCount1(Map<String, Object> map);
	public int queryMaxCount2(Map<String, Object> map);
	public List<User> queryByPage1(Map<String, Object> map);
	public List<User> queryByPage2(Map<String, Object> map);
	public List<User> queryByNamePassword(String username, String password);
	public List<User> queryByUid(int uid);
	
	public void reduceBalance(int uid,double balance);	
	public void UpdateStatus(int uid);
	public void update(User user);
	public void addUser(User user);
	
	
	
}
