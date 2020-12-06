package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Visita;
import exceptions.VisitaException;

public class VisitaDAOImpl implements VisitaDAO {

	public VisitaDAOImpl() { 		
	}
	
	@Override
	public void adicionar(Visita v) throws VisitaException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO visita (id, visitante_id, tour_id, data) " + 
			"VALUES (?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, v.getId());
			st.setLong(2, v.getIdVisitante());
			st.setLong(3,  v.getIdTour());
			st.setDate(4,java.sql.Date.valueOf(v.getData()));
			
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaException(e);
		}

	}

	@Override
	public List<Visita> pesquisarPorVisitante(Long visitanteId) throws VisitaException {
		List<Visita> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM visita WHERE visitante_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, visitanteId );
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Visita c = new Visita();
				c.setId(rs.getLong("id"));
				c.setIdVisitante(rs.getLong("visitante_id"));
				c.setIdTour(rs.getLong("tour_id"));
				c.setData(rs.getDate("data").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaException(e);
		}
		return lista;
	}
	
	@Override
	public List<Visita> pesquisarPorId(Long id) throws VisitaException {
		List<Visita> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM visita WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id );
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Visita c = new Visita();
				c.setId(rs.getLong("id"));
				c.setIdVisitante(rs.getLong("visitante_id"));
				c.setIdTour(rs.getLong("tour_id"));
				c.setData(rs.getDate("data").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaException(e);
		}
		return lista;
	}

	@Override
	public List<Visita> pesquisarPorTour(Long tourId) throws VisitaException {
		List<Visita> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM visita WHERE tour_id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, tourId );
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Visita c = new Visita();
				c.setId(rs.getLong("id"));
				c.setIdVisitante(rs.getLong("visitante_id"));
				c.setIdTour(rs.getLong("tour_id"));
				c.setData(rs.getDate("data").toLocalDate());
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaException(e);
		}
		return lista;
	}

}
