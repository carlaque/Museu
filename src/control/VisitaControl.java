package control;

import java.time.LocalDate;
import java.util.List;

import dao.VisitaDAO;
import dao.VisitaDAOImpl;
import entity.Visita;
import exceptions.VisitaException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitaControl {
	
	private ObservableList<Visita> visitas = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private LongProperty idVisitanteProperty = new SimpleLongProperty(0);
	private LongProperty idTourProperty = new SimpleLongProperty(0);
	
	private ObjectProperty<LocalDate> dataProperty = new SimpleObjectProperty<>(LocalDate.now());
	
	private VisitaDAO visitaDAO = new VisitaDAOImpl();

	public Visita getVisita() {
		Visita v = new Visita();
		v.setId(idProperty.get());
		v.setIdVisitante(idVisitanteProperty.get());
		v.setIdTour(idTourProperty.get());
		v.setData(dataProperty.get());
		return v;
	}
	
	public void setVisita(Visita v) {
		if(v != null) {
			idProperty.set(v.getId());
			idVisitanteProperty.set(v.getId());
			idTourProperty.set(v.getId());
			dataProperty.set(v.getData());
		}
	}
	
	public void adicionar() throws VisitaException {
		visitaDAO.adicionar(getVisita());
	}
	
	public void pesquisarPorId() throws VisitaException {
		List<Visita> lista = visitaDAO.pesquisarPorId(  getVisita().getId());
		
		this.visitas.clear();
		this.visitas.addAll(lista);
	}
	
	public void pesquisarPorIdVisitante() throws VisitaException {
		List<Visita> lista = visitaDAO.pesquisarPorVisitante( getVisita().getIdVisitante());
		
		this.visitas.clear();
		this.visitas.addAll(lista);
	}
	
	public void pesquisarPorIdTour() throws VisitaException {
		List<Visita> lista = visitaDAO.pesquisarPorTour( getVisita().getIdTour());
		
		this.visitas.clear();
		this.visitas.addAll(lista);
	}
	
	public void update() throws VisitaException {
		visitaDAO.update(getVisita());
	}
	
	public void delete() throws VisitaException {
		visitaDAO.delete(getVisita().getId());
	}
	
	public ObservableList<Visita> getVisitas() {
		return visitas;
	}

	public LongProperty getIdProperty() {
		return idProperty;
	}

	public LongProperty getIdVisitanteProperty() {
		return idVisitanteProperty;
	}

	public LongProperty getIdTourProperty() {
		return idTourProperty;
	}

	public ObjectProperty<LocalDate> getDataProperty() {
		return dataProperty;
	}

	public VisitaDAO getTourDAO() {
		return visitaDAO;
	}

	
	
}
