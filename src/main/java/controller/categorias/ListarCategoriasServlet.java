package controller.categorias;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CategoriaDAO;
import models.Categorias;

@WebServlet("/categorias")
public class ListarCategoriasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Categorias> categorias=null;
            categorias = categoriaDAO.listarCategorias();
//			try {
//				categorias = categoriaDAO.listarCategorias();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            request.setAttribute("categorias", categorias);

            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/categorias/listar.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao listar categorias", e);
        }
    }
}
