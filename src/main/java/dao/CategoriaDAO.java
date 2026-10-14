package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import models.Categorias;

public class CategoriaDAO {

	public void salvar(Categorias categoria) throws SQLException {
		String sqlSalvar = "insert into categorias(nome, descricao, ativo) "
				+ "values (?, ?, true)";
		try (
			Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlSalvar)){
			
			comando.setString(1, categoria.getNome());
			comando.setString(2, categoria.getDescricao());
			comando.executeUpdate(); 
			// executeUpdate(): 
			// é utilizado exclusivamente para comando que modificam os dados 
			//ou a estrutura do banco de dados. Retornam um num inteiro, que representa
			// a quantidade de linhas afetadas pela operação no banco de dados
			
			//comando.execute()
			// método mais genérico de todos. Ele pode executar qualquer tipo de instrução SQL.
			// aceita qualquer instrução sql. retorna valor boolean
		}
	}
	
	public void alterar(Categorias categoria) throws SQLException{
		
		String sqlAlterar = "update categorias set nome = ?, descricao = ?  where id = ?";
		
		try (Connection conexao = Conexao.obterConexao();
			
			PreparedStatement comando = conexao.prepareStatement(sqlAlterar)){
			
			comando.setString(1, categoria.getNome());
			comando.setString(2, categoria.getDescricao());
//			comando.setBoolean(3, categoria.getAtivo());
			comando.setInt(3, categoria.getId());
			
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
	
	public Categorias localizarCategoria(int id) throws SQLException {
		String sqlLocalizar = "select * from categorias where id = ?";
		Categorias categoria = new Categorias();
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlLocalizar)){
					comando.setInt(1, id);
			
					ResultSet resultado = comando.executeQuery();
					// executeQUery(): utilizado para fazer consultas ao banco de dados, recuperar ou buscar dados!
					// ( SELECT )
					// retorna um objeto do tipo ResultSet
					
					while (resultado.next()) {
						// next(): percorre linha por linha no objeto de ResultSet vindo do executeQuery()
						categoria.setId(resultado.getInt("id"));
						categoria.setNome(resultado.getString("nome"));
						categoria.setDescricao(resultado.getString("descricao"));
						categoria.setAtivo(resultado.getBoolean("ativo"));
					}
		}
		return categoria;
	}
	
public List<Categorias> listarCategorias() throws SQLException {
		
		List<Categorias> lista = new ArrayList<>();
		
		String sqlLista = "select * from categorias order by nome";
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlLista)){
				
				ResultSet resultado = comando.executeQuery();
				
//				System.out.println("OLAAAAA"+resultado);
				while (resultado.next()) {
					
					Categorias cat = new Categorias();
					cat.setId(resultado.getInt("id"));
					cat.setNome(resultado.getString("nome"));
					cat.setDescricao(resultado.getString("descricao"));
					lista.add(cat);
				}
			}
		return lista;
	}
	
}
