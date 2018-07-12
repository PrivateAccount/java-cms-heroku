<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="col-lg-8 offset-lg-2">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />&id=<c:out value="${visitor.getId()}" />" method="post" class="form">
				<div class="form-title">Szczegóły wizyty</div>
				<div class="form-group">
					<div class="field-label">Id:</div>
					<input type="text" value="<c:out value="${visitor.getId()}" />" class="form-control" disabled> 
					<div class="field-label">IP:</div>
					<input type="text" value="<c:out value="${visitor.getVisitorIp()}" />" class="form-control" disabled> 
					<div class="field-label">Referer:</div>
					<input type="text" value="<c:out value="${visitor.getHttpReferer()}" />" class="form-control" disabled> 
					<div class="field-label">Request:</div>
					<input type="text" value="<c:out value="${visitor.getRequestUri()}" />" class="form-control" disabled> 
					<div class="field-label">Data:</div>
					<input type="text" value="<c:out value="${fn:substringBefore(visitor.getVisited(), '.')}" />" class="form-control" disabled> 
				</div>
				<hr>
				<div class="form-buttons">
					<button type="submit" name="cancel" class="btn btn-warning">Anuluj</button>
				</div>
			</form>
		</div>
	</div>
</div>
