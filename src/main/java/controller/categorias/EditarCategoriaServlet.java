package controller.categorias;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CategoriaDAO;
import models.Categorias;

@WebServlet("/categorias/editar")
public class EditarCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Categorias categoria=null;
            
            categoria = categoriaDAO.localizarCategoria(id);
//			try {
//				categoria = categoriaDAO.localizarCategoria(id);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

            if (categoria == null) {
                request.setAttribute("erro", "Categoria não encontrada.");
                request.getRequestDispatcher("/WEB-INF/views/erro.jsp")
                       .forward(request, response);
                return;
            }

            request.setAttribute("categoria", categoria);

            RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/categorias/editar.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar categoria", e);
        }
    }
}
