<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Página de Login</title>
		<!-- Para garantir que a página encontre o ficheiro CSS 
	independentemente de como a URL da 
	Servlet foi chamada, utilize a variável implícita  -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
	</head>

	<body>

		<div class="main-login">
			<form class="form" action="${pageContext.request.contextPath }/login" method="post">
				<h1>HelpDesk</h1>

				<div class="grupo-campos">
					<div class="campo">
						<label>Email</label>
						<input type="text" name="textEmail" required>
					</div>

					<div class="campo">
						<label>Senha</label>
						<input type="text" name="textSenha" required>
					</div>

					<button class="btn-submit" type="submit">Logar</button>

				</div>


			</form>
		</div>



	</body>

	</html>