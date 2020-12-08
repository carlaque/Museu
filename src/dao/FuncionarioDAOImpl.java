package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Funcionario;
import exceptions.FuncionarioException;

public class FuncionarioDAOImpl implements FuncionarioDAO {

	public FuncionarioDAOImpl() { 		
	}
	
	@Override
	public void adicionar(Funcionario f) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO funcionario (id, nome, cpf, nascimento, telefone) " + 
			"VALUES (?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, f.getId());
			st.setString(2, f.getNome());
			st.setString(3, f.getCpf());
			st.setDate(4,java.sql.Date.valueOf(f.getNascimento()));
			st.setString(5,  f.getTelefone());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}

	}

	@Override
	public List<Funcionario> pesquisarPorNome(String nome) throws FuncionarioException {
		List<Funcionario> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM funcionario WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setId(rs.getLong("id"));
				f.setNome(rs.getString("nome"));
				f.setCpf(rs.getString("cpf"));
				f.setNascimento(rs.getDate("nascimento").toLocalDate());
				f.setTelefone(rs.getString("telefone"));
				lista.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
		return lista;
	}

	@Override
	public List<Funcionario> carregar() throws FuncionarioException {
		List<Funcionario> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM funcionario";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setId(rs.getLong("id"));
				f.setNome(rs.getString("nome"));
				f.setCpf(rs.getString("cpf"));
				f.setNascimento(rs.getDate("nascimento").toLocalDate());
				f.setTelefone(rs.getString("telefone"));
				lista.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
		return lista;
	}

	@Override
	public void update(Funcionario f) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE funcionario SET nome=?, cpf=?, nascimento=?, telefone=? WHERE id=?"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, f.getNome());
			st.setString(2,  f.getCpf());
			st.setDate(3, java.sql.Date.valueOf(f.getNascimento()));
			st.setString(4, f.getTelefone());
			st.setLong(5, f.getId());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
	}

	@Override
	public void delete(Long id) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "DELETE FROM funcionario WHERE id=?"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
	}

}
