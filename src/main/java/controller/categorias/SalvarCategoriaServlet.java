package controller.categorias;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import models.Categorias;

@WebServlet("/categorias/salvar")

public class SalvarCategoriaServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	/**
	 * @see Quando receber dados atraves de formularios
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("txtNome");
		String descricao = request.getParameter("txtDescricao");
		
		Categorias cat = new Categorias();
		cat.setNome(nome);
		cat.setDescricao(descricao);
		
		try {
			categoriaDAO.salvar(cat);
			response.sendRedirect(request.getContextPath() + "/categorias");
		} catch (SQLException e) {
			throw new ServletException("Erro ao salvar categoria", e);
		}
		
	}


}
