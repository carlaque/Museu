package control;

import java.time.LocalDate;
import java.util.List;

import dao.AutorDAO;
import dao.AutorDAOImpl;
import entity.Autor;
import exceptions.AutorException;
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
	
	private AutorDAO autorDAO = new AutorDAOImpl();
	
	public Autor getAutor() { 
		Autor a = new Autor();
		a.setId(idProperty.get());
		a.setNome(nomeProperty.get());
		a.setNascimento(nascimentoProperty.get());
		a.setFalecimento(falecimentoProperty.get());
		return a;
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
	
	public void adicionar() throws AutorException {
		autorDAO.adicionar(getAutor());
//		getAutores().add(getAutor());
	}
	
	public void pesquisarPorNome() throws AutorException {
		List<Autor> contatos = autorDAO.pesquisarPorNome(this.getNomeProperty().get());
		
		this.autores.clear();
		this.autores.addAll(contatos);
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