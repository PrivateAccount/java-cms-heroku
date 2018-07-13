<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
	<c:when test="${page.getId() > 0}">
		<h1 class="page-title"><c:out value="${page.getTitle()}" /></h1>
		<h4 class="page-date">Opublikowano: <c:out value="${fn:substringBefore(page.getModified(), '.')}" /></h4>
		<hr class="page-separator">
		<div class="page-content">
			<section class="article-image">
				<img src="<c:out value="${page.getImage()}" />" alt="article">
			</section>
			<section class="article-intro">
				<c:out value="${page.getIntro()}" escapeXml="false" />
			</section>
			<section class="article-content">
				<c:out value="${page.getContent()}" escapeXml="false" />
			</section>
			<div class="py-3 col-lg-4 offset-lg-4">
				<button class="btn btn-success btn-block" onclick="startManual()">Start</button>
			</div>
		</div>
		<div class="manual-content">
			<div class="col-lg-8 offset-lg-2" id="manual-browser">
				<div id="manual-title">
					Tytuł
				</div>
				<div id="manual-picture">
					<a href="#zoom" onclick="zoomImage()">
						<img src="/img/manual/1.png" id="manual-image" alt="Java Servlets Application">
					</a>
					<img src="/img/loader.gif" id="manual-loader" alt="Java Servlets Loader">
				</div>
				<div id="manual-buttons">
					<button class="btn btn-success" id="manual-prev"> &lt; </button>
					<span id="manual-page">1 z 18</span>
					<button class="btn btn-success" id="manual-next"> &gt; </button>
				</div>
				<div id="manual-description">
					Szczegóły
				</div>
			</div>
			<div class="col-lg-12" id="image-zoom">
				<a href="#zoom" onclick="closeZoom()">
					<img src="/img/manual/1.png" id="zoom-image" alt="Java Servlets Application">
				</a>
			</div>
			<c:forEach items="${data}" var="manual" varStatus="iterator">
				<div style="display: none;">
					<span id="<c:out value="${manual.getIndex()}" />">
						<span class="title"><c:out value="${manual.getTitle()}" escapeXml="false" /></span>
						<span class="description"><c:out value="${manual.getContent()}" escapeXml="false" /></span>
					</span>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<h1 class="page-title">Strona nie znaleziona</h1>
		<a href="/"><h4 class="page-date">Powrót</h4></a>
		<hr class="page-separator">
		<div class="page-content">
			<div class="text-center py-3">
				<img src="/img/page-not-found.png" class="not-found" alt="Page not found">
			</div>
		</div>
	</c:otherwise>
</c:choose>

<style>
	div.manual-content { 
		display: none; 
	}
	div#manual-browser {
		padding: 0 1.8em;
		color: #666;
	}
	div#manual-title {
		font-size: 1.5em;
		margin-bottom: 0.5em;
		text-align: left;
		width: 100%;
	}
	div#manual-picture {
		min-height: 400px;
	}
	img#manual-image {
		width: 100%;
		height: 400px;
		padding: 1px;
		border: 1px solid #ccc;
	}
	img#manual-loader {
		position: fixed;
		left: calc(50% - 20px);
		top: calc(50% - 20px);
		display: none;
	}
	img#zoom-image {
		width: 100%;
		height: auto;
		padding: 1px;
		border: 1px solid #ccc;
	}
	div#manual-buttons {
		margin: 1.0em 0;
		text-align: center;
	}
	span#manual-page {
		width: 100px;
		text-align: center;
		font-weight: bold;
		display: inline-block;
	}
	div#manual-description {
		font-size: 1.0em;
		line-height: 1.25em;
		margin: 1.0em 0;
		text-align: justify;
		width: 100%;
	}
	div#image-zoom {
		display: none;
	}
</style>

<script>
	var pageId = 1;
	const pagesCount = 18;

	function startManual() {
		$('div.page-content').fadeOut(function() {
			$('div.manual-content').fadeIn(function() {
				var title = $('span#manual-' + pageId + ' span.title').html();
				var description = $('span#manual-' + pageId + ' span.description').html();
				$('div#manual-title').html(title);
				$('div#manual-description').html(description);
			});
		});
	}
	
	$('button#manual-prev').unbind('click').on('click', function() {
		if (pageId > 1) pageId--;
		else pageId = pagesCount;
		changeImage($('img#manual-image'), pageId);
	});

	$('button#manual-next').unbind('click').on('click', function() {
		if (pageId < pagesCount) pageId++;
		else pageId = 1;
		changeImage($('img#manual-image'), pageId);
	});

	function changeImage(imageObj, id) {
		var $img = imageObj;
		$img.fadeOut(function() {
			$('img#manual-loader').show();
			$('span#manual-page').text("...");
			$img.attr('src', '/img/manual/' + id + '.png');
			$('img#zoom-image').attr('src', '/img/manual/' + id + '.png');
			$img.on('load', function() {
				$('img#manual-loader').hide();
				$img.fadeIn(function() {
					$('span#manual-page').text(id + ' z ' + pagesCount);
				});
			});
			var title = $('span#manual-' + id + ' span.title').html();
			var description = $('span#manual-' + id + ' span.description').html();
			$('div#manual-title').html(title);
			$('div#manual-description').html(description);
		});
	}

	function zoomImage() {
		$('div#manual-browser').fadeOut(function() {
			$('div#image-zoom').fadeIn();
		});
	}

	function closeZoom() {
		$('div#image-zoom').fadeOut(function() {
			$('div#manual-browser').fadeIn();
		});
	}
</script>