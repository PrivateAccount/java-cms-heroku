<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="page-content">
	<section class="article-image">
		<c:if test="${not empty page.getImage()}">
			<img src="<c:out value="${page.getImage()}" />" width="100%" alt="index">
		</c:if>
	</section>
	<section class="article-intro">
		<c:out value="${page.getIntro()}" escapeXml="false" />
	</section>
	<section class="article-content">
		<c:out value="${page.getContent()}" escapeXml="false" />
	</section>
</div>

<c:forEach items="${articles}" var="article" varStatus="iterator">
	<hr class="page-separator">
	<div class="article-item">
		<span class="article-image">
			<img src="<c:out value="${article.getImage()}" />" alt="article">
		</span>
		<span class="article-description">
			<a href="/article/<c:out value="${article.getId()}" />"><h3 class="page-title"><c:out value="${article.getTitle()}" /></h3></a>
			<h4 class="page-date"><c:out value="${fn:substringBefore(article.getModified(), '.')}" /></h4>
			<div class="page-intro"><c:out value="${article.getIntro()}" escapeXml="false" /></div>
		</span>
	</div>
</c:forEach>
