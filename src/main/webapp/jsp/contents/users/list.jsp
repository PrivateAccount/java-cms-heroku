<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="list-header py-3">
				<span class="list-title">Użytkownicy (<c:out value="${data.size()}" />)</span>
				<span class="list-search">
					<form action="/<c:out value="${module}" />?action=search" method="post" class="list-filter">
						<input type="search" id="filter" name="filter" value="<c:out value="${filter}" />" class="form-control">
						<button type="submit" name="search" class="btn btn-primary">Szukaj</button>
						<button type="submit" name="clear" class="btn btn-danger">&times;</button>
					</form>
				</span>
				<span class="list-buttons">
					<a href="/<c:out value="${module}" />?action=new"><button class="btn btn-success">Nowy użytkownik</button></a>
					<a href="/admin"><button class="btn btn-danger">Zamknij</button></a>
				</span>
			</div>
			<table class="table table-bordered table-striped">
				<tr>
					<th class="text-center">Id</th>
					<th>Login</th>
					<th>Email</th>
					<th class="text-center">Rejestracja</th>
					<th class="text-center">Logowanie</th>
					<th colspan="3" class="text-center">Akcje</th>
				</tr>
				<c:forEach items="${data}" var="account" varStatus="iterator">
					<c:choose>
						<c:when test="${account.getId() == user.id}">
							<c:set var="item_color" value="row-green" />
						</c:when>
						<c:otherwise>
							<c:set var="item_color" value="row-grey" />
						</c:otherwise>
					</c:choose>
					<tr class="${iterator.index % 2 == 0 ? 'even' : 'odd'} ${item_color}">
						<td class="text-center"><c:out value="${account.getId()}" /></td>
						<td><c:out value="${account.getLogin()}" /></td>
						<td><c:out value="${account.getEmail()}" /></td>
						<td class="text-center"><c:out value="${fn:substringBefore(account.getRegistered(), '.')}" /></td>
						<td class="text-center"><c:out value="${fn:substringBefore(account.getLogged(), '.')}" /></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=edit&id=<c:out value="${account.getId()}"/>"><button class="btn btn-sm btn-info">Edytuj</button></a></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=password&id=<c:out value="${account.getId()}"/>"><button class="btn btn-sm btn-warning">Hasło</button></a></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=delete&id=<c:out value="${account.getId()}"/>"><button class="btn btn-sm btn-danger">Usuń</button></a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty data}">
					<tr>
						<td colspan="8" class="no-results">
							(Brak wyników wyszukiwania)
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
</div>

<script>
	setTimeout(function() {
		$('input#filter').focus();
	}, 500);
</script>
