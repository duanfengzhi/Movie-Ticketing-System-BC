<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='java.util.*, com.hgd.ebp.util.*' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<div class="message">管理员登录</div>
 		
        <jsp:useBean id="admin" scope="request" class="com.hgd.ebp.domain.Admin" />
	       <form:form modelAttribute="admin" action="./LoginCtrl" method="POST" >
	       <p ><font color='red' size="-1"><i><form:errors /></i></font></p>
	        <table>
	            <tr id="1">
	                <td id="2">用户名<br/><br/></td>
	                <td >	                   
	                       <form:input path="adminname" autofocus='true' placeholder="请输入用户名" required='true'/><br/><br/>
	                       </td>
	                       <td>
	                       <i><font color='red' size="-1"><form:errors path="adminname"/></font></i>
	                    <br/><br/>
	                </td>
	            </tr>
	            <tr id="1">
	                <td id="2">密&nbsp;&nbsp;&nbsp;&nbsp;码<br/><br/></td>
	                <td >	                    
	                        <form:password path="password" autofocus='true' placeholder="请输入密码" required='true'/>
	                    <br/><br/>
	                 </td>
	                 <td>      
	                        <i><font color='red' size="-1"><form:errors path="password"  /></font></i>
	                  <br/><br/>
	                </td>
	            </tr>
	            <tr>
	                <td id="2"></td>
	                <td><input type=submit value='登录' onclick="checkLoginForm(this)"/></td>
	            </tr>
	        </table>
	    </form:form>
	


	
