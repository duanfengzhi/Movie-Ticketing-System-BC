<%@ page language="java" import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="page">
<form class="center-div" action="./UpdateUserCtrl" method="POST">
  <div class="layui-form-item" style="margin:20px auto;">
    <label class="layui-form-label">用户名:</label>
    <label class="layui-form-label" style="text-align:left">${sessionScope.User.username}</label>
	<label class="layui-form-label" style="text-align:left">${errMap['username'] != NULL ? errMap['username'] : "" }</label>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label">新密码:</label>
    <div class="layui-input-inline">
      <input type="password" name="password" required placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <label class="err_msg">${errMap['password'] != NULL ? errMap['password'] : "" }</label>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">请再确认一遍密码:</label>
    <div class="layui-input-inline">
      <input type="password" name="password_two" required placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <label class="err_msg">${errMap['password_two'] != NULL ? errMap['password_two'] : "" }</label>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">您的姓名:</label>
    <div class="layui-input-block">
      <input type="text" name="name" required  value="${param.name == NULL ? sessionScope.User.name : param.name}" 
	  placeholder="请输入标题" autocomplete="off" class="layui-input">
	  <label class="err_msg">${errMap['name'] != NULL ? errMap['name'] : "" }</label>
	</div>
  </div>
  

  <label class="layui-form-label">性别</label>
	<div class="layui-input-block">
      <input type="radio" name="gender" value="男" style="display: true;margin:9px,15px;" 
					${sessionScope.User.gender == '男' ? 'checked' : '' }/>男
      <input type="radio" name="gender" value="女" style="display: true;" 
					${sessionScope.User.gender == '女' ? 'checked' : '' }/>女
	</div>
  <label class="err_msg">${errMap['gender'] != NULL ? errMap['gender'] : "" }</label>

  <div class="layui-form-item">
    <label class="layui-form-label">身份证号:</label>
    <div class="layui-input-block">
      <input type="text" name="idCard" required  value="${param.idCard == NULL ? sessionScope.User.idCard : param.idCard}" 
	  placeholder="请输入标题" autocomplete="off" class="layui-input">
	  <label class="err_msg">${errMap['idCard'] != NULL ? errMap['idCard'] : "" }</label>
	</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">通讯地址:</label>
    <div class="layui-input-block">
      <input type="text" name="address" required  value="${param.address == NULL ? sessionScope.User.address : param.address}" 
	  placeholder="请输入标题" autocomplete="off" class="layui-input">
	  <label class="err_msg">${errMap['address'] != NULL ? errMap['address'] : "" }</label>
	</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">手机号:</label>
    <div class="layui-input-block">
      <input type="text" name="telno" required  value="${param.telno == NULL ? sessionScope.User.telno : param.telno}" 
	  placeholder="请输入标题" autocomplete="off" class="layui-input">
	  <label class="err_msg">${errMap['telno'] != NULL ? errMap['telno'] : "" }</label>
	</div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
		<input type=submit value='修改' class="layui-btn layui-btn-normal" />
		<input type=reset value='重置' class="layui-btn layui-btn-normal" />
    </div>
  </div>
	<label class="err_msg">${succ != NULL ? succ : "" }</label>
	<label class="err_msg">${errMap['GLOBAL'] != NULL ? errMap['GLOBAL'] : "" }</label>
</form>
</div>