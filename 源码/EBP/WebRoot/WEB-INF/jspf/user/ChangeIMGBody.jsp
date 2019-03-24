<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div style="center; margin: 80px auto; width:50%;">
	<form action="./ImageUploadCtrl" method="POST" enctype="multipart/form-data" class="layui-form">
		<table class="layui-table" >
			<tr>
			<td>请选择要上传的图片：</td>
			<td><input type="file" name="image" /></td>
			<td><input type="submit" value="上传图片" /></td>
			</tr>
	    </table>
	</form>
</div>
