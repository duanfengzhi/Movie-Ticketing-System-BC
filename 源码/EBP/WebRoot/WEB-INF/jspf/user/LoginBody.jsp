<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='java.util.*, com.hgd.ebp.util.*' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<div class="message">用户登录</div>
	 <c:if test="${errMap['GLOBAL'] != null}">
         <p><i class="remind_info">${errMap['GLOBAL']}</i></p>
     </c:if>
<form action='./UserLoginCtrl' method="POST" >
      <table>
          <tr id="1">	              
              <td >	                   
                     <i><input type="text" name="username" autofocus placeholder="用户名" required/></i>
                     </td>
                     <td>
                     <i>${errMap['username']}</i>
                  <br/><br/>
              </td>
          </tr>
          <tr id="1">
              <td >
                      <i><input type="password" name="password" autofocus placeholder="密码" required/></i>
               </td>
               <td>
                      <i>${errMap['password']}</i>
                <br/><br/>
              </td>
          </tr>
          <tr>
              <td><input type=submit value='登录' onclick="checkLoginForm(this)"/></td>
          </tr>
      </table>
</form>	
