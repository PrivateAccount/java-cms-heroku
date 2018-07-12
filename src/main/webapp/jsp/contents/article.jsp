<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
	<c:when test="${article.getId() > 0}">
		<h1 class="page-title"><c:out value="${article.getTitle()}" /></h1>
		<h4 class="page-date">Opublikowano: <c:out value="${fn:substringBefore(article.getModified(), '.')}" /></h4>
		<hr class="page-separator">
		<div class="page-content">
			<section class="article-image">
				<img src="<c:out value="${article.getImage()}" />" alt="article">
			</section>
			<section class="article-intro">
				<c:out value="${article.getIntro()}" escapeXml="false" />
			</section>
			<section class="article-content">
				<c:out value="${article.getContent()}" escapeXml="false" />
			</section>
		</div>
	</c:when>
	<c:otherwise>
		<h1 class="page-title">Strona nie znaleziona</h1>
		<a href="/"><h4 class="page-date">Powrót</h4></a>
		<hr class="page-separator">
		<div class="page-content">
			<div class="text-center py-3">
				<img src="/img/page-not-found.png" class="not-found" alt="Page not found">
			</div>
		</div>
	</c:otherwise>
</c:choose>
