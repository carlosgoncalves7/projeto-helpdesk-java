package controller.usuarios;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.UsuarioDAO;
import models.Usuario;

@WebServlet("/usuarios/editar")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Usuario usuario =null;
            
//            usuario = usuarioDAO.LocalizarUsuario(id);
			try {
				usuario = usuarioDAO.LocalizarUsuario(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (usuario == null) {
                request.setAttribute("erro", "Usuario não encontrada.");
                request.getRequestDispatcher("/WEB-INF/views/erro.jsp")
                       .forward(request, response);
                return;
            }

            request.setAttribute("usuario", usuario);

            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/usuarios/editar.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar categoria", e);
        }
    }
}
