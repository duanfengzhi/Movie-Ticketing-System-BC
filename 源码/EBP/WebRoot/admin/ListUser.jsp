<%@ page language="java" import="java.util.*, java.sql.*, com.hgd.ebp.domain.*, com.hgd.ebp.util.* " pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="subTitle" scope="request">用户管理</c:set>
<c:set var="mainBody" scope="request">/admin/ListUserBody.jsp</c:set>
<jsp:include page="/WEB-INF/jspf/admin/_layout.jsp" />