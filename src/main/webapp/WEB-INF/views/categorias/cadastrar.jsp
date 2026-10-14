<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Nova Categoria</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/formulario.css">
    </head>

    <body>
    
    <main class="main-formulario">
        <form class="form" action="${pageContext.request.contextPath}/categorias/salvar" method="post">
            <h1>Nova Categoria</h1>

            <div class="grupo-campo">
                <div class="campo">
                    <label>Nome:</label>
                    <input type="text" name="txtNome" required><br>

                </div>

                <div class="campo">
                    <label>Descrição:</label>
                    <input type="text" name="txtDescricao"><br>
                </div>
             </div>
                <button class="btn-submit" type="submit">Salvar</button>
        </form>

        <a class="btn-link" href="${pageContext.request.contextPath}/categorias">Voltar</a>
	</main>
    </body>

    </html>