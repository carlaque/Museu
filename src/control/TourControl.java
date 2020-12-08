package control;

import java.time.LocalDate;
import java.util.List;

import dao.TourDAO;
import dao.TourDAOImpl;
import entity.Tour;
import exceptions.TourException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourControl {
	
	private ObservableList<Tour> tours = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty();
	private LongProperty idFuncionarioProperty = new SimpleLongProperty();
	private ObjectProperty<LocalDate> dataProperty = new SimpleObjectProperty<>(LocalDate.now());
	
	private TourDAO tourDAO = new TourDAOImpl();	
	
	public void setTour(Tour t) {
		if (t != null)  { 
			idProperty.set(t.getId());
			dataProperty.set(t.getData());
			idFuncionarioProperty.set(t.getIdFuncionario());
		}
	}
	
	public Tour getTour() { 
		Tour t = new Tour();
		t.setId(idProperty.get());		
		t.setIdFuncionario(idFuncionarioProperty.get());
		t.setData(dataProperty.get());
		return t;
	}
	
	public void adicionar() throws TourException {
		tourDAO.adicionar(getTour());
	}
	
	public void pesquisarPorId() throws TourException {
		List<Tour> lista = tourDAO.pesquisarPorId((int) this.getIdProperty().get());
		
		this.tours.clear();
		this.tours.addAll(lista);
	}
	
	public void carregar() throws TourException {
		List<Tour> lista = tourDAO.carregar();
		
		this.tours.clear();
		this.tours.addAll(lista);
	}
	
	public void update() throws TourException {
		Tour t = new Tour();
		t.setId(idProperty.get());		
		t.setIdFuncionario(idFuncionarioProperty.get());
		t.setData(dataProperty.get());
		tourDAO.update(t);
	}
	
	public void delete() throws TourException {
		tourDAO.delete(idProperty.get());
	}
	
	public ObservableList<Tour> getTours() {
		return tours;
	}

	public LongProperty getIdProperty() {
		return idProperty;
	}

	public LongProperty getIdFuncionarioProperty() {
		return idFuncionarioProperty;
	}

	public ObjectProperty<LocalDate> getDataProperty() {
		return dataProperty;
	}


	
}
