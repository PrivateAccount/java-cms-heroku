<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
	<c:when test="${fn:substringBefore(param.message, '@') == 'ERROR'}">
		<c:set var="message_color" scope="session" value="#c00" />
		<c:set var="background_color" scope="session" value="#fcc" />
		<c:set var="border" scope="session" value="1px solid #f99" />
		<c:set var="present" scope="session" value="true" />
	</c:when>
	<c:when test="${fn:substringBefore(param.message, '@') == 'WARNING'}">
		<c:set var="message_color" scope="session" value="#c60" />
		<c:set var="background_color" scope="session" value="#fed" />
		<c:set var="border" scope="session" value="1px solid #fc0" />
		<c:set var="present" scope="session" value="true" />
	</c:when>
	<c:when test="${fn:substringBefore(param.message, '@') == 'SUCCESS'}">
		<c:set var="message_color" scope="session" value="#060" />
		<c:set var="background_color" scope="session" value="#be6" />
		<c:set var="border" scope="session" value="1px solid #9c6" />
		<c:set var="present" scope="session" value="true" />
	</c:when>
	<c:when test="${fn:substringBefore(param.message, '@') == 'INFORMATION'}">
		<c:set var="message_color" scope="session" value="#069" />
		<c:set var="background_color" scope="session" value="#def" />
		<c:set var="border" scope="session" value="1px solid #bcd" />
		<c:set var="present" scope="session" value="true" />
	</c:when>
	<c:otherwise>
		<c:set var="message_color" scope="session" value="inherit" />
		<c:set var="background_color" scope="session" value="inherit" />
		<c:set var="border" scope="session" value="0px solid #ccc" />
		<c:set var="present" scope="session" value="false" />
    </c:otherwise>
</c:choose>

<c:if test="${present == true}">
	<p class="message" style="color: <c:out value="${message_color}" />; border: <c:out value="${border}" />; background-color: <c:out value="${background_color}" />;">
		<c:out value="${fn:substringAfter(param.message, '@')}" />
	</p>
</c:if>
