<%@ page language="java" import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>

<div style="center; margin: 80px auto; width:40%;">
	<br/><br/>
	充值成功！<br/><br/>
	<b>本次充值信息如下：</b><br/><br/>
	用户名：${sessionScope.User.username}<br/><br/>
	充值方式：${param.way }<br/><br/>
	充值金额：${param.num }元<br/><br/>
	账户余额：
	<span class='balance_info'>${sessionScope.User.balance }元</span>
</div>