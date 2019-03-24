package com.hgd.ebp.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.hgd.ebp.domain.User;
import com.hgd.ebp.exception.UserLoginException;
import com.hgd.ebp.filter.UserLoginFilter;
import com.hgd.ebp.service.*;
import com.hgd.ebp.state.UserQueryState;
import com.hgd.ebp.state.UserQueryState2;


@SuppressWarnings("serial")
@Controller
@RequestMapping
public class UserController extends HttpServlet {
	private static final String UPLOAD_PATH = "user/images/";
	@Resource
    private UserService userSvc;
	
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    private static final String HOST = "192.168.1.200:10021";
    private static final String URL = "jdbc:hive://" + HOST + "/default";
    
    private static final Object[][] TICKET_DATA = {
		// descr      price
		{1,"复仇者联盟3 无限战争", 50.00},
		{2,"头号玩家", 99.00},
		{3,"侏罗纪世界", 70.00},
		{4,"超人总动员", 60.00},
		{5,"刀剑神域序列之争", 60.00},
		{6,"肖生克的救赎", 50.00},
		{7,"星际穿越", 70.00}
	};
	
	@RequestMapping(value="/user/UserLoginCtrl", method=RequestMethod.POST)
	public String Login(Model model, HttpSession session,
			@Valid @ModelAttribute("user")User user,
			Errors errors){
		if (errors.hasFieldErrors()) return "user/Login";
		try {
			user = userSvc.userLogin(user.getUsername(), user.getPassword());
		} catch (Exception e) {
			errors.reject("", (e instanceof UserLoginException) ?
					e.getMessage() : "发生非预期错误，请联系管理员");
        	return "user/Login";
		}
		session.setAttribute(UserLoginFilter.ATTR_USER, user);
		session.setAttribute("userBalance", user.getBalance());
		session.setAttribute("uid", user.getUid());
		
		int uid=(int)session.getAttribute("uid");
		double avg_price=0;
		try {
			Class.forName(driverName);
	        Connection conn = DriverManager.getConnection(URL, "", "");
	        Statement stmt = conn.createStatement();
	        String sql = "SELECT AVG(price) FROM data WHERE uid="+uid;
	        System.out.println(sql);
	        ResultSet res = stmt.executeQuery(sql);
	        
	        while (res.next()) {
	        	avg_price=res.getDouble(1);
	            System.out.println(res.getDouble(1) ) ;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		double lowprice=avg_price-10;
		double highprice=avg_price +10;
		ArrayList<Integer> Guesslist=new ArrayList<>();
		for(int i=0;i<TICKET_DATA.length;i++){
			double p=(double)TICKET_DATA[i][2];
			if(lowprice<=p&&p<=highprice){
				Guesslist.add((int)TICKET_DATA[i][0]);
			}
		}
		session.setAttribute("Guesslist", Guesslist);
		
        return "redirect:../";	 //重定向， 与转发有着本质上的不同
	}
	
	@RequestMapping(value="/user/LogoutCtrl", method=RequestMethod.GET)
	public String Logout(HttpServletRequest request, HttpSession session)
			throws ServletException, IOException {
		session.removeAttribute(UserLoginFilter.ATTR_USER);
		return "index";
	}
	@RequestMapping(value="/user/reduceBalanceCtrl")
	public String reduceBalance(Model model, HttpSession session,double amount)
			throws ServletException, IOException {
		int uid = (int)session.getAttribute("uid");
		userSvc.reduceBalance(uid, amount);
		
		List<User> list=userSvc.queryByUid(uid);
		session.setAttribute("User", (User)list.get(0));
		
		model.addAttribute("amount", amount);
		return "redirect:/user/AddOrderCtrl";
	}
	
	@RequestMapping(value="/AddUserCtrl", method=RequestMethod.POST)
	public String Add(Model model,String username,String password, String password_two, 
						String name, String gender, String idCard, String address, String telno, HttpSession session)
			throws ServletException, IOException {
		Map<String, String> errMap = new HashMap<String, String>();		
		if (username == null || "".equals(username)) {
			errMap.put("username","请输入您的用户名");
		}		
		if (password == null || "".equals(password)) {
			errMap.put("password","请输入您的用户名密码");
		}		
		if (!password.equals(password_two)) {
			errMap.put("password_two","两次密码不一致，请再确认一遍");
		}
		if (password_two == null || "".equals(password_two)) {
			errMap.put("password_two","请再次输入一遍您的密码");
		}
		if (name == null || "".equals(name)) {
			errMap.put("name","请输入您的真实姓名");
		}
		if (gender == null || "".equals(gender)) {
			errMap.put("gender","请选择您的性别");
		}
		if (idCard == null || "".equals(idCard)) {
			errMap.put("idCard","请输入您的身份证号");
		}else if (idCard.length()!=18) {
			errMap.put("idCard","身份证号长度有误，请重新输入");
		}
		
		if (address == null || "".equals(address)) {
			errMap.put("address","请输入您的通讯地址");
		}
		
		if (telno == null || "".equals(telno)) {
			errMap.put("telno","请输入您的手机号码");
		}else if (telno.length() != 11) {
			errMap.put("telno","手机号码长度有误，请重新输入");
		}
		
		if (errMap.size() != 0) {
			model.addAttribute("errMap", errMap);
	        return "AddUser";
		}
		try{
			User user = userSvc.addUser(username, password, name,gender, idCard, address, telno);
			model.addAttribute("user", user);
			session.setAttribute(UserLoginFilter.ATTR_USER, user);
		} catch(Exception e) {
			e.printStackTrace();
			errMap.put("GLOBAL", "发生了非预期错误，请联系平台管理员进行解决。");
			model.addAttribute("errMap", errMap);
	        return "AddUser";
		}
        return "user/AddUserSucc";
	}
	
	@RequestMapping(value="/user/UpdateUserCtrl", method=RequestMethod.POST)
	public String Update(Model model,String password, String password_two, 
						String name, String gender, String idCard, String address, String telno, HttpSession session)
			throws ServletException, IOException {
		Map<String, String> errMap = new HashMap<String, String>();		
		String username = ((User)session.getAttribute(UserLoginFilter.ATTR_USER)).getUsername();
		if (username == null || "".equals(username)) {
			errMap.put("username","请输入您的新用户名");
		}		
		if (password.equals("") && "".equals(password_two)) {
			User u = (User) session.getAttribute(UserLoginFilter.ATTR_USER);
			password = u.getPassword();
		} else if (!password.equals(password_two)) {
			errMap.put("password_two","两次密码不一致，请再确认一遍");
		}
		if (password_two == null || "".equals(password_two)) {
			errMap.put("password_two","请再次输入一遍您的密码");
		}
		if (gender == null || "".equals(gender)) {
			errMap.put("gender","请选择您的性别");
		}
		if (idCard.length()!=18) {
			errMap.put("idCard","身份证号长度有误，请重新输入");
		}
		if (address == null || "".equals(address)) {
			errMap.put("address","请输入您的通讯地址");
		}
		if (telno == null || "".equals(telno)) {
			errMap.put("telno","请输入您的手机号码");
		}else if (telno.length() != 11) {
			errMap.put("telno","手机号码长度有误，请重新输入");
		}
		if (errMap.size() != 0) {
			model.addAttribute("errMap", errMap);
	        return "user/UpdateUser";
		}

		try{
			User user = userSvc.updateUser(username, password, name,
										gender, idCard, address, telno);
			model.addAttribute("User", user);
			session.removeAttribute(UserLoginFilter.ATTR_USER);
			session.setAttribute(UserLoginFilter.ATTR_USER, user);
			model.addAttribute("succ", "资料修改成功！");
		} catch(Exception e) {
			e.printStackTrace();
			errMap.put("GLOBAL", "发生了非预期错误，请联系平台管理员进行解决。");
			model.addAttribute("errMap", errMap);
	        return "user/UpdateUser";
		}
        return "user/UpdateUser";
	}
	
	@RequestMapping(value="/user/ImageUploadCtrl", method=RequestMethod.POST)
	public String imageUpload(Model model, MultipartFile image, HttpSession session,
			HttpServletRequest request){
		if (image == null ) return "Upload";
		if (image.getOriginalFilename().equals("")) return "Upload";

		//设置上传文件的保存目录
		String path = request.getServletContext().getRealPath(".");
		path += "/"+ UPLOAD_PATH;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        
        String username = ((User)session.getAttribute(UserLoginFilter.ATTR_USER)).getUsername();
        String newFilename= username + ".png";
        try {
            //将上传的文件保存到指定位置
            image.transferTo(new File(path, newFilename));
            System.out.println(path);
            model.addAttribute("filename", "images/" + newFilename);
        } catch (Exception e) {
            e.printStackTrace();
            return "user/Upload";
        } 

        return "user/UploadSucc";
	}
	
	
	@RequestMapping(value="/user/AddMoneyCtrl", method=RequestMethod.POST)
	public String AddMoney(Model model, HttpSession session, String way, String num) {
		Map<String, String> errMap = new HashMap<String, String>();
		if("".equals(num)){
			errMap.put("money", "请输入要充值金额。");
		} else if(Integer.parseInt(num)<=0) {
			errMap.put("money", "请输入正数。");
		}
		if(errMap.size()>0){
			model.addAttribute("errMap", errMap);
			return "user/AddMoney";
		}
		
		try {
			int money = Integer.parseInt(num);
			User u = (User) session.getAttribute(UserLoginFilter.ATTR_USER);
			userSvc.AddMoney(money, u.getUsername());
			double money1 = u.getBalance();
			u.setBalance(money1+money);
			session.removeAttribute(UserLoginFilter.ATTR_USER);
			session.setAttribute(UserLoginFilter.ATTR_USER, u);
			System.out.println(way);
			model.addAttribute("way", way);
			model.addAttribute("numOfMoney",num);
		} catch (Exception e) { 
			e.printStackTrace();
			errMap.put("money", "输入格式有误，请输入具体金额。");
			model.addAttribute("errMap", errMap);
			return "user/AddMoney";
		}
		return "user/AddMoneySucc";
	}	
	//查出所有的之后列表
	@RequestMapping(value="/admin/ListUserCtrl", method=RequestMethod.GET)
	public String listAll(Model model, HttpSession session, String page) {
		UserQueryState state = null;
		if (page == null) {
			page = "0";
			session.removeAttribute("UserQueryState");
			state = new UserQueryState();
		} else {
			state = (UserQueryState)
					session.getAttribute("UserQueryState");
			if (state == null) {
				state = new UserQueryState();
			}
		}
		List<User> list =null;
		try {
			int lastPage = userSvc.getLastPage(state);
			state.setLastPage(lastPage);
			
			list = userSvc.getUsersByPage(state, page);
			session.setAttribute("UserQueryState", state);
			model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<User>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
		}
		model.addAttribute("listusers", list); 
		return "admin/ListUser";
	}	
	//查询条件之后列表
	@RequestMapping(value="/admin/ListUserCtrl", method=RequestMethod.POST)
	public String listBy(Model model, HttpSession session, String starttime, String endtime) {
		Map<String, String> errMap = new HashMap<String, String>();
		boolean error1=false;
		boolean error2=false;
		
		if((starttime==null && endtime==null)||(starttime=="" && endtime=="")){
			errMap.put("date", "请输入日期");
			model.addAttribute("errMap", errMap);
	        return "admin/ListUser";
		} 
		
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
		} catch (ParseException e) {
			starttime = "2001-01-01";
			error1=true;
		}

		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
		} catch (ParseException e) {
			endtime = "2030-12-31";
			error2=true;
		}
		
		if(error1||error2){
			errMap.put("date", "日期格式不正确，请检查后重新输入");
			model.addAttribute("errMap", errMap);
	        return "admin/ListUser";
		}
		
		session.removeAttribute("UserQueryState");
		List<User> list=null;
		String begin=starttime+" 00:00:00";
		String end=endtime+" 00:00:00";
		
		Timestamp ts_begin=Timestamp.valueOf(begin);
		Timestamp ts_end=Timestamp.valueOf(end);
		UserQueryState state= new UserQueryState(0, ts_begin, ts_end);
		
		try {
				int lastPage = userSvc.getLastPage(state);
				state.setLastPage(lastPage);
				list = userSvc.getUsers(state);
				session.setAttribute("UserQueryState", state);
				model.addAttribute("lastPage", lastPage);
			
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<User>();
			errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
		}
		model.addAttribute("listusers", list); 
		return "admin/ListUser";
	}
	@RequestMapping(value="/admin/ListUserCtrl2", method=RequestMethod.POST)
	public String listBy2(Model model, HttpSession session, String username,
						String idCard, String telno) {
		
		//都为空则直接将全部用户列出来
		if((username==null && idCard==null && telno==null)
			||(username=="" && idCard=="" && telno=="")){
			return "redirect:/admin/ListUserCtrl";
		}
		
		session.removeAttribute("UserQueryState2");
		List<User> list=null;
		
		UserQueryState2 state= new UserQueryState2(0, username, idCard,telno,username);
		
		try {
				int lastPage = userSvc.getLastPage(state);
				state.setLastPage(lastPage);
				list = userSvc.getUsers(state);
				session.setAttribute("UserQueryState2", state);
				model.addAttribute("lastPage", lastPage);
		} catch (Exception e) {
			e.printStackTrace();
			list = new ArrayList<User>();
			Map<String, String> errMap = new HashMap<String, String>();
			errMap.put("GLOBAL", "发生非预期错误，请联系管理员");
			model.addAttribute("errMap", errMap);
		}
		model.addAttribute("listusers", list); 
		return "admin/ListUser";
	}
	
	@RequestMapping(value="/admin/UpdateUserCtrl", method=RequestMethod.GET)
	public String update(Model model, HttpSession session, Integer uid){
		
		int newstatus=0;
		int status;
		String str=null;
		String but=null;
		try{
			status=userSvc.queryByUid(uid).get(0).getStatus();
			newstatus=1-status;
			userSvc.UpdateStatus(uid);
			if(newstatus==0){
				str="禁用";
				but="启用";
			} else {
				str="活动";
				but="禁用";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		model.addAttribute("msg", "" + uid + ":" +  str+ ":" + but+ ":"+newstatus);
		return "admin/AjaxUserStatus";
	}

	
}
