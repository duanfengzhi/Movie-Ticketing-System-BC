<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理员界面</title>
  <link href='<c:url value="/style/layui.css"/>' rel="stylesheet" type="text/css"/>
  <link href='<c:url value="/style/login.css"/>' rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value='/js/common.js'/>" /></script> 
  <script type="text/javascript">
	function   adjustTicketStatus(urlStr)
  {
   $.ajax({
        type     : "get",
        async    : true,
        url      : urlStr,
        dataType : "text",
        cache    : false,
        success  : statusTicketChanging
    });
  }
 </script>
 
 <script type="text/javascript">
 function  adjustUserStatus(urlStr)
  {
   $.ajax({
        type     : "get",
        async    : true,
        url      : urlStr,
        dataType : "text",
        cache    : false,
        success  : statusUserChanging
    });
  }
 </script>




</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">购票系统管理员界面</div>
    

    
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
          ${sessionScope.adminUser.adminname}
      </li>
      <li class="layui-nav-item"><a href='<c:url value="/admin/LogoutCtrl" />' 
      	onClick='return confirm("确认是否退出？")' >退 出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" style="text-align:center">
      	<li class="layui-nav-item" style="margin:10% auto">
      		<a href='<c:url value="/admin/index.jsp" />'>首&nbsp;&nbsp;页</a>
      	</li>
        <li class="layui-nav-item" style="margin:10% auto"><a href='<c:url value="/admin/ListTicketsCtrl" />'>票项管理</a></li>
        <li class="layui-nav-item" style="margin:10% auto"><a href='<c:url value="/admin/ListUserCtrl" />'>用户管理</a></li>
        <li class="layui-nav-item" style="margin:10% auto"><a href='<c:url value="/admin/ListOrderMngCtrl" />'>订单管理</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">
    	<jsp:include page="/WEB-INF/jspf/${mainBody}" />
    </div>
  </div>
  
  <div class="layui-footer">
    &copy; 电影票, 2008 -
	<%= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)%>
  </div>
</div>
</body>
</html>