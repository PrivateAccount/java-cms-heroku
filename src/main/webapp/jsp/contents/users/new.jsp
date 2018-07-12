<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-lg-4 offset-lg-4">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />" method="post" class="form">
				<div class="form-title">Nowy użytkownik</div>
				<div class="form-group">
					<div class="field-label">Login:</div>
					<input type="text" id="login" name="login" value="<c:out value="${account.getLogin()}" />" class="form-control"> 
					<div class="field-label">Email:</div>
					<input type="text" id="email" name="email" value="<c:out value="${account.getEmail()}" />" class="form-control"> 
					<div class="field-label">Hasło:</div>
					<input type="password" id="password" name="password" value="<c:out value="${account.getPassword()}" />" class="form-control"> 
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
			if ($('input#login').val() && $('input#email').val() && $('input#password').val()) {
				$('input.form-control').attr('disabled', true);
				$('button.btn').attr('disabled', true);
			}
		}, 100);
	}
	setTimeout(function() {
		$('input#login').focus();
	}, 500);
</script>
