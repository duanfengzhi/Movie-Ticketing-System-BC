<%@ page language="java" import="java.util.*,com.hgd.ebp.domain.Ticket" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<div id="filter_form">
	<table class="layui-table" >
	    <thead>
		    <tr>
		        <th style="width: 10%">序 号</th>
		        <th style="width: 40%">名 称</th>
		        <th style="width: 20%">单 价</th>
		        <th style="width: 20%">购票数</th>
		        <th style="width: 20%">总 价</th>
		        <th></th>
		    </tr>
	    </thead>
		<c:set var="allnum" value='0' />
		<c:set var="allprice" value='0' />
		<c:forEach var="o" items="${sessionScope.ownticket}" varStatus="loop">
			<c:set var="num" value="${sessionScope.numList[loop.count-1]}"/>
			<c:forEach var="t" items="${sessionScope.tickets}">
				<c:if test="${ o==t.tid}">
					<c:set var="allnum" value="${allnum+num }"/>
					<c:set var="allprice" value="${allprice+num*t.price }"/>
					<tr>
					<td>${t.tid }</td>
					<td>${t.descr }</td>
					<td>${t.price }</td>
					<td>${num}</td>
					<td>${num*t.price}</td>
					<td><form  action='./DeleteTicketCtrl' method='POST'>
					<input type="hidden" name='point' value=${loop.count-1 }>
					<input type="submit" value="删除" class="layui-btn layui-btn-primary"/>
					</form></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
	    <tr>
			<td>总计</td>
			<td> </td>
			<td> </td>
			<td>${allnum}</td>
			<td>${allprice}</td>
			<td> </td>
	    </tr>
	</table>
	<div id="center-div">
		<form action='./BookingTicket.jsp' style="float:left">
			<input 	style="font-size:35"type="submit"name=""value="继续订票" 	class="layui-btn layui-btn-danger"/>
		</form>
		<form action='./CheckOutCtrl' style="float:right;margin:auto 100px">
			<input 	style="font-size:35"type="submit"name=""value="结  账" 	class="layui-btn layui-btn-danger"/>
		</form>
	</div>

</div>
<c:if test="${errMap['moneyErr'] != NULL}">
<div>
  	<label class="err_msg">${errMap['moneyErr'] != NULL ? errMap['moneyErr'] : "" }</label>
  	<label class="err_msg"><br/><br/>您的余额：${sessionScope.User.balance}<br/><br/></label>
  	<a href='<c:url value="/user/AddMoney.jsp" />' ><label class="layui-btn layui-btn-danger">去 充 值</label></a>
</div>
</c:if>
