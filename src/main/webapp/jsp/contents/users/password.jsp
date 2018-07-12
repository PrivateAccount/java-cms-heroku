<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 offset-lg-4">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />&id=<c:out value="${account.getId()}" />" method="post" class="form">
				<div class="form-title">Hasło użytkownika</div>
				<div class="form-group">
					<input type="hidden" name="login" value="<c:out value="${account.getLogin()}" />">
					<input type="hidden" name="email" value="<c:out value="${account.getEmail()}" />">
					<div class="field-label">Nowe hasło:</div>
					<input type="password" id="password" name="password" value="" class="form-control"> 
				</div>
				<hr>
				<div class="form-buttons">
					<button type="submit" id="confirm" name="confirm" class="btn btn-primary" onclick="lockForm()">Zapisz</button>
					<button type="submit" id="cancel" name="cancel" class="btn btn-warning" onclick="lockForm()">Anuluj</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	function lockForm() {
		setTimeout(function() {
			if ($('input#password').val()) {
				$('input.form-control').attr('disabled', true);
				$('button.btn').attr('disabled', true);
			}
		}, 100);
	}
	setTimeout(function() {
		$('input#password').focus();
	}, 500);
</script>
