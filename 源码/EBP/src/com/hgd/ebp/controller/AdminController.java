package com.hgd.ebp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hgd.ebp.domain.Admin;
import com.hgd.ebp.exception.AdminLoginException;
import com.hgd.ebp.filter.AdminLoginFilter;
import com.hgd.ebp.service.AdminService;

@SuppressWarnings("serial")
@Controller
@RequestMapping(value="/admin")
public class AdminController extends HttpServlet {
	@Resource
	private AdminService adminSvc;
	@RequestMapping(value="/LoginCtrl", method=RequestMethod.POST)
	public String login(Model model, HttpSession session,@Valid @ModelAttribute("admin")Admin admin,
			Errors errors)  {
		if (errors.hasFieldErrors()) return "admin/Login";
				
				try {
					 admin= adminSvc.adminLogin(admin.getAdminname(), admin.getPassword());
	
				} catch (Exception e) {
					errors.reject("", (e instanceof AdminLoginException) ? 
							e.getMessage() : "发生非预期错误，请联系管理员");
		        	return "admin/Login";
				} 
				session.setAttribute(AdminLoginFilter.ATTR_ADMINUSER, admin);
				return "redirect:./";
	}
	@RequestMapping(value="/LogoutCtrl", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(AdminLoginFilter.ATTR_ADMINUSER);
		return "redirect:../";
	}
}
