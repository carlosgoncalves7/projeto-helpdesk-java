<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categorias</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabelas.css">
</head>
<body>
	<header class="header">
		<nav class="nav">
			<a class="link" href="${pageContext.request.contextPath}/home">Início</a>
		</nav>
	</header>
	
	<main class="main">
		<div class="cabecalho">
				<h1>Categorias</h1>
				<a class="botao-primario" href="${pageContext.request.contextPath}/categorias/nova">Nova categoria</a>
		</div>

    
	<section class="lista">
		<div class="cabecalho">
			<h2 class="titulo">Lista de categorias</h2>
		</div>
		
	
	<div class="tabela-container">
		<table class="tabela">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Ativa</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categorias}">
                <tr>
                    <td>${c.nome}</td>
                    <td>${c.descricao}</td>
                    <td>${c.ativo == '1' ? 'Sim' : 'Não'}</td>
                    <td class="acoes">
                        <a href="${pageContext.request.contextPath}/categorias/editar?id=${c.id}">
                            Editar
                        </a>

                        <c:if test="${c.ativo == '1'}">
                            |
                            <a href="${pageContext.request.contextPath}/categorias/excluir?id=${c.id}"
                               onclick="return confirm('Deseja realmente excluir esta categoria?');">
                                Excluir
                            </a>
                        </c:if>

                        <c:if test="${not c.ativo}">
                            |
                            <a href="${pageContext.request.contextPath}/categorias/reativar?id=${c.id}">
                                Ativar
                            </a>
                        </c:if>
                        
                        <c:if test="${c.ativo}">
                            |
                            <a href="${pageContext.request.contextPath}/categorias/desativar?id=${c.id}">
                                Desativar
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
    

    <c:if test="${empty categorias}">
        <p>Nenhuma categoria cadastrada.</p>
    </c:if>
	</section>
    
	</main>
	
</body>
</html>