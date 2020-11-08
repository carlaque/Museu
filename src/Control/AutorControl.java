package Control;

import java.time.LocalDate;

import Entity.Autor;
import Entity.Obra;
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
		Autor c = new Autor();
		c.setId(idProperty.get());
		c.setNome(nomeProperty.get());
		c.setNascimento(nascimentoProperty.get());
		c.setNascimento(falecimentoProperty.get());
		return c;
	}
	
	
	
	public void setAutor(Autor c) { 
		if (c != null)  { 
			idProperty.set(c.getId());
			nomeProperty.set(c.getNome());
			nacionalidadeProperty.set(c.getNacionalidade());
			nascimentoProperty.set(c.getNascimento());
			falecimentoProperty.set(c.getFalecimento());
		}
	}
	
	public void adicionar() {
		getAutores().add(getAutor());
	}
	
	public void pesquisarPorNome() {
		for (Autor c : getAutores()) { 
			if (c.getNome().contains(nomeProperty.get())) { 
				setAutor(c);
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
