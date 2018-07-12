<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<div class="page-content">
	<section class="article-image">
		<c:if test="${not empty page.getImage()}">
			<img src="<c:out value="${page.getImage()}" />" width="100%" alt="contact">
		</c:if>
	</section>
	<section class="article-intro">
		<c:out value="${page.getIntro()}" escapeXml="false" />
	</section>
	<section class="article-content">
		<div class="row py-3">
			<div class="col-lg-4 text-left">
				<c:out value="${page.getContent()}" escapeXml="false" />
			</div>
			<div class="col-lg-8">
				<form action="/contact" method="post" class="form">
					<div class="form-group text-left">
						<div>Imię:</div>
						<input name="nick" class="form-control" type="text" required>
						<div>E-mail:</div>
						<input name="email" class="form-control" type="email" required>
						<div>Wiadomość:</div>
						<textarea name="message" class="form-control" rows="3" spellcheck="false" required></textarea>
					</div>
					<button type="submit" name="confirm" class="btn btn-primary btn-block">Wyślij wiadomość</button>
				</form>
				<c:if test="${not empty message}">
					<div class="py-3">
						<jsp:include page="message.jsp">
							<jsp:param name="message" value="${message}" />
						</jsp:include>
					</div>
					<script>
						window.scrollTo(0, document.body.scrollHeight);
					</script>
				</c:if>
			</div>
		</div>
	</section>
</div>
