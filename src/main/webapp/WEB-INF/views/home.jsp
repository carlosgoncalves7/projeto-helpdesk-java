<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
<title>HelpDesk</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<header class="header-home">
		<div class="portal">
			<p>Portal de Solicitações</p>
		</div>
		<div class="btn-sair">
			<a href="${pageContext.request.contextPath}/logout">Sair</a>
		</div>
	</header>
	<main class="main-home">
		<section class="welcome">
			<div class="perfil">
				<h1>Bem-vindo, ${sessionScope.usuarioLogado.nome}</h1>
    			<p>Perfil: <span>${sessionScope.usuarioLogado.perfil}</span></p>
			</div>
			<div class="solicitacao">
				<c:if test="${sessionScope.usuarioLogado.perfil == 'SOLICITANTE'}">
            		<a href="${pageContext.request.contextPath}/solicitacoes/nova">Nova solicitação</a>
        		</c:if>
			</div>
		</section>
		<section class="acessos">
			<p>Acessos</p>
			<nav>
				<a href="${pageContext.request.contextPath}/solicitacoes">
					<div class="informacoes-acesso">
						<h2>Solicitações</h2>
						<p>Consulte suas solicitações</p>
					</div>
				</a>
				
        		
        		<c:if test="${sessionScope.usuarioLogado.perfil == 'ADMIN'}">
            		<a href="${pageContext.request.contextPath}/categorias">
            			<div class="informacoes-acesso">
							<h2>Categorias</h2>
							<p>Acesse as categorias.</p>
						</div>	
            		</a>
            		<a href="${pageContext.request.contextPath}/usuarios">
            			<div class="informacoes-acesso">
							<h2>Usuários</h2>
							<p>Acesse os usuários.</p>
						</div>
            		</a>
        		</c:if>
        		
			</nav>
		</section>
	</main>

</body>
</html>