<%@ page language="java" import="java.util.*, com.hgd.ebp.domain.Ticket" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
<div style="float:left;text-align:center;width:70%;height:600px;">
	<div style="float:left;text-align:left;width:70%;height:600px;">
		<br/><span style=" size:3;color:#333333">您的预定已成功！</span><br/><br/>
		定单号：${requestScope.oid}<br/><br/>
		定单生成时间： ${requestScope.commitTime }<br/><br/>
		成交金额： ${requestScope.amount }元 <br/><br/>
		以下是该定单详细信息：<br/><br/><br/>

		<table class="layui-table" >
		    <tr>
		        <th style="width: 10%">序 号</th>
		        <th style="width: 40%">名 称</th>
		        <th style="width: 20%">单 价</th>
		        <th style="width: 20%">购票数</th>
		        <th style="width: 20%">总 价</th>
		    </tr>
	                      
			<c:set var="allnum" value='0' />
			<c:set var="allprice" value='0' />
			<c:forEach var="o" items="${requestScope.ownticket}" varStatus="loop">
			<c:set var="num" value="${requestScope.numList[loop.count-1]}"/>
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
		    </tr>
		    
		</table>
                   
		<br/><br/>恭喜您预订成功，出票码已发送至您的手机，请注意查收。
	</div>
</div>