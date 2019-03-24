<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h3>添加新电影票项</h3>
<form action='./AddTicketsCtrl' method=POST>
	<table class="layui-table" >
		<tr>
			<td>电影名称:</td>
			<td>
				<input type=text name='descr' value='${param.descr==null ? "" : param.descr}' /><br/>
				<span id="seasonErr" class="err_msg">${errMap['descr'] != null ? errMap['descr'] : "" }
				</span><br/><br/>
            </td>
        </tr>
        
        <tr>
            <td>放映时间:</td>
            <td>
                <input type=text name='startTime' value='${param.startTime==null ? "" : param.startTime}' /><br/>
				<span id="seasonErr" class="err_msg">${errMap['startTime'] != null ? errMap['startTime'] : "" }
				</span><br/><br/>
            </td>
        </tr>
        
        <tr>
            <td>总票数:</td>
            <td>
                <input type=text name='amount' value='${param.amount==null ? "" : param.amount}' /><br/>
				<span id="seasonErr" class="err_msg">${errMap['amount'] != null ? errMap['amount'] : "" }
				</span><br/><br/>
            </td>
        </tr>
        
        <tr>
            <td>单价:</td>
            <td>
                <input type=text name='price' value='${param.price==null ? "" : param.price}' /><br/>
				<span id="seasonErr" class="err_msg">${errMap['price'] != null ? errMap['price'] : "" }
				</span><br/><br/>
            </td>
        </tr>
    </table>
    <div style="margin: 0 30%">
    	<input type=submit value='添加' 	class="layui-btn layui-btn-normal" />
    	<input type=reset value='重置' 	class="layui-btn layui-btn-normal" />
    </div>
</form>

