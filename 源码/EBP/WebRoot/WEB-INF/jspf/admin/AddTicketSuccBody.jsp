<%@ page import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>


    <%
        Ticket t = (Ticket)request.getAttribute("ticket");
    %>
    
<h3><font color=blue size=10 >添加新票项成功!</font></h3>
<table class="layui-table" >
	<tr>
		<td>编号:</td>
		<td>${ticket.tid}</td>
     </tr>
       
     <tr>
         <td>描述:</td>
         <td>${ticket.descr}</td>
     </tr>
    
     <tr>
         <td>上映时间: </td>
         <td>${ticket.startTime}</td>
     </tr>
    
     <tr>
         <td>总票数: </td>
         <td>${ticket.amount}</td>
     </tr>
    
     <tr>
         <td>剩余票数: </td>
         <td>${ticket.balance}</td>
     </tr>
     
     <tr>
         <td>单价:</td>
         <td>${ticket.price}</td>
     </tr>
</table>