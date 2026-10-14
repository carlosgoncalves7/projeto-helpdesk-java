package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import models.Interacoes;
public class InteracoesDAO {
	
	public void salvar(Interacoes interacao) throws SQLException {
		String sqlSalvar = "insert into interacoes(nome, descricao, ativo) "
				+ "values (?, ?, true)";
		try (
			Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlSalvar)){
			
			comando.setString(1, categoria.getNome());
			comando.setString(2, categoria.getDescricao());
			comando.executeUpdate(); 

		}
	}
	
	public void alterar(Interacoes interacao) throws SQLException{
		
		String sqlAlterar = "update categorias set nome = ?, descricao = ?  where id = ?";
		
		try (Connection conexao = Conexao.obterConexao();
			
			PreparedStatement comando = conexao.prepareStatement(sqlAlterar)){
			
			comando.setString(1, interacao.getNome());
			comando.setString(2, interacao.getDescricao());
//			comando.setBoolean(3, categoria.getAtivo());
			comando.setInt(3, interacao.getId());
			
			comando.executeUpdate();
		}
	}
	
	public void inativar(int id) throws SQLException, ClassNotFoundException {
		String sqlInativar = "update categorias set ativo = false where id = ?";
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlInativar)){
					
			comando.setInt(1, id);
			comando.executeUpdate();
		}
	}
	
	public void reativa(int id) throws SQLException, ClassNotFoundException {
		String sqlReativar = "update categorias set ativo = true where id = ?";
		
		try (
			Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlReativar)) {
			
			comando.setInt(1, id);
			comando.executeUpdate();
			
		}
		
	}
	
	public void excluir(int id) throws SQLException, ClassNotFoundException {
		String sqlExcluir = "delete from categorias where id = ?";
		
		try(
				Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlExcluir)){
			comando.setInt(1, id);
			comando.executeUpdate();
			
		}
	}
	
	public Interacoes localizarCategoria(int id) throws SQLException {
		String sqlLocalizar = "select * from categorias where id = ?";
		Interacoes interacao = new Interacoes();
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlLocalizar)){
					comando.setInt(1, id);
			
					ResultSet resultado = comando.executeQuery();
					// executeQUery(): utilizado para fazer consultas ao banco de dados, recuperar ou buscar dados!
					// ( SELECT )
					// retorna um objeto do tipo ResultSet
					
					while (resultado.next()) {
						// next(): percorre linha por linha no objeto de ResultSet vindo do executeQuery()
						interacao.setId(resultado.getInt("id"));
						interacao.setNome(resultado.getString("nome"));
						interacao.setDescricao(resultado.getString("descricao"));
						interacao.setAtivo(resultado.getBoolean("ativo"));
					}
		}
		return interacao;
	}
	
public List<Interacoes> listarCategorias() throws SQLException {
		
		List<Interacoes> lista = new ArrayList<>();
		
		String sqlLista = "select * from categorias order by nome";
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlLista)){
				
				ResultSet resultado = comando.executeQuery();
				
//				System.out.println("OLAAAAA"+resultado);
				while (resultado.next()) {
					
					Interacoes interacao = new Interacoes();
					interacao.setId(resultado.getInt("id"));
					interacao.setNome(resultado.getString("nome"));
					interacao.setDescricao(resultado.getString("descricao"));
					lista.add(interacao);
				}
			}
		return lista;
	}
	
	
}
