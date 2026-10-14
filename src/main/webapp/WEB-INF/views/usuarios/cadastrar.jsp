<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nova Usuario</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
	
	<h1>Nova Usuario</h1>

    <form action="${pageContext.request.contextPath}/usuarios/salvar" method="post">

        <label>Nome:</label>
        <input type="text" name="" required><br>

        <label>Email:</label>
        <input type="text" name=""><br>
        
        <label>Senha:</label>
        <input type="text" name=""><br>
        
        <label>Perfil:</label>
        <input type="text" name=""><br>


        <button type="submit">Salvar</button>
    </form>

    <a href="${pageContext.request.contextPath}/usuarios">Voltar</a>
	
	
</body>
</html>