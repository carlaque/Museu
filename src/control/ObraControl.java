package control;

import entity.Autor;
import entity.Obra;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObraControl {
	
	private ObservableList<Obra> obras = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty tituloProperty = new SimpleStringProperty("");
	private StringProperty descricaoProperty = new SimpleStringProperty("");
	private StringProperty periodoProperty = new SimpleStringProperty("");
	
	private ObjectProperty<Autor> autorProperty = new SimpleObjectProperty<>(new Autor());
	

	
	public Obra getObra() { 
		Obra o = new Obra();
		o.setId(idProperty.get());
		o.setTitulo(tituloProperty.get());
		o.setDescricao(descricaoProperty.get());
		o.setPeriodo(periodoProperty.get());
		
		return o;
	}
	
	
	public void setObra(Obra o) { 
		if (o != null)  { 
			idProperty.set(o.getId());
			
			tituloProperty.set(o.getTitulo());
			descricaoProperty.set(o.getDescricao());
			periodoProperty.set(o.getPeriodo());
		}
	}
	
	public void adicionar() {
		getObras().add(getObra());
	}
	
	public void pesquisarPorNome() {
		for (Obra o : getObras()) { 
			if (o.getTitulo().contains(tituloProperty.get())) { 
				setObra(o);
			}
		}
	}
	
	
	
	public LongProperty getIdProperty() {
		return idProperty;
	}


	public StringProperty getTituloProperty() {
		return tituloProperty;
	}


	public StringProperty getDescricaoProperty() {
		return descricaoProperty;
	}


	public StringProperty getPeriodoProperty() {
		return periodoProperty;
	}


	public ObjectProperty<Autor> getAutorProperty() {
		return autorProperty;
	}


	public ObservableList<Obra> getObras() {
		return obras;
	}

}
