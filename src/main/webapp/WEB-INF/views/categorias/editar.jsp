<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Editar Categoria</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
    </head>

    <body>
        
	<main class="main-formulario">
		<form class="form" action="${pageContext.request.contextPath}/categorias/atualizar" method="post">
			<h1>Editar Categoria</h1>
			
			<input type="hidden" name="id" value="${categoria.id}" required>
			
            <div class="grupo-campo">
                <div class="campo">
                    <label>Nome:</label>
                    <input type="text" name="nome" value="${categoria.nome}" required>
                </div>

                <div class="campo">
                    <label>Descrição:</label>
                    <input type="text" name="descricao" value="${categoria.descricao}">
                </div>
            </div>
            <!-- <label>Ativo:</label>
        <input type="text" name="ativo" value="${categoria.ativo}"> -->

            <button class="btn-submit" type="submit">Salvar alterações</button>
        </form>

        <a class="btn-link" href="${pageContext.request.contextPath}/categorias">Voltar</a>
		
	</main>
        
    </body>

    </html>