<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-lg-6 offset-lg-3">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />" method="post" class="form danger">
				<div class="form-title">Usuwanie użytkownika</div>
				<div class="form-group">
					<p class="dialog-text">Czy na pewno chcesz usunąć następującego użytkownika?</p>
					<input type="hidden" name="id" value="<c:out value="${account.getId()}" />">
					<div class="field-label one-line"><span class="field-head">Login:</span><span class="field-data"><c:out value="${account.getLogin()}" /></span></div>
					<div class="field-label one-line"><span class="field-head">Email:</span><span class="field-data"><c:out value="${account.getEmail()}" /></span></div>
				</div>
				<hr>
				<c:if test="${account.getId() == user.id}">
					<c:set var="button_disabled" value="disabled" />
				</c:if>
				<div class="form-buttons">
					<button type="submit" id="confirm" name="confirm" class="btn btn-danger" <c:out value="${button_disabled}" />>Usuń</button>
					<button type="submit" id="cancel" name="cancel" class="btn btn-warning">Anuluj</button>
				</div>
			</form>
		</div>
	</div>
</div>
