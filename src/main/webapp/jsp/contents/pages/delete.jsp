<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<div class="col-lg-6 offset-lg-3">
			<form action="/<c:out value="${module}" />?action=<c:out value="${action}" />" method="post" class="form danger">
				<div class="form-title">Usuwanie strony</div>
				<div class="form-group">
					<p class="dialog-text">Czy na pewno chcesz usunąć następującą stronę?</p>
					<input type="hidden" name="id" value="<c:out value="${site.getId()}" />">
					<div class="field-label one-line"><span class="field-head">Indeks:</span><span class="field-data"><c:out value="${site.getIndex()}" /></span></div>
					<div class="field-label one-line"><span class="field-head">Tytuł:</span><span class="field-data"><c:out value="${site.getTitle()}" /></span></div>
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
