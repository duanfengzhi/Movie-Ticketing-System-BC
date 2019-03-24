package com.hgd.ebp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hgd.ebp.dao.AdminMapper;
import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminException;
import com.hgd.ebp.exception.AdminLoginException;

@Service
@Scope("singleton")
public class AdminService {
	@Resource
    private AdminMapper adminUserDAO;
    
    public Admin adminLogin(String adminname, String password) 
            throws AdminLoginException, AdminException {
    	Map<String,Object> map=new HashMap<>();
		map.put("adminname", adminname);
		map.put("password", password);
    	
    	List<Admin> list = adminUserDAO.queryByNamePassword(map);
        if (list.size() == 0) {
        	throw new AdminLoginException("用户名或密码错");
        }
        
        if (list.size() > 1) {
        	throw new AdminLoginException("用户重复，请联系管理员");
        }
        
        return list.get(0);
    }
}
