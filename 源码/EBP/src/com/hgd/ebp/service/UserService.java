package com.hgd.ebp.service;

import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.*;
import com.hgd.ebp.domain.*;
import com.hgd.ebp.exception.*;
import com.hgd.ebp.state.UserQueryState;
import com.hgd.ebp.state.UserQueryState2;
import com.hgd.ebp.util.WebUtil;

@Service
@Scope("singleton")
public class UserService {
	@Resource
	private UserMapper userDAO;
	
	
	public int getLastPage(UserQueryState state) throws UserException {
		Map<String,Object> map=new HashMap<>();
		map.put("begin", state.getBegin());
		map.put("end", state.getEnd());
		int count = userDAO.queryMaxCount1(map);
		
		int maxPage = (count + WebUtil.MAX_PAGE_LINES - 1) / WebUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	
	public int getLastPage(UserQueryState2 state) throws UserException {
		Map<String,Object> map=new HashMap<>();
		map.put("username", state.getUsername());
		map.put("idCard", state.getIdCard());
		map.put("telno",state.getTelno());
		map.put("name", state.getName());
		
		int count = userDAO.queryMaxCount2(map);
		
		int maxPage = (count + WebUtil.MAX_PAGE_LINES - 1) / WebUtil.MAX_PAGE_LINES;
		int lastPage = (maxPage > 0) ? maxPage - 1 : 0;  
        return lastPage;
	}
	
	public List<User> getUsersByPage(UserQueryState state, 
			String page) throws UserException {
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
		map.put("begin", state.getBegin());
		map.put("end", state.getEnd());
		
		List<User> list = userDAO.queryByPage1(map);
        return list;
	}
	
	public List<User> getUsersByPage(UserQueryState2 state, 
			String page) throws UserException {
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
		map.put("username", state.getUsername());
		map.put("idCard", state.getIdCard());
		map.put("telno",state.getTelno());
		map.put("name", state.getName());
		
		
		List<User> list = userDAO.queryByPage2(map);
        return list;
	}
	
	public List<User> getUsers(UserQueryState state) throws UserException{
		Map<String,Object> map=new HashMap<>();
		map.put("startRow", state.getCurPage() * WebUtil.MAX_PAGE_LINES);
		map.put("rowCount", WebUtil.MAX_PAGE_LINES);
		map.put("begin", state.getBegin());
		map.put("end", state.getEnd());
		List<User> list = userDAO.queryByPage1(map);
		return list;
	}
	
	public List<User> getUsers(UserQueryState2 state) throws UserException{
		Map<String,Object> map=new HashMap<>();
		map.put("startRow", state.getCurPage() * WebUtil.MAX_PAGE_LINES);
		map.put("rowCount", WebUtil.MAX_PAGE_LINES);
		map.put("username", state.getUsername());
		map.put("idCard", state.getIdCard());
		map.put("telno",state.getTelno());
		map.put("name", state.getName());
		List<User> list = userDAO.queryByPage2(map);
		return list;
	}
	
	public List<User> queryByUid(int uid){
		List<User> list = userDAO.queryByUid(uid);
		return list;
	}
	
	public void UpdateStatus(int uid)throws Exception{
		
		userDAO.UpdateStatus(uid);
		
	}
	
	
	public User userLogin(String username, String password) 
            throws UserLoginException, UserException {
        List<User> list = userDAO.queryByNamePassword(username, password);
        if (list.size() == 0) {
        	throw new UserLoginException("用户名或密码错");
        }
        
        if (list.size() > 1) {
        	throw new UserLoginException("用户重复，请联系管理员");
        }
        
        return list.get(0);
    }
	
	public User addUser(String username, String password, String name,
			String gender, String idCard, String address, String telno) throws UserException{
		Timestamp d =new Timestamp(System.currentTimeMillis());
		User user=new User(1,username, password, name, gender, idCard, address, telno, d, 0, 0);
		
		userDAO.addUser( user);
		return user;
	}
	
	public User updateUser(String username, String password, String name,
			String gender, String idCard, String address, String telno) throws UserException{
		Timestamp d =new Timestamp(System.currentTimeMillis());
		User user=new User(1,username, password, name, gender, idCard, address, telno, d, 0, 0);
		userDAO.update(user);
		return user;
	}
	public void reduceBalance(int uid,double balance){
		userDAO.reduceBalance(uid, balance);
		
	}
	
	public void AddMoney(int money, String username) throws UserException{
		userDAO.AddMoney(money, username);
	}

}
