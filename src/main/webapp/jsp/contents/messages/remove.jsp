<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-lg-6 offset-lg-3">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />" method="post" class="form danger">
				<div class="form-title">Usuwanie wiadomości</div>
				<div class="form-group">
					<p class="dialog-text">Czy na pewno chcesz usunąć wszystkie oczekujące wiadomości?</p>
				</div>
				<hr>
				<div class="form-buttons">
					<button type="submit" id="confirm" name="confirm" class="btn btn-danger">Usuń</button>
					<button type="submit" id="cancel" name="cancel" class="btn btn-warning">Anuluj</button>
				</div>
			</form>
		</div>
	</div>
</div>
