<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">
	<div class="py-4 row">
		<div class="col-lg-4 offset-lg-4">
			<form action="/login" method="post" class="form">
				<div class="form-title">Logowanie</div>
				<div class="form-group">
					<div class="field-label">Login:</div>
					<input type="text" id="login" name="login" class="form-control" required> 
					<div class="field-label">Hasło:</div>
					<input type="password" id="password" name="password" class="form-control" required> 
				</div>
				<hr>
				<button type="submit" class="btn btn-primary btn-block" onclick="lockForm()">Zaloguj</button>
			</form>
		</div>
	</div>
</div>

<script>
	function lockForm() {
		setTimeout(function() {
			if ($('input#login').val() && $('input#password').val()) {
				$('input.form-control').attr('disabled', true);
				$('button.btn').attr('disabled', true);
			}
		}, 100);
		$('p.message').fadeOut();
	}
	setTimeout(function() {
		$('input#login').focus();
	}, 500);
</script>
