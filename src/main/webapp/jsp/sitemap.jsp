<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<?xml version="1.0" encoding="UTF-8"?>
	
<urlset
	xmlns="http://www.sitemaps.org/schemas/sitemap/0.9"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.sitemaps.org/schemas/sitemap/0.9
	http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd">
	
<!-- created by Auto Sitemap Generator of Java Servlets Application on Heroku -->

<c:set var="index_time" value="${fn:substringBefore(index.getModified(), '.')}" />
<c:set var="index_full" value="${fn:replace(index_time, \" \", \"T\")}" />

<c:set var="contact_time" value="${fn:substringBefore(contact.getModified(), '.')}" />
<c:set var="contact_full" value="${fn:replace(contact_time, \" \", \"T\")}" />

<c:set var="manual_time" value="${fn:substringBefore(manual.getModified(), '.')}" />
<c:set var="manual_full" value="${fn:replace(manual_time, \" \", \"T\")}" />

<url>
	<loc>https://java-cms.herokuapp.com/</loc>
	<lastmod><c:out value="${index_full}" />+00:00</lastmod>
	<priority>1.00</priority>
</url>
<url>
	<loc>https://java-cms.herokuapp.com/login</loc>
	<lastmod><c:out value="${index_full}" />+00:00</lastmod>
	<priority>0.80</priority>
</url>
<url>
	<loc>https://java-cms.herokuapp.com/manual</loc>
	<lastmod><c:out value="${manual_full}" />+00:00</lastmod>
	<priority>0.80</priority>
</url>
<url>
	<loc>https://java-cms.herokuapp.com/contact</loc>
	<lastmod><c:out value="${contact_full}" />+00:00</lastmod>
	<priority>0.80</priority>
</url>

<c:forEach items="${articles}" var="article">
	<c:set var="date_time" value="${fn:substringBefore(article.getModified(), '.')}" />
	<c:set var="date_full" value="${fn:replace(date_time, \" \", \"T\")}" />
	<url>
		<loc>https://java-cms.herokuapp.com/article/<c:out value="${article.getId()}" /></loc>
		<lastmod><c:out value="${date_full}" />+00:00</lastmod>
		<priority>0.80</priority>
	</url>
</c:forEach>

</urlset>
