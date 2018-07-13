<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="list-header py-3">
				<span class="list-title">Strony (<c:out value="${data.size()}" />)</span>
				<span class="list-search">
					<form action="/<c:out value="${module}" />?action=search" method="post" class="list-filter">
						<input type="search" id="filter" name="filter" value="<c:out value="${filter}" />" class="form-control">
						<button type="submit" name="search" class="btn btn-primary">Szukaj</button>
						<button type="submit" name="clear" class="btn btn-danger">&times;</button>
					</form>
				</span>
				<span class="list-buttons">
					<a href="/<c:out value="${module}" />?action=new"><button class="btn btn-success">Nowa strona</button></a>
					<a href="/admin"><button class="btn btn-danger">Zamknij</button></a>
				</span>
			</div>
			<table class="table table-bordered table-striped">
				<tr>
					<th class="text-center">Id</th>
					<th>Indeks</th>
					<th class="text-center">Typ</th>
					<th>Tytuł</th>
					<th>Intro</th>
					<th>Treść</th>
					<th class="text-center">Data</th>
					<th colspan="2" class="text-center">Akcje</th>
				</tr>
				<c:forEach items="${data}" var="site" varStatus="iterator">
					<c:choose>
						<c:when test="${fn:toLowerCase(site.getIndex()) == 'index'}">
							<c:set var="item_icon" value="<img src='img/home.png' title='Strona główna' />" />
						</c:when>
						<c:when test="${fn:toLowerCase(site.getIndex()) == 'contact'}">
							<c:set var="item_icon" value="<img src='img/contact.png' title='Strona kontaktowa' />" />
						</c:when>
						<c:when test="${fn:substringBefore(site.getIndex(), '-') == 'manual'}">
							<c:set var="item_icon" value="<img src='img/manual.png' title='Manual' />" />
						</c:when>
						<c:otherwise>
							<c:set var="item_icon" value="<img src='img/article.png' title='Artykuł' />" />
						</c:otherwise>
					</c:choose>
					<tr class="${iterator.index % 2 == 0 ? 'even' : 'odd'}">
						<td class="text-center"><c:out value="${site.getId()}" /></td>
						<td><c:out value="${site.getIndex()}" /></td>
						<td class="text-center"><c:out value="${item_icon}" escapeXml="false" /></td>
						<td><c:out value="${site.getTitle()}" /></td>
						<td><c:out value="${fn:substring(site.getIntro(), 0, 100)}" />...</td>
						<td><c:out value="${fn:substring(site.getContent(), 0, 150)}" />...</td>
						<td class="text-center"><c:out value="${fn:substringBefore(site.getModified(), '.')}" /></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=edit&id=<c:out value="${site.getId()}"/>"><button class="btn btn-sm btn-info">Edytuj</button></a></td>
						<td class="text-center"><a href="/<c:out value="${module}" />?action=delete&id=<c:out value="${site.getId()}"/>"><button class="btn btn-sm btn-danger">Usuń</button></a></td>
					</tr>
				</c:forEach>
				<c:if test="${empty data}">
					<tr>
						<td colspan="9" class="no-results">
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
