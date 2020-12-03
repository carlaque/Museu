package control;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import entity.Funcionario;
import entity.Tour;
import entity.Visitante;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourControl {
	
	private ObservableList<Tour> tours = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private ObjectProperty<Set<Visitante>> visitantesProperty = new SimpleObjectProperty<>(new HashSet<Visitante>());
	private ObjectProperty<LocalDate> dataProperty = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<LocalDate> horaProperty = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<Funcionario> guiaProperty = new SimpleObjectProperty<>(new Funcionario());
	
public void setTour(Tour t) {
		if (t != null)  { 
			idProperty.set(t.getId());
			visitantesProperty.set(t.getVisitantes());
			dataProperty.set(t.getData());
			horaProperty.set(t.getHora());
			guiaProperty.set(t.getGuia());
		}
	}
	
	public Tour getTour() { 
		Tour t = new Tour();
		t.setId(idProperty.get());
		t.setVisitantes(visitantesProperty.get());
		t.setData(dataProperty.get());
		t.setHora(horaProperty.get());
		guiaProperty.set(t.getGuia());
		return t;
	}
	
	public void adicionar() {
		getTours().add(getTour());
	}
	
	public void pesquisarPorId() {
		for (Tour t : getTours()) { 
			if (t.getId().equals(idProperty.get())) { 
				setTour(t);
			}
		}	
	}

	public ObservableList<Tour> getTours() {
		return tours;
	}

	public void setTours(ObservableList<Tour> tours) {
		this.tours = tours;
	}

	public LongProperty getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(LongProperty idProperty) {
		this.idProperty = idProperty;
	}

	public ObjectProperty<Set<Visitante>> getVisitantesProperty() {
		return visitantesProperty;
	}

	public void setVisitantesProperty(ObjectProperty<Set<Visitante>> visitantesProperty) {
		this.visitantesProperty = visitantesProperty;
	}

	public ObjectProperty<LocalDate> getDataProperty() {
		return dataProperty;
	}

	public void setDataProperty(ObjectProperty<LocalDate> dataProperty) {
		this.dataProperty = dataProperty;
	}

	public ObjectProperty<LocalDate> getHoraProperty() {
		return horaProperty;
	}

	public void setHoraProperty(ObjectProperty<LocalDate> horaProperty) {
		this.horaProperty = horaProperty;
	}

	public ObjectProperty<Funcionario> getGuiaProperty() {
		return guiaProperty;
	}

	public void setGuiaProperty(ObjectProperty<Funcionario> guiaProperty) {
		this.guiaProperty = guiaProperty;
	}

	
}
