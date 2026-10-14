<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuario</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
</head>
<body>
	

    <main class="main-formulario">
		<form class="form" action="${pageContext.request.contextPath}/usuarios/atualizar" method="post">
			<h1>Editar Usuario</h1>
			
			<input type="hidden" name="id" value="${usuario.id}" required>
            <div class="grupo-campo">
                <div class="campo">
                    <label>Nome:</label>
                    <input type="text" name="nome" value="${usuario.nome}" required>
                </div>

                <div class="campo">
                    <label>Email:</label>
                    <input type="text" name="email" value="${usuario.email}">
                </div>
            </div>

            <button class="btn-submit" type="submit">Salvar alterações</button>
        </form>

        <a class="btn-link" href="${pageContext.request.contextPath}/usuarios">Voltar</a>
		
	</main>
</body>
</html>