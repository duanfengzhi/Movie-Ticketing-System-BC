<%@ page import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="filter_form">
    <form action="./UpdateTicketsCtrl" method="POST">
    <div style="margin:10 auto;text-align:center"><h2>修改票项</h2></div>
    <table class="layui-table">
    <tr>
    <td>编号:</td><td><input type="text"  name="tid" value="${updateTicket.tid}" size="20" /></td>
    </tr>
    <tr>
       <td>描述:</td><td><input type="text"  name="descr" value="${updateTicket.descr}" size="20" /> </td>
    </tr>
    <tr>
    <td>时间:</td> <td><input type="text"  name="startTime" value="${updateTicket.startTime}" size="20" /> </td>
    </tr>
    <tr>
    <td>总票数:</td> <td><input type="text"  name="amount" value="${updateTicket.amount}" size="20" /> </td>
    </tr>
    <tr>
    <td>剩余票数:</td><td><input type="text"  name="balance" value="${updateTicket.balance}" size="20" /> </td>
    </tr>
    <tr>
    <td>单价:</td><td><input type="text"  name="price" value=" ${updateTicket.price}" size="20" /></td>
    </tr>
    
    </table>
    <p><input type=submit value='修改'  class="layui-btn layui-btn-danger"/></p>
     </form>
</div>
