package controller.usuarios;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.UsuarioDAO;

@WebServlet("/usuarios/reativar")
public class ReativarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            try {
            	usuarioDAO.reativa(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (SQLException e) {
            throw new ServletException("Erro ao reativar usuario", e);
        }
    }
}
