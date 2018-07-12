<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />" method="post" class="form">
				<div class="form-title">Nowa strona</div>
				<div class="form-group">
					<div class="field-label">Indeks:</div>
					<input type="text" id="index" name="index" value="<c:out value="${site.getIndex()}" />" class="form-control"> 
					<div class="field-label">Tytuł:</div>
					<input type="text" id="title" name="title" value="<c:out value="${site.getTitle()}" />" class="form-control"> 
					<div class="field-label">Obrazek:</div>
					<input type="text" id="image" name="image" value="<c:out value="${site.getImage()}" />" class="form-control"> 
					<div class="field-label">Intro:</div>
					<textarea id="intro" name="intro" class="form-control" rows="5" spellcheck="false"><c:out value="${site.getIntro()}" /></textarea>
					<div class="field-label">Treść:</div>
					<textarea id="content" name="content" class="form-control" rows="15" spellcheck="false"><c:out value="${site.getContent()}" /></textarea>
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
			if ($('input#index').val() && $('input#title').val() && $('textarea#content').val()) {
				$('input.form-control').attr('disabled', true);
				$('textarea.form-control').attr('disabled', true);
				$('button.btn').attr('disabled', true);
			}
		}, 100);
	}
	setTimeout(function() {
		$('input#index').focus();
	}, 500);
</script>
