<%@ page language="java" import="java.util.Date, java.sql.*, com.hgd.ebp.domain.*, com.hgd.ebp.util.* " pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@ taglib prefix="fs" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="filter_form"><br/>
	<form action='./ListUserCtrl' method=POST>
		<table  class="layui-table"  >
			<tr>
				<td >起始日期:</td>
				<td><input type=date name='starttime' 
				value="${param.starttime == null? '2018-01-01' : param.starttime }" /></td>
				<td id="err_msg">${errMap['startdate'] == null? '' : errMap['startdate'] }</td>
				
                <td >终止日期:</td>
                <td><input type=date name='endtime' 
                value="${param.endtime == null? '2018-09-30': param.endtime }" /></td>
				<td id="err_msg">${errMap['enddate'] == null? '' : errMap['enddate'] }</td>
				
				<td id="err_msg">${errMap['date'] == null? '' : errMap['date'] }</td>
				
                <td><input type=submit value='查询'  class="layui-btn layui-btn-normal"/></td>
            </tr>
         </table>
     </form>
     
     <form action='./ListUserCtrl2'  method=POST>
     	<table  class="layui-table">
            <tr>
                <td >姓名/用户名:</td>
                <td><input type=text name='username' value="${param.username}"/></td>
                
				<td id="err_msg">${errMap['username'] == null? '' : errMap['username'] }</td>
                <td >身份证号:</td>
                
                <td><input type=text name='idCard' value="${param.idCard}"/></td>
				<td id="err_msg">${errMap['idCard'] == null? '' : errMap['idCard'] }</td>
				
                <td >联系电话:</td>
                <td><input type=text name='telno' value="${param.telno}"/></td>
				<td id="err_msg">${errMap['telno'] == null? '' : errMap['telno'] }</td>
				
                <td><input type=submit value='查询'  class="layui-btn layui-btn-normal"/></td>
            </tr>
         </table>
	  </form>
	  	 
	<table  class="layui-table"  >
		<tr>
  	 		<th>编号</th>
  	 		<th>姓名</th>
  	 		<th>用户名</th>
  	 		<th>性别</th>
  	 		<th>身份证号</th>
  	 		<th>住址</th>
  	 		<th>头像</th>
  	 		<th>联系电话</th>
  	 		<th>注册时间</th>
  	 		<th>账户余额</th>
  	 		<th>状态</th>
  	 		<th>&nbsp;&nbsp;</th>
  	 	</tr>
	  	 	
		<c:forEach var="user" items="${listusers}">
  	 	<tr>
  	 		<td>${user.uid}</td>
  	 		<td>${user.name}</td>
  	 		<td>${user.username}</td>
  	 		<td>${user.gender}</td>
  	 		<td>${user.idCard}</td>       
  	 		<td>${user.address}</td>
  	<%--  	"<c:url value="/user/images/${user.username}Img.png"/>"  
  			"../user/images/${user.username}Img.png"	--%>
  	 		<td><img src="<c:url value="/user/images/${user.username}.png"/>"
				class="layui-nav-img" style="display:none" onload="this.style.display=''"></td>
  	 		<td>${user.telno} </td>
  	 		<td>${user.regTime}</td>
  	 		<td id="balance_info">${user.balance}</td>
  	 		
  	 		
  	 		
  	 		<c:url var="urlStr" 
	  	 		value='/admin/UpdateUserCtrl?uid=${user.uid}' />
	        	<td><span id='ajaxUserStatus${user.uid}'>${user.status== 0 ?  '禁用':'活动' }
	        	</span></td>
	            
	            <td><a href='####' onclick="adjustUserStatus('${urlStr}')">
	            <span id='ajaxUserButton${user.uid}' 
	            class="${user.status== 1 ?  
	            'layui-btn layui-btn-danger layui-btn-sm':
	            'layui-btn layui-btn-normal layui-btn-sm'}"
	            
	            >
	            ${user.status== 0 ?  '启用':'禁用' }
	            </span></a></td>
  	 	</tr>
  	 	
  	 	</c:forEach>
	</table>
	<table class="layui-table" >
		<tr>
			<td style="text-align:center">
	              <a href='./ListUserCtrl?page=0'>首 页</a>&nbsp;&nbsp;&nbsp;&nbsp;  
	              <a href='./ListUserCtrl?page=prev'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
	              <a href='./ListUserCtrl?page=next'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
	              <a href='./ListUserCtrl?page=${requestScope.lastPage}'>末 页</a>
	          </td> 
	      </tr>
	</table>
</div>

