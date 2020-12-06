package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Tour;
import exceptions.TourException;

public class TourDAOImpl implements TourDAO {

	public TourDAOImpl() { 		
	}
	
	@Override
	public void adicionar(Tour v) throws TourException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO tour (id, data, funcionario_id) VALUES (?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, v.getId());
			st.setDate(2,java.sql.Date.valueOf(v.getData()));
			st.setLong(3, v.getIdFuncionario());
			
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TourException(e);
		}

	}

	@Override
	public List<Tour> pesquisarPorId( int id) throws TourException {
		List<Tour> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM tour WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id );
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Tour c = new Tour();
				c.setId(rs.getLong("id"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setIdFuncionario(rs.getLong("funcionario_id"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TourException(e);
		}
		return lista;
	}
	
	@Override
	public List<Tour> carregar() throws TourException {
		List<Tour> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM tour ";
			PreparedStatement st = con.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Tour c = new Tour();
				c.setId(rs.getLong("id"));
				c.setData(rs.getDate("data").toLocalDate());
				c.setIdFuncionario(rs.getLong("funcionario_id"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TourException(e);
		}
		return lista;
	}

}
