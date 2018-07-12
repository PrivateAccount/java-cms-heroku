<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="col-lg-8 offset-lg-2">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />&id=<c:out value="${message.getId()}" />" method="post" class="form">
				<div class="form-title">Szczegóły wiadomości</div>
				<div class="form-group">
					<div class="field-label">Id:</div>
					<input type="text" value="<c:out value="${message.getId()}" />" class="form-control" disabled> 
					<div class="field-label">IP:</div>
					<input type="text" value="<c:out value="${message.getIp()}" />" class="form-control" disabled> 
					<div class="field-label">Nick:</div>
					<input type="text" value="<c:out value="${message.getNick()}" />" class="form-control" disabled> 
					<div class="field-label">Email:</div>
					<input type="text" value="<c:out value="${message.getEmail()}" />" class="form-control" disabled> 
					<div class="field-label">Treść:</div>
					<textarea class="form-control" rows="5" disabled><c:out value="${message.getMessage()}" /></textarea>
					<div class="field-label">Data:</div>
					<input type="text" value="<c:out value="${fn:substringBefore(message.getSent(), '.')}" />" class="form-control" disabled> 
				</div>
				<hr>
				<div class="form-buttons">
					<button type="submit" name="commit" class="btn btn-primary">Zatwierdź</button>
					<button type="submit" name="cancel" class="btn btn-warning">Anuluj</button>
				</div>
			</form>
		</div>
	</div>
</div>
