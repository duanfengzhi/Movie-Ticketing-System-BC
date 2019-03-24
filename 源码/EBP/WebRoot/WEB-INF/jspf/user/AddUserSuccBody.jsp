<%@ page language="java" import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>
<form action='./AddUserCtrl' method=POST><br/>
			<h3>恭喜您注册成功!</h3>
			<h3>您是我们的第${requestScope.user.uid }个用户！</h3><br/>
			<table class='add_table'>
				<tr>
					<td>用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
					<td>${requestScope.user.username }</td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;码:</td>
					<td class='remind_info'>${requestScope.user.password }&nbsp;&nbsp;
					请牢记您的密码！</td>
				</tr>
				<tr>
					<td>您的名字: </td>
					<td>${requestScope.user.name }</td>
				</tr>
				<tr>
					<td>您的性别: </td>
					<td>${requestScope.user.gender }</td>
				</tr>
				<tr>
					<td>身份证号: </td>
					<td>${requestScope.user.idCard }</td>
				</tr>
				<tr>
					<td>通讯地址: </td>
					<td>${requestScope.user.address }</td>
				</tr>
				<tr>
					<td>联系电话: </td>
					<td>${requestScope.user.telno }</td>
				</tr>
				<tr>
					<td>注册时间: </td>
					<td>${requestScope.user.regTime }</td>
				</tr>
				<tr>
					<td>账户余额: </td>
					<td>${requestScope.user.balance }</td>
				</tr>
				<tr>
					<td>账户状态: </td>
					<td>活动</td>
				</tr>
				
			</table>
		</form>