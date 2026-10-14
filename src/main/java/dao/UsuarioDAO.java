package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bd.Conexao;
import models.Usuario;

public class UsuarioDAO {
	
public Usuario Logar(String email, String senha) throws SQLException {
		
		String sql = "select * from usuarios where email = ? and senha = ?";
		
		try (Connection conexao = Conexao.obterConexao();
				PreparedStatement logar = conexao.prepareStatement(sql)){
				logar.setString(1, email);
				logar.setString(2, senha);
				
				try(ResultSet resultado = logar.executeQuery()){
					if(resultado.next()) {
						Usuario usu = new Usuario();
						usu.setId(resultado.getInt("id"));
						usu.setNome(resultado.getString("nome"));
						usu.setEmail(resultado.getString("email"));
						usu.setPerfil(resultado.getString("perfil"));
						
						return usu;
					}
				}
		}
				
		return null;
		
	}

public void salvar(Usuario usuario) throws SQLException {
	String sqlSalvar = "insert into usuarios(nome, email, senha, perfil, ativo, data_cadastro) "
			+ "values (?, ?, ?, ?, 1, CURRENT_TIMESTAMP)";
	try (
		Connection conexao = Conexao.obterConexao();
		PreparedStatement comando = conexao.prepareStatement(sqlSalvar)){
		
		comando.setString(1, usuario.getNome());
		comando.setString(2, usuario.getEmail());
		comando.setString(3, usuario.getSenha());
		comando.setString(4, usuario.getPerfil());
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

public void alterar(Usuario usuario) throws SQLException, ClassNotFoundException {
	
	String sqlAlterar = "update usuarios set nome = ?, email = ?, perfil = ?  where id = ?";
	
	try (Connection conexao = Conexao.obterConexao();
		
		PreparedStatement comando = conexao.prepareStatement(sqlAlterar)){
		
		
		comando.setString(1, usuario.getNome());
		comando.setString(2, usuario.getEmail());
		comando.setString(3, usuario.getPerfil());
		comando.setInt(4, usuario.getId());
//		comando.setBoolean(3, usuario.getAtivo());

		
		comando.executeUpdate();
	}
}

public void inativar(int id) throws SQLException, ClassNotFoundException {
	String sqlInativar = "update usuarios set ativo = false where id = ?";
	
	try (Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlInativar)){
				comando.setInt(1, id);
				
				comando.executeUpdate();
	}
}

public void reativa(int id) throws SQLException, ClassNotFoundException {
	String sqlReativar = "update usuarios set ativo = true where id = ?";
	
	try (
		Connection conexao = Conexao.obterConexao();
		PreparedStatement comando = conexao.prepareStatement(sqlReativar)) {
		
		comando.setInt(1, id);
		comando.executeUpdate();
		
	}
	
}

public void excluir(int id) throws SQLException, ClassNotFoundException {
	String sqlExcluir = "delete from usuarios where id = ?";
	
	try(
			Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlExcluir)
		){
		comando.setInt(1, id);
		comando.executeUpdate();
		
	}
}

public Usuario LocalizarUsuario(int id) throws SQLException, ClassNotFoundException {
	String sqlLocalizar = "select * from usuarios where id = ?";
	Usuario usuario = new Usuario();
	
	try (Connection conexao = Conexao.obterConexao();
			PreparedStatement comando = conexao.prepareStatement(sqlLocalizar)){
				comando.setInt(1, id);
		
				ResultSet resultado = comando.executeQuery();
				// executeQUery(): utilizado para fazer consultas ao banco de dados, recuperar ou buscar dados!
				// ( SELECT )
				// retorna um objeto do tipo ResultSet
				
				while (resultado.next()) {
					// next(): percorre linha por linha no objeto de ResultSet vindo do executeQuery()
					usuario.setId(resultado.getInt("id"));
					usuario.setNome(resultado.getString("nome"));
					usuario.setEmail(resultado.getString("email"));
					usuario.setAtivo(resultado.getBoolean("ativo"));
				}
	}
	return usuario;
}

public List<Usuario> listarUsuarios() throws SQLException {
	
	List<Usuario> lista = new ArrayList<>();
	
	String sql = "select * from usuarios order by nome";
	
	try (Connection conexao = Conexao.obterConexao();PreparedStatement comando = conexao.prepareStatement(sql)){
			ResultSet resultado = comando.executeQuery();
			
			while(resultado.next()) {
				
				Usuario user = new Usuario();
				user.setId(resultado.getInt("id"));
				user.setNome(resultado.getString("nome"));
				user.setEmail(resultado.getString("email"));
				user.setAtivo(resultado.getBoolean("ativo"));
				user.setPerfil(resultado.getString("perfil"));
				user.setData_cadastro(resultado.getDate("data_cadastro"));
				lista.add(user);
			}
		}
	
	
	return lista;
	
	}

}
