<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div style="center; margin: 80px auto; width:40%;">
	<p>用&nbsp;&nbsp;户&nbsp;&nbsp;名:
	<b>${sessionScope.User.username}</b></p><br/>
	账户余额：<span class='balance_info'>${sessionScope.User.balance}</span>
	<br/><br/><br/>
	<form action='./AddMoneyCtrl' method='POST' >
		充值方式：
		<select name="way">
			<option value="支付宝 " selected> 支付宝 </option>
			<option value="微信" > 微信 </option>
			<option value="QQ钱包"> QQ钱包 </option>
			<option value="银行卡"> 银行卡 </option>
		</select><br/><br/><br/>
		
		充值金额：<input type=text name='num' value="2000">元<br/><br/><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type=submit value=充值 style="width:80px;height:23px">
	</form>
</div>