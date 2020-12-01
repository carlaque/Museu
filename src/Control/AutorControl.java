package Control;

import java.time.LocalDate;

import Entity.Autor;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AutorControl {
	
	private ObservableList<Autor> autores = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty nomeProperty = new SimpleStringProperty("");
	private StringProperty nacionalidadeProperty = new SimpleStringProperty("");
	
	private ObjectProperty<LocalDate> nascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<LocalDate> falecimentoProperty = new SimpleObjectProperty<>(LocalDate.now());

	
	public Autor getAutor() { 
		Autor a = new Autor();
		a.setId(idProperty.get());
		a.setNome(nomeProperty.get());
		a.setNascimento(nascimentoProperty.get());
		a.setNascimento(falecimentoProperty.get());
		return a;
	}
	
	
	
	public void setAutor(Autor a) { 
		if (a != null)  { 
			idProperty.set(a.getId());
			nomeProperty.set(a.getNome());
			nacionalidadeProperty.set(a.getNacionalidade());
			nascimentoProperty.set(a.getNascimento());
			falecimentoProperty.set(a.getFalecimento());
		}
	}
	
	public void adicionar() {
		getAutores().add(getAutor());
	}
	
	public void pesquisarPorNome() {
		for (Autor a : getAutores()) { 
			if (a.getNome().contains(nomeProperty.get())) { 
				setAutor(a);
			}
		}
	}
	
	
	public LongProperty getIdProperty() {
		return idProperty;
	}
	public StringProperty getNomeProperty() {
		return nomeProperty;
	}
	public StringProperty getNacionalidadeProperty() {
		return nacionalidadeProperty;
	}
	public ObjectProperty<LocalDate> getNascimentoProperty() {
		return nascimentoProperty;
	}
	public ObjectProperty<LocalDate> getFalecimentoProperty() {
		return falecimentoProperty;
	}
	
	public ObservableList<Autor> getAutores() {
		return autores;
	}

}
