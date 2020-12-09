package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Obra;
import exceptions.ObraException;

public class ObraDAOImpl implements ObraDAO {

	public ObraDAOImpl() { 		
	}
	
	@Override
	public void adicionar(Obra o) throws ObraException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO obra (id, titulo, descricao, periodo, autor_id) " + 
			"VALUES (?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, o.getId());
			st.setString(2, o.getTitulo());
			st.setString(3,  o.getDescricao());
			st.setString(4,  o.getPeriodo());
			st.setLong(5,  o.getAutorId());
			
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ObraException(e);
		}

	}

	@Override
	public List<Obra> pesquisarPorNome(String nome, int autorId) throws ObraException {
		List<Obra> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM obra WHERE titulo LIKE ? AND autor_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			st.setInt(2, autorId );
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Obra c = new Obra();
				c.setId(rs.getLong("id"));
				c.setTitulo(rs.getString("titulo"));
				c.setDescricao(rs.getString("descricao"));
				c.setPeriodo(rs.getString("periodo"));
				c.setAutorId(rs.getInt("autor_id"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ObraException(e);
		}
		return lista;
	}

	@Override
	public List<Obra> carregar(int autorId) throws ObraException {
		List<Obra> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM obra WHERE autor_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, autorId );
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Obra c = new Obra();
				c.setId(rs.getLong("id"));
				c.setTitulo(rs.getString("titulo"));
				c.setDescricao(rs.getString("descricao"));
				c.setPeriodo(rs.getString("periodo"));
				c.setAutorId(rs.getInt("autor_id"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ObraException(e);
		}
		return lista;
	}
	
	@Override
	public void update(Obra o) throws ObraException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE obra SET titulo=?, descricao=?, periodo=?, autor_id=? WHERE id=?"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, o.getTitulo());
			st.setString(2,  o.getDescricao());
			st.setString(3,o.getPeriodo());
			st.setLong(4, o.getAutorId());
			st.setLong(5, o.getId());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ObraException(e);
		}
	}

	@Override
	public void delete(Long id) throws ObraException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "DELETE FROM obra WHERE id=?"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ObraException(e);
		}
	}
}
