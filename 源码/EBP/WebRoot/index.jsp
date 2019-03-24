<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="subTitle" scope="request">首  页</c:set>
<c:set var="mainBody" scope="request">indexBody.jsp</c:set>
<c:set var="showGuess" value="true" scope="request" />
<jsp:include page="/WEB-INF/jspf/_layout.jsp" />