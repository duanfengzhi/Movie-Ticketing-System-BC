<%@ page import="java.util.*,com.hgd.ebp.domain.OrderMng" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<div  id="filter_form">
	<form action='./ListOrderMngCtrl' method='POST'>   
		<table class="layui-table" >
			<tr>
				<td>起始日期:<input type=date  name="begin_time" value="${param.begin_time == null? '2018-04-20' : param.begin_time }" size="20" /></td>
				<td>终止日期: &nbsp;&nbsp;<input type=date  name="end_time" value="${param.end_time == null? '2018-05-08' : param.end_time }" size="20" /></td>
				<td style="color:red"><i>${errMap['date'] == null? '' : errMap['date'] }</i></td>
				<td><input type="submit" class="layui-btn layui-btn-normal" value="查询" size="15" /></td>
			</tr>
		</table>
	</form>
	<form action='./ListOrderMngCtrl2' method='POST'> 
		<table class="layui-table" >
			<tr>
				<td> 订单号： <input type="text" value="${param.oid == null? '' : param.oid }" name="oid" size="20" /></td>
				<td> 姓名/用户名：&nbsp; &nbsp;<input type="text" value="${param.name == null? '' : param.name }"  name="name" size="15" /></td>
				<td>身份证号：<input type="text" value="${param.idCard == null? '' : param.idCard }" name="idCard"  size="20" /></td>
				<td><input type="submit" class="layui-btn layui-btn-normal" value="查询" size="10" /></td>
			</tr>
		</table>
	</form>
</div>
	
<div id="filter_form">
	<table class="layui-table">
		<tr>
			<th>编号</th>
			<th>订单号</th>
			<th>订单内容</th>
			<th>成交时间</th>
			<th>金额（元）</th>
			<th>姓名</th>
			<th>用户名</th>
			<th>身份证号</th>
		</tr>
		<c:forEach var="t" items="${requestScope.listOrderMng}">
        <tr>
             <td>${t.uid }</td>
             <td>${t.oid }</td>
             <td>${t.descr }</td>
             <td>${t.commitTime }</td>
             <td>${t.amount }</td>
             <td>${t.name }</td>
             <td>${t.username }</td>
             <td>${t.idCard }</td>
		</tr>
		</c:forEach>
	</table>
	
	
	<table class="layui-table">
	    <tr>
	        <td style="text-align:center">
	            <a href='./ListOrderMngCtrl?page=0'>首 页</a>&nbsp;&nbsp;&nbsp;&nbsp;  
	            <a href='./ListOrderMngCtrl?page=prev'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
	            <a href='./ListOrderMngCtrl?page=next'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
	            <a href='./ListOrderMngCtrl?page=${requestScope.lastPage}'>末 页</a>
	        </td> 
	    </tr>
	</table>
</div>
  


    
