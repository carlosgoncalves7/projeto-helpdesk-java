package controller.usuarios;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import models.Usuario;

@WebServlet("/usuarios/salvar")

public class SalvarUsuarioServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private UsuarioDAO categoriaDAO = new UsuarioDAO();

	/**
	 * @see Quando receber dados atraves de formularios
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("txtNome");
		String email = request.getParameter("txtEmail");
		
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setEmail(email);
		
		try {
			categoriaDAO.salvar(user);
			response.sendRedirect(request.getContextPath() + "/usuarios");
		} catch (SQLException e) {
			throw new ServletException("Erro ao salvar usuario", e);
		}
		
	}


}
