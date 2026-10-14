package controller.categorias;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CategoriaDAO;
import models.Categorias;

@WebServlet("/categorias/atualizar")
public class AtualizarCategoriaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Categorias categoria = new Categorias();
        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setDescricao(descricao);

        try {
        	categoriaDAO.alterar(categoria);
//            try {
//				categoriaDAO.salvar(categoria);
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            response.sendRedirect(request.getContextPath() + "/categorias");
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar categoria", e);
        }
    }
}
