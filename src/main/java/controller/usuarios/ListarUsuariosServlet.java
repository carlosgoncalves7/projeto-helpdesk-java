package controller.usuarios;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import models.Usuario;
import dao.UsuarioDAO;

@WebServlet("/usuarios")
public class ListarUsuariosServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			List<Usuario> usuarios = null;
			usuarios = usuarioDAO.listarUsuarios();
			
			request.setAttribute("usuarios", usuarios);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/usuarios/listar.jsp");
			dispatcher.forward(request, response);
			
		} catch(SQLException e) {
			
			throw new ServletException("Erro ao listar usuarios", e);
			
		}
	}
}
