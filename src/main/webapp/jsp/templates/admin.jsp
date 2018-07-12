<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="pl">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="MEAN Stack Application. Using MongoDB, Express.js, Angular and Node.js together.">
		<meta name="keywords" content="java servlets, postgresql, heroku, demo application">
		<title>Java Servlets Application on Heroku</title>
		<link href="/css/bootstrap.min.css" rel="stylesheet">
		<link href="/css/font-awesome.min.css" rel="stylesheet">
		<link href="/css/app.css" rel="stylesheet">
		<link href="/img/favicon.ico" rel="shortcut icon" type="image/png">
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
		<script src="/js/app.js"></script>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<a class="navbar-brand" href="/"><img id="page-logo" src="/img/logo.png" alt="Java Servlets Application"><span id="page-brand">JavaCMS</span></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item">
							<c:choose>
								<c:when test="${user.getId() > 0}">
									<a class="nav-link" href="/admin">Admin Panel</a>
								</c:when>
								<c:otherwise>
									<a class="nav-link" href="/login">Logowanie</a>
								</c:otherwise>
							</c:choose>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/manual">Instrukcja</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/contact">Kontakt</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<section id="main-content">
			<div class="container">
				<div class="row py-3">
					<div class="col-lg-12">
						<c:choose>
							<c:when test="${not empty action}">
								<jsp:include page="../contents/${module}/${action}.jsp" />
							</c:when>
							<c:otherwise>
								<jsp:include page="../contents/${module}.jsp" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<c:if test="${not empty message}">
					<div class="row">
						<div class="col-lg-10 offset-lg-1">
							<jsp:include page="../contents/message.jsp">
								<jsp:param name="message" value="${message}" />
							</jsp:include>
						</div>
					</div>
				</c:if>
			</div>
		</section>
		<footer class="bg-dark fixed-bottom">
			<div class="container">
				<p class="text-center">Copyright &copy; 2018 <a href="http://angular-cms.pl/category/11" target="_blank">Andrzej Żukowski</a></p>
			</div>
		</footer>
	</body>
</html>
