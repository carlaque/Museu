//package control;
//
//import java.time.LocalDate;
//
//import entity.Funcionario;
//import entity.Tour;
//import javafx.beans.property.LongProperty;
//import javafx.beans.property.ObjectProperty;
//import javafx.beans.property.SimpleLongProperty;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//public class TourControl {
//	
//	private ObservableList<Tour> tours = FXCollections.observableArrayList();
//	
//	private LongProperty idProperty = new SimpleLongProperty(0);
//	private ObjectProperty<LocalDate> dataProperty = new SimpleObjectProperty<>(LocalDate.now());
//	private ObjectProperty<LocalDate> horaProperty = new SimpleObjectProperty<>(LocalDate.now());
//	private ObjectProperty<Funcionario> guiaProperty = new SimpleObjectProperty<>(new Funcionario());
//	
////	private LongProperty visitantesProperty = new SimpleLongProperty(0); 
//	//todo isso na real vai ser definido pelos visitantes 
//	
//	
//public void setTour(Tour t) {
//		if (t != null)  { 
//			idProperty.set(t.getId());
//			
//			dataProperty.set(t.getData());
//			horaProperty.set(t.getHora());
//			guiaProperty.set(t.getGuia());
//		}
//	}
//	
//	public Tour getTour() { 
//		Tour t = new Tour();
//		t.setId(idProperty.get());
//		
//		t.setData(dataProperty.get());
//		t.setHora(horaProperty.get());
//		guiaProperty.set(t.getGuia());
//		return t;
//	}
//	
//	public void adicionar() {
//		getTours().add(getTour());
//	}
//	
//	public void pesquisarPorId() {
//		for (Tour t : getTours()) { 
//			if (t.getId().equals(idProperty.get())) { 
//				setTour(t);
//			}
//		}	
//	}
//
//	public ObservableList<Tour> getTours() {
//		return tours;
//	}
//
//	public LongProperty getIdProperty() {
//		return idProperty;
//	}
//
//	public ObjectProperty<LocalDate> getDataProperty() {
//		return dataProperty;
//	}
//
//	public ObjectProperty<LocalDate> getHoraProperty() {
//		return horaProperty;
//	}
//
//	public ObjectProperty<Funcionario> getGuiaProperty() {
//		return guiaProperty;
//	}
//	
//	
//
//	
//}
