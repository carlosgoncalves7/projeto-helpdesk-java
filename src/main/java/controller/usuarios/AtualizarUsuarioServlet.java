package controller.usuarios;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.UsuarioDAO;
import models.Usuario;

@WebServlet("/usuarios/atualizar")
public class AtualizarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String perfil = request.getParameter("perfil");

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setPerfil(perfil);

        try {
//        	usuarioDAO.alterar(usuario);
            try {
            	usuarioDAO.alterar(usuario);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar o usuário", e);
        }
    }
}
