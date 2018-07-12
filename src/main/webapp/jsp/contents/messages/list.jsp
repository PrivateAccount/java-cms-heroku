<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="list-header py-3">
				<span class="list-title">Wiadomości (<c:out value="${data.size()}" />)</span>
				<span class="list-search">
					<form action="/<c:out value="${module}" />?action=search" method="post" class="list-filter">
						<input type="search" id="filter" name="filter" value="<c:out value="${filter}" />" class="form-control">
						<button type="submit" name="search" class="btn btn-primary">Szukaj</button>
						<button type="submit" name="clear" class="btn btn-danger">&times;</button>
					</form>
				</span>
				<span class="list-buttons">
					<a href="/<c:out value="${module}" />?action=remove"><button class="btn btn-success">Usuń wiadomości</button></a>
					<a href="/admin"><button class="btn btn-danger">Zamknij</button></a>
				</span>
			</div>
			<table class="table table-bordered table-striped">
				<tr>
					<th class="text-center">Id</th>
					<th>IP</th>
					<th>Nick</th>
					<th>Email</th>
					<th>Treść</th>
					<th class="text-center">Data</th>
					<th colspan="2" class="text-center">Akcje</th>
				</tr>
				<c:forEach items="${data}" var="message" varStatus="iterator">
					<c:choose>
						<c:when test="${message.getRequest() == true}">
							<c:set var="item_color" value="row-green" />
						</c:when>
						<c:otherwise>
							<c:set var="item_color" value="row-grey" />
						</c:otherwise>
					</c:choose>
					<tr class="${iterator.index % 2 == 0 ? 'even' : 'odd'} ${item_color}">
						<td class="text-center"><c:out value="${message.getId()}" /></td>
						<td><c:out value="${message.getIp()}" /></td>
						<td><c:out value="${message.getNick()}" /></td>
						<td><c:out value="${message.getEmail()}" /></td>
						<td><c:out value="${fn:substring(message.getMessage(), 0, 150)}" />...</td>
						<td class="text-center"><c:out value="${fn:substringBefore(message.getSent(), '.')}" /></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=view&id=<c:out value="${message.getId()}"/>"><button class="btn btn-sm btn-warning">Podgląd</button></a></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=delete&id=<c:out value="${message.getId()}"/>"><button class="btn btn-sm btn-danger">Usuń</button></a></td>
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
