<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Ninja Gold Game</title>
		<link rel="stylesheet" type="text/css" href="/css/styles.css">
	</head>
	<body>
		<main class="container">
			<h1>Tu oro: <span class="gold-box"><c:out value="${oro}"></c:out></span></h1>
			<div class="flex-row">
				<form action="/gold/process" method="post" class="form-box">
					<h3>Farm</h3>
					<p>(earns 10-20 gold)</p>
					<input type="hidden" name="accion" value="farm">
					<button type="submit" class="btn btn-green">Find Gold!</button>
				</form>
				<form action="/gold/process" method="post" class="form-box">
					<h3>Cave</h3>
					<p>(earns 5-10 gold)</p>
					<input type="hidden" name="accion" value="cave">
					<button type="submit" class="btn btn-green">Find Gold!</button>
				</form>
				<form action="/gold/process" method="post" class="form-box">
					<h3>House</h3>
					<p>(earns 2-5 gold)</p>
					<input type="hidden" name="accion" value="house">
					<button type="submit" class="btn btn-green">Find Gold!</button>
				</form>
				<form action="/gold/process" method="post" class="form-box">
					<h3>Casino</h3>
					<p>(earns/takes 0-50 gold)</p>
					<input type="hidden" name="accion" value="casino">
					<button type="submit" class="btn btn-green">Find Gold!</button>
				</form>
				<form action="/gold/process" method="post" class="form-box">
					<h3>Spa</h3>
					<p>(takes 5-20 gold)</p>
					<input type="hidden" name="accion" value="spa">
					<button type="submit" class="btn btn-green">Relax!</button>
				</form>
			</div>
			<div>
				<h2>Activities:</h2>
				<div class="text-box h-300">
					<c:forEach var="action" items="${acciones}">
						<c:choose>
							<c:when test="${action.getValor()>=0}">
								<p class="text-green"><c:out value="${action.getMessage()}" /></p>
							</c:when>
							<c:otherwise>
								<p class="text-red"><c:out value="${action.getMessage()}" /></p>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				<a href="gold/reset">Reiniciar</a>
			</div>
		</main>
		
		
	</body>
</html>