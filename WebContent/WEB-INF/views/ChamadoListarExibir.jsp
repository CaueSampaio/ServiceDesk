<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Chamados Exibir</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<!-- Container Principal -->
	<div id="main" class="container">		
		<h3 class="page-header">${fila.nome}</h3>

		<table class="table table-striped">
			<tr>
				<th>Nome</th>
				<th>Descrição</th>
				<th>Abertura</th>
				<th>Fechamento</th>
				<th>Status</th>
				<th>Tempo</th>
			</tr>
			<c:forEach var="chamado" items="${chamados}">
				<tr>			
					<td>${chamado.id}</td>
					<td>${chamado.descricao}</td>
					<td><fmt:formatDate dateStyle = "short" timeStyle = "short" value = "${chamado.dataAbertura}" /></td>
					<td>${not empty chamado.dataFechamento ? chamado.dataFechamento : '-'}</td>
					<td>${chamado.status}</td>
					<jsp:useBean id="now" class="java.util.Date"/>  
		        	<td><fmt:formatNumber value="${not empty chamado.dataFechamento ? (chamado.dataFechamento.time - chamado.dataAbertura.time)/86400000 : (now.time - chamado.dataAbertura.time)/86400000}" maxFractionDigits="0"/> dias</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>