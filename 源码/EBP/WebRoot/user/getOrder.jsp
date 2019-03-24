<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="subTitle" scope="request">查看订单</c:set>
<c:set var="mainBody" scope="request">/user/getOrderBody.jsp</c:set>
<jsp:include page="/WEB-INF/jspf/_layout.jsp" />