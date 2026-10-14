<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>
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
				<h1>Usuarios</h1>
				<a class="botao-primario" href="${pageContext.request.contextPath}/usuarios/novo">Novo usuário</a>
		</div>

    
	<section class="lista">
		<div class="cabecalho">
			<h2 class="titulo">Lista de usuarios</h2>
		</div>
		
	
	<div class="tabela-container">
		<table class="tabela">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Ativo</th>
                <th>Perfil</th>
                <th>Data Cadastro</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td>${u.nome}</td>
                    <td>${u.email}</td>
                    <td>${u.ativo ? 'Sim':'Não'}</td>
                    <td>${u.perfil}</td>
                    <td>${u.data_cadastro}</td>
                    <td class="acoes">
                        <a href="${pageContext.request.contextPath}/usuarios/editar?id=${u.id}">
                            Editar
                        </a>
						
						
						<c:if test="${u.perfil != 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/usuarios/excluir?id=${u.id}"
                               onclick="return confirm('Deseja realmente excluir esse usuário?');">
                                Excluir
                            </a>
                        </c:if>
                        <c:if test="${not u.ativo}">
                            |
                            <a href="${pageContext.request.contextPath}/usuarios/reativar?id=${u.id}">
                                Ativar
                            </a>
                        </c:if>
                        
                        <c:if test="${u.ativo}">
                            |
                            <a href="${pageContext.request.contextPath}/usuarios/desativar?id=${u.id}">
                                Desativar
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
    

    <c:if test="${empty usuarios}">
        <p>Nenhuma usuario cadastrado.</p>
    </c:if>
	</section>
    
	</main>
	
</body>
</html>