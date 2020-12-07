package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;
import exceptions.AutorException;

public class AutorDAOImpl implements AutorDAO {

	public AutorDAOImpl() { 		
	}
	
	@Override
	public void adicionar(Autor a) throws AutorException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO autor (id, nome, nacionalidade, nascimento, falecimento) " + 
			"VALUES (?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, a.getId());
			st.setString(2, a.getNome());
			st.setString(3,  a.getNacionalidade());
			st.setDate(4,java.sql.Date.valueOf(a.getNascimento()));
			st.setDate(5, java.sql.Date.valueOf(a.getFalecimento()));
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AutorException(e);
		}

	}

	@Override
	public List<Autor> pesquisarPorNome(String nome) throws AutorException {
		List<Autor> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM autor WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Autor c = new Autor();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setNacionalidade(rs.getString("nacionalidade"));
				c.setNascimento(rs.getDate("nascimento").toLocalDate());
				c.setFalecimento(rs.getDate("falecimento").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AutorException(e);
		}
		return lista;
	}

	@Override
	public List<Autor> carregar(String nome) throws AutorException {
		List<Autor> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM autor ";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Autor c = new Autor();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setNacionalidade(rs.getString("nacionalidade"));
				c.setNascimento(rs.getDate("nascimento").toLocalDate());
				c.setFalecimento(rs.getDate("falecimento").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AutorException(e);
		}
		return lista;
	}

	@Override
	public void update(Autor a) throws AutorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Long id) throws AutorException {
		// TODO Auto-generated method stub
		
	}

}
