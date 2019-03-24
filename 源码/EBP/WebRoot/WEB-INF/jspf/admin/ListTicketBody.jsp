<%@ page import="java.util.*, com.hgd.ebp.domain.Ticket" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="filter_form">

<form action='./ListTicketsCtrl' method='POST'>
	<table class="layui-table" >
		 <tr>
			<td>起始日期: <input type=date  name="begin_time" 
				value="${param.begin_time == null? '2018-04-20' : param.begin_time }" size="20" /></td>
			<td>终止日期: <input type=date  name="end_time" 
				value="${param.end_time == null? '2018-09-03' : param.end_time }" size="20" /></td>
			<td style="color:red"><i>${errMap['date'] == null? '' : errMap['date'] }</i></td>
			<td><input type="submit" class="layui-btn layui-btn-normal" value="查询" size="15" /> </td>
		</tr>
		
		<div style="float:right;">
			<a href='AddTicket.jsp' class="layui-btn layui-btn-normal" >添加票项</a>
		</div>
	</table>
</form>
                 
<table class="layui-table" >
	<tr>
        <th>序号</th>
        <th >描述</th>
        <th>时间</th>
        <th>总票数</th>
        <th>剩余票数</th>
        <th>单价（元)</th>
        <th>状态</th>
        <th>&nbsp;&nbsp;</th>
	</tr>
                       
	<c:forEach var="t" items="${requestScope.listTickets}">
		<tr>
			<td>${t.tid }</td>
			<td>${t.descr }</td>
			<td>${t.startTime }</td>
			<td>${t.amount }</td>
			<td>${t.balance }</td>
			<td>${t.price }</td>
			
			<c:url var="urlStr" value='/admin/UpdateTicketCtrl?tid=${t.tid}'/>
        	<td><span id='ajaxTicketStatus${t.tid}'>${t.status== 0 ?  '已停售':'售票中' }</span></td>
            <td><a href='####'  class="layui-btn layui-btn-normal"   onclick="adjustTicketStatus('${urlStr}')" >
            <span id='ajaxTicketButton${t.tid}' >
	            ${t.status== 0 ?  '售票':'停售' }</span></a>
            <a href='./UpdateTicketsCtrl?tid=${t.tid }' class="layui-btn layui-btn-danger">修改</a>
        	</td>
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
</div>