<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="subTitle" scope="request">实时订票</c:set>
<c:set var="mainBody" scope="request">/user/BookingTicketBody.jsp</c:set>
<c:set var="showGuess" value="true" scope="request" />
<jsp:include page="/WEB-INF/jspf/_layout.jsp" />