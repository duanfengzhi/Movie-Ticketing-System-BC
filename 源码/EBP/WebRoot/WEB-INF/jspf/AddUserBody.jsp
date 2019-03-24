
<%@ page contentType="text/html" language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width:800px;height:800px;margin:auto">
<form action='./AddUserCtrl' method='POST' >
	<table class="layui-table" border="1" style="border-collapse:collapse;">
		<tr>
			<td style="text-align:right">用户名:<br/><br/></td>
			<td><input id="user" type=text name='username' value='${param.username}' onblur="checkUser()" />
				<span id="seasonErr" class="err_msg">${errMap['username'] != null ? errMap['username'] : "" }
				</span><br/><br/>
			</td>
			<td><div id="user_prompt"></div></td>
		</tr>
		<tr>
			<td style="text-align:right">密码:<br/><br/></td>
			<td><input id="pwd" type="password" name='password' value='${param.password }'  onblur="checkPwd()"/>
				<span id="seasonErr" class="err_msg">${errMap['password'] }</span>
			</td>
			<td><div id="pwd_prompt"></div></td>
		</tr>
		
		<tr>
			<td style="text-align:right">请再次输入您的密码:<br/><br/></td>
			<td><input id="repwd" type="password" name='password_two' value="${param.password_two }" onblur="checkRepwd()"/>
				<span id="seasonErr" class="err_msg">${errMap['password_two'] != null ? errMap['password_two'] : "" }
				</span><br/><br/>
			</td>
			<td><div id="repwd_prompt"></div></td>
		</tr>
		
		<tr>
			
			<td style="text-align:right">您的姓名:<br/><br/></td>
			<td><input type=text name='name' value="${param.name }" />
				<span id="seasonErr" class="err_msg">${errMap['name'] != null ? errMap['name'] : "" }
				</span><br/><br/>
			</td>
		</tr>
		
		<tr>
			
			<td style="text-align:right">性别:<br/><br/></td>
			<td>
				<input type=radio name='gender' value='Man'
					${param.gender == 'Man' ? 'checked' : ''}/>男
				<input type=radio name='gender' value='Woman'
					${param.gender == 'Woman' ? 'checked' : ''}/>女
				
				<span id="seasonErr" class="err_msg">${errMap['gender'] != null ? errMap['gender'] : "" }
				</span><br/><br/>
			</td>
		</tr>
		
		<tr>
			<td style="text-align:right">身份证号:<br/><br/></td>
			<td><input id="idcardnumber" type=text name='idCard' value="${param.idCard }" onblur="checkIdcardnumber()"/>
				<span id="seasonErr" class="err_msg">${errMap['idCard'] != null ? errMap['idCard'] : "" }
				</span><br/><br/>
				 <div id="idcardnumber_prompt"></div>
			</td>
		</tr>
		
		<tr>
			
			<td style="text-align:right">通讯地址:<br/><br/></td>
			<td><input type=text name='address' value="${param.address }" />
				<span id="seasonErr" class="err_msg">${errMap['address'] != null ? errMap['address'] : "" }
				</span><br/><br/></td>
		</tr>
		
		<tr>
			
			<td style="text-align:right">手机号:<br/><br/></td>
			<td><input id="mobile" type=text name='telno' value="${param.telno }" onblur="checkMobile()"/>
				<span id="seasonErr" class="err_msg">${errMap['telno'] != null ? errMap['telno'] : "" }</span></td>
				 <td><div id="mobile_prompt"></div></td>
		</tr>
	</table>
	<div style="margin:10px auto;width:400px;float:left">
		<input type=submit value='注册' style="margin:0 auto" class="layui-btn layui-btn-normal"/>
	</div>
	<div style="margin:10px auto;width:400px;float:left">
		<input type=reset value='重置' style="margin:0 auto;text-align:center" class="layui-btn layui-btn-normal"/>
	</div>
	<span id="seasonErr" class="err_msg">${errMap['GLOBAL'] != null ? errMap['GLOBAL'] : "" }</span>
</form>
</div>