<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1 class="page-title">Admin Panel</h1>

<div class="container">
	<div class="py-4 row">
		<div class="col-lg-6">
			<div class="panel-field">Zalogowano jako: <span class="panel-data"><c:out value="${user.getLogin()}" /></span></div>
			<div class="panel-field">Adres e-mail: <span class="panel-data"><c:out value="${user.getEmail()}" /></span></div>
			<div class="panel-field">Ostatnie logowanie: <span class="panel-data"><c:out value="${fn:substringBefore(user.getLogged(), '.')}" /></span></div>
		</div>
		<div class="col-lg-3">
			<div class="panel-heading">Wiadomości</div>
			<div class="panel-value"><c:out value="${messagesCount}" /></div>
		</div>
		<div class="col-lg-3">
			<div class="panel-heading">Odwiedziny</div>
			<div class="panel-value"><c:out value="${visitorsCount}" /></div>
		</div>
	</div>
	<div class="py-4 row">
		<div class="col-lg-4 offset-lg-4">
			<div class="item-button"><a href="/pages"><button class="btn btn-primary btn-block">Strony</button></a></div>
			<div class="item-button"><a href="/users"><button class="btn btn-primary btn-block">Użytkownicy</button></a></div>
			<div class="item-button"><a href="/visitors"><button class="btn btn-primary btn-block">Odwiedziny</button></a></div>
			<div class="item-button"><a href="/messages"><button class="btn btn-primary btn-block">Wiadomości</button></a></div>
			<div class="item-button"><a href="/logout"><button class="btn btn-danger btn-block">Wyloguj</button></a></div>
		</div>
	</div>
</div>
