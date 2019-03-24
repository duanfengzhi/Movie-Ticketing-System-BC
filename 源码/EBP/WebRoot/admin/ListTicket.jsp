<%@ page import="java.util.*, com.hgd.ebp.domain.Ticket" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="subTitle" scope="request">票项管理</c:set>
<c:set var="mainBody" scope="request">/admin/ListTicketBody.jsp</c:set>
<jsp:include page="/WEB-INF/jspf/admin/_layout.jsp" />