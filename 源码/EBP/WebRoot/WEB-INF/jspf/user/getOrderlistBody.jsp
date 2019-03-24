<%@ page language="java" import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<center>	  	  			
	    <table class='list_table'>
	        <tr>
	            <th>序号</th>
	            <th>描述</th>
	            <th>单价</th>
	            <th>数量</th>
	            <th>总价</th>
	            <th>订单号</th>
	        </tr>
	        <c:forEach var="orderlist" items="${ orderlists}">
		        <tr>
		            <td>${orderlist.lid}</td>
		            <td>${orderlist.descr}</td>
		            <td>${orderlist.price}</td>
		            <td>${orderlist.quanity}</td>
		            <td>${orderlist.amount}</td>
		            <td>${orderlist.oid}</td>
		        </tr>
	        </c:forEach>
	      </table>
</center>
