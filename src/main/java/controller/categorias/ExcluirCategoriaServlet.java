package controller.categorias;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CategoriaDAO;

@WebServlet("/categorias/excluir")
public class ExcluirCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
//        	categoriaDAO.excluir(id);
            try {
				categoriaDAO.excluir(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            response.sendRedirect(request.getContextPath() + "/categorias");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir categoria", e);
        }
    }
}
