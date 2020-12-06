package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Visitante;
import exceptions.VisitanteException;

public class VisitanteDAOImpl implements VisitanteDAO {

	public VisitanteDAOImpl() { 		
	}
	
	@Override
	public void adicionar(Visitante o) throws VisitanteException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO visitante (cpf, nome, nascimento) VALUES (?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, o.getCpf());
			st.setString(2, o.getNome());
			st.setDate(3,java.sql.Date.valueOf(o.getNascimento()));
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitanteException(e);
		}

	}

	@Override
	public List<Visitante> pesquisarPorNome(String nome) throws VisitanteException {
		List<Visitante> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM visitante WHERE nome LIKE ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Visitante c = new Visitante();
				c.setCpf(rs.getString("cpf"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getDate("nascimento").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitanteException(e);
		}
		return lista;
	}

	@Override
	public List<Visitante> carregar() throws VisitanteException {
		List<Visitante> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM visitante ";
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Visitante c = new Visitante();
				c.setCpf(rs.getString("cpf"));
				c.setNome(rs.getString("nome"));
				c.setNascimento(rs.getDate("nascimento").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitanteException(e);
		}
		return lista;
	}
}
