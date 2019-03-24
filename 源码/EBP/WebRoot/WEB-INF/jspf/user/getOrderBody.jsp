<%@ page language="java" import="java.util.*,com.hgd.ebp.domain.Order" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="page">
	<table class="layui-table">
		<tr>
			<th>
			<h3>我的订单</h3>
			</th>
		</tr>
	</table>
	<table class="layui-table">
        <tr>
            <th>序  号</th>
            <th>定单号</th>
            <th>下单时间</th>
            <th>金 额(元)</th>
        </tr>
        <c:forEach var="order" items="${sessionScope.listOrders}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${order.oid}</td>
            <td>${order.commitTime}</td>
            <td>${order.amount}</td>
        </tr>
        </c:forEach>
	</table><br/>
	<!-- <form action='./ListTicketsCtrl' method='POST'> -->
		<table class="layui-table" >
			<tr>
				<td style="text-align:center">
		              <a href='./ListOrdersCtrl?page=0'>首 页</a>&nbsp;&nbsp;&nbsp;&nbsp;  
		              <a href='./ListOrdersCtrl?page=prev'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		              <a href='./ListOrdersCtrl?page=next'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
		              <a href='./ListOrdersCtrl?page=${requestScope.lastPage}'>末 页</a>
		          </td> 
		      </tr>
		</table>
		
		<!-- </form> -->
	
</div>
