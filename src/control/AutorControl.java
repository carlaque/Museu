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
	
	private LongProperty idProperty = new SimpleLongProperty();
	private StringProperty nomeProperty = new SimpleStringProperty("");
	private StringProperty nacionalidadeProperty = new SimpleStringProperty("");
	
	private ObjectProperty<LocalDate> nascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<LocalDate> falecimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	
	private AutorDAO autorDAO = new AutorDAOImpl();
	
	public Autor getAutor() { 
		Autor a = new Autor();
		a.setId(idProperty.get());
		a.setNome(nomeProperty.get());
		a.setNacionalidade(nacionalidadeProperty.get());
		a.setNascimento(nascimentoProperty.get());
		a.setFalecimento(falecimentoProperty.get());
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
	
	public void adicionar() throws AutorException {
		autorDAO.adicionar(getAutor());
//		getAutores().add(getAutor());
	}
	
	public void pesquisarPorNome() throws AutorException {
		List<Autor> lista = autorDAO.pesquisarPorNome(this.getNomeProperty().get());
		
		this.autores.clear();
		this.autores.addAll(lista);
	}
	
	public void carregar() throws AutorException{
		List<Autor> lista = autorDAO.carregar(this.getNomeProperty().get());
		
		this.autores.clear();
		this.autores.addAll(lista);
	}
	
	public void update() throws AutorException {
		autorDAO.update(getAutor());
	}
	
	public void delete() throws AutorException {
		autorDAO.delete(getAutor().getId());
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
