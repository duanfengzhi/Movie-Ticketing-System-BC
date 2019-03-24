<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>影院购票系统</title>
	<meta HTTP-EQUIV=Content-Type content="text/html; charset=utf-8">
	<link href='<c:url value="/style/style.css"/>' rel="stylesheet" type="text/css" />
	<link href='<c:url value="/style/layui.css"/>' rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/common.js'/>" /></script>
</head>
<body>
	<ul class="layui-nav" >
 <%-- <li class="layui-nav-item layui-this"><a href='<c:url value="/index.jsp" />'>首 页</a></li> --%>
	  <li class="layui-nav-item"><a href='<c:url value="/index.jsp" />'>首 页</a></li>
	  <li class="layui-nav-item"><a href='<c:url value="/user/ListTicketsCtrl" />'>电 影 票</a></li>
	  <li class="layui-nav-item"></li>
	</ul>
	
	<c:set var="loginStr">
		<a href='<c:url value="/user/Login.jsp" />' >登录</a>
	</c:set>
	
	<c:set var="loginStr2">
		<a href='<c:url value="/AddUser.jsp" />' >注册</a>
	</c:set>
	
	<c:set var="logoutStr">
		<a href='<c:url value="/user/LogoutCtrl" />' onClick='return confirm("确认是否退出？")' >退 出</a>
	</c:set>
	<c:set var="logoutStr2">
		<a href='<c:url value="/user/ChangeIMG.jsp" />' >更换头像</a>
	</c:set>
	<c:set var="logoutStr3">
		<a href='<c:url value="/user/AddMoney.jsp" />' >账户充值</a>
	</c:set>
	<c:set var="logoutStr4">
		<a href='<c:url value="/user/UpdateUser.jsp" />' >
		<img src="<c:url value="/user/images/${sessionScope.User.username}.png"/>" 
		class="layui-nav-img" style="display:none" onload="this.style.display=''">${sessionScope.User.username}</a>
	</c:set>
	<c:set var="logoutStr5">
		<a href='<c:url value="/user/ListOrdersCtrl" />' >我的订单</a>
	</c:set>
	
	<ul class="layui-nav layui-layout-right" >
		<li class="layui-nav-item">
			${sessionScope.User == null ? loginStr : logoutStr}
		</li>
		<li class="layui-nav-item">
			${sessionScope.User == null ? loginStr2 : logoutStr2}
		</li>
		<li class="layui-nav-item">
			${sessionScope.User == null ? "" : logoutStr3}
		</li>
		<li class="layui-nav-item">
			${sessionScope.User == null ? "" : logoutStr5}
		</li>
		<li class="layui-nav-item">
			${sessionScope.User == null ? "" : logoutStr4}
		</li>
	</ul>
	
	<div class="layui-fluid" style="center;float:center">
	    <jsp:include page="/WEB-INF/jspf/${mainBody}" />
	</div>
	
	<div class="layui-fluid" style="center;float:left;width: 100%;height: 200px;">
	<c:if test="${showGuess}">
            <input type=hidden id='urlRoot' value='<c:url value="/user/" />'/>
            <div id="guess_like" style="margin:auto">
                <div id="guessimg1" style="float:left">
                    <a href='#'><img src='' style="display:none" onload="this.style.display=''"/></a>
                </div>
                <div id="guessimg2" style="float:left">
	                <a href='#'><img src='' style="display:none" onload="this.style.display=''"/></a>
                </div>
                <div id="guessimg3" style="float:left">
	                <a href='#'><img src='' style="display:none" onload="this.style.display=''"/></a>
                </div>
                <div id="guessimg4" style="float:left">
	                <a href='#'><img src='' style="display:none" onload="this.style.display=''"/></a>
                </div>
           </div>
    </c:if>  
	</div>
</body>
</html>
