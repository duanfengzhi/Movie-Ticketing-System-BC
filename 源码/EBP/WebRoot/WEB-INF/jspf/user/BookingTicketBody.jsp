<%@ page import="java.util.*, com.hgd.ebp.domain.Ticket" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action='./ListTicketsCtrl' method='POST'>
	<table class="layui-table" >
		<tr>
				<td >起始日期:</td>
				<td><input type=date name='date' 
				value=${requestScope.date == null? '2018-07-10': requestScope.date } />（起始日期起7天内）</td>
				<td id="err_msg">${errMap['date'] == null? '' : errMap['date'] }</td>
				
                <td >价 格: </td>
                <td><input type=text name='price' 
                value=${requestScope.price == null ? "50" : requestScope.price } /></td>
				<td id="err_msg">${errMap['price'] == null? '' : errMap['price'] }</td>
				
                <td><input type=submit value='查询'  class="layui-btn layui-btn-normal"/></td>
        </tr>
	</table>
</form>

<table class="layui-table" style="text-align:center">
    <thead>
	    <tr>
	        <th style="width: 5%">序号</th>
	        <th style="width: 25%">名 称</th>
	        <th style="width: 20%">时间</th>
	        <th style="width: 10%">总票数</th>
	        <th style="width: 15%">剩余票数</th>
	        <th style="width: 10%">单 价</th>
	        <th style="width: 10%">状 态</th>
	        <th style="width: 15%">购 票</th>
	        <th style="width: 15%"></th>
	        
	    </tr>
    </thead>
    <c:forEach var="t" items="${listTickets}">
    <tr>
    	<td>
	    	${t.tid }
	    	<c:if test="${starttid == t.tid}">
	                <img src='<c:url value="/images/userlmg/love.png"/>'
	                    width=20px height=20px style='vertical-align: text-bottom' />
	        </c:if>
    	</td>
        <td>${t.descr }</td>
        <td>${t.startTime }</td>
        <td>${t.amount }</td>
        <td>${t.balance }</td>
        <td>${t.price }</td>
        <td style="font-size:2 ; font-color:${t.status == 0 ? 'red':'black' }">
        ${t.status == 0 ? '已停售':'售票中' }</td>

        <c:if test="${ t.status==1}">
     		<form  action='./AddShoppingCartCtrl' method='POST'>
        <td>
				<span><input type="text"  name='num' style="width:30px"/></span>
				<input type="hidden" name='tid' value=${t.tid }>
		</td>
		<td>
	            <span><input type="submit" value="加入购物车" class="layui-btn layui-btn-normal layui-btn-xs"/></span>
    			<label class="err_msg">${errMap['numErr'] != NULL ? errMap['numErr'] : "" }</label>
    	</td>
        	</form>
		</c:if>
		<c:if test="${ t.status==0}">
			<td></td>
			<td></td>
		</c:if>
    </tr>
	</c:forEach>
</table>

<table class="layui-table" >
	<tr>
		<td style="text-align:center">
              <a href='./ListTicketsCtrl?page=0'>首 页</a>&nbsp;&nbsp;&nbsp;&nbsp;  
              <a href='./ListTicketsCtrl?page=prev'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
              <a href='./ListTicketsCtrl?page=next'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
              <a href='./ListTicketsCtrl?page=${requestScope.lastPage}'>末 页</a>
          </td> 
      </tr>
</table>

<form action='./ShowShoppingCart.jsp' style="float:none">
	<input type="submit" style="font-size:10" class="layui-btn layui-btn-normal" value="查看购物车/结账 " />
</form>






