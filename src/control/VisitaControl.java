package control;

import java.time.LocalDate;

import entity.Visita;
import entity.Visitante;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitaControl {
	
	private ObservableList<Visita> visitas = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	
	private ObjectProperty<Visitante> visitanteProperty = new SimpleObjectProperty<>(new Visitante());
	
	private ObjectProperty<LocalDate> dataProperty = new SimpleObjectProperty<>(LocalDate.now());

	public Visita getVisita() {
		Visita v = new Visita();
		v.setId(idProperty.get());
		v.setVisitante(visitanteProperty.get());
		v.setData(dataProperty.get());
		return v;
	}
	
	public void setVisita(Visita v) {
		if(v != null) {
			idProperty.set(v.getId());
			visitanteProperty.set(v.getVisitante());
			dataProperty.set(v.getData());
		}
	}
	
	public void adicionar() {
		getVisitas().add(getVisita());
	}
	
	public void pesquisarPorId() {
		getVisitas().forEach(v -> {
			if (v.getId().equals(idProperty.get())) {
				setVisita(v);
			}
		});
	}

	public ObservableList<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(ObservableList<Visita> visitas) {
		this.visitas = visitas;
	}

	public LongProperty getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(LongProperty idProperty) {
		this.idProperty = idProperty;
	}

	public ObjectProperty<Visitante> getVisitanteProperty() {
		return visitanteProperty;
	}

	public void setVisitanteProperty(ObjectProperty<Visitante> visitanteProperty) {
		this.visitanteProperty = visitanteProperty;
	}

	public ObjectProperty<LocalDate> getDataProperty() {
		return dataProperty;
	}

	public void setDataProperty(ObjectProperty<LocalDate> dataProperty) {
		this.dataProperty = dataProperty;
	}
	
	
}
