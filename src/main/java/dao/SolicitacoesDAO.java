package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import models.Solicitacoes;

public class SolicitacoesDAO {
	
	public void salvar(Solicitacoes solicitacao) throws SQLException {


		String sqlSalvar = "insert into solicitacoes"
				+ " (titulo, descricao, local_ocorrencia, prioridade, status, data_abertura,"
				+ " data_fechamento, usuario_id, tecnico_id, categoria_id)"
				+ "	VALUES(?, ?, ?, ?, ?, CURRENT_TIMESTAMP"
				+ "?, ?, ?, ?);";
		try (
			Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlSalvar)){
			
			comando.setString(1, solicitacao.getTitulo());
			comando.setString(2, solicitacao.getDescricao());
			comando.setString(3, solicitacao.getLocal_ocorrencia());
			comando.setString(4, solicitacao.getPrioridade());
			comando.setString(5, solicitacao.getStatus());
			comando.setDate(6, solicitacao.getData_fechamento());
			comando.setInt(7, solicitacao.getUsuario_id());
			comando.setInt(8, solicitacao.getTecnico_id());
			comando.setInt(9, solicitacao.getCategoria_id());
			comando.executeUpdate();
		}
	}
	
	public void alterar(Solicitacoes solicitacao) throws SQLException{
		
		String sqlAlterar = "update solicitacoes"
				+ "set titulo= ?, descricao= ?, local_ocorrencia= ?, prioridade= ?', status= ?,"
				+ " data_abertura= ?, data_fechamento= ?,"
				+ " usuario_id= ?, tecnico_id= ?, categoria_id= ?"
				+ "where id= ?;";
		
		try (Connection conexao = Conexao.obterConexao();
			
			PreparedStatement comando = conexao.prepareStatement(sqlAlterar)){
			
			comando.setString(1, solicitacao.getTitulo());
			comando.setString(2, solicitacao.getDescricao());
			comando.setString(3, solicitacao.getLocal_ocorrencia());
			comando.setString(4, solicitacao.getPrioridade());
			comando.setString(5, solicitacao.getStatus());
			comando.setDate(6, solicitacao.getData_abertura());
			comando.setDate(7, solicitacao.getData_fechamento());
			comando.setInt(8, solicitacao.getUsuario_id());
			comando.setInt(9, solicitacao.getTecnico_id());
			comando.setInt(10, solicitacao.getCategoria_id());
			comando.setInt(11, solicitacao.getId());
			
			comando.executeUpdate();
		}
	}
	
	
	public void excluir(int id) throws SQLException, ClassNotFoundException {
		String sqlExcluir = "delete from solicitacoes where id = ?";
		
		try(
				Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlExcluir)){
			comando.setInt(1, id);
			comando.executeUpdate();
			
		}
	}
	
	public Solicitacoes localizarSolicitacao(int id) throws SQLException {
		String sqlLocalizar = "select * from solicitacoes where id = ?";
		Solicitacoes solicitacao = new Solicitacoes();
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlLocalizar)){
					comando.setInt(1, id);
			
					ResultSet resultado = comando.executeQuery();
					
					while (resultado.next()) {

						solicitacao.setId(resultado.getInt("id"));
						solicitacao.setDescricao(resultado.getString("descricao"));
					}
		}
		return solicitacao;
	}
	
public List<Solicitacoes> listarCategorias() throws SQLException {
		
		List<Solicitacoes> lista = new ArrayList<>();
		
		String sqlLista = "select * from solicitacoes order by data_abertura";
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement comando = conexao.prepareStatement(sqlLista)){
				
				ResultSet resultado = comando.executeQuery();
				
//				System.out.println("OLAAAAA"+resultado);
				while (resultado.next()) {
					
					Solicitacoes solicitacao = new Solicitacoes();
					solicitacao.setId(resultado.getInt("id"));
					solicitacao.setTitulo(resultado.getString("titulo"));
					solicitacao.setDescricao(resultado.getString("descricao"));
					solicitacao.setLocal_ocorrencia(resultado.getString("local_ocorrencia"));
					solicitacao.setPrioridade(resultado.getString("prioridade"));
					solicitacao.setStatus(resultado.getString("status"));
					solicitacao.setData_abertura(resultado.getDate("data_abertura"));
					solicitacao.setData_fechamento(resultado.getDate("data_fechamento"));
					solicitacao.setUsuario_id(resultado.getInt("usuario_id"));
					solicitacao.setTecnico_id(resultado.getInt("tecnico_id"));
					solicitacao.setCategoria_id(resultado.getInt("categoria_id"));
					lista.add(solicitacao);
				}
			}
		return lista;
	}
	
	
}
