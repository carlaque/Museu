package control;

import java.time.LocalDate;
import java.util.List;

import dao.FuncionarioDAO;
import dao.FuncionarioDAOImpl;
import entity.Funcionario;
import exceptions.FuncionarioException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioControl {
	
	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty nomeProperty = new SimpleStringProperty("");
	private StringProperty cpfProperty = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> nascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty telefoneProperty = new SimpleStringProperty("");
	
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
	
	
	public void setFuncionario(Funcionario f) {
		
		if (f != null)  { 
			idProperty.set(f.getId());
			nomeProperty.set(f.getNome());
			cpfProperty.set(f.getCpf());
			nascimentoProperty.set(f.getNascimento());
			telefoneProperty.set(f.getTelefone());
		}
		
	}
	
	public Funcionario getFuncionario() { 
		Funcionario f = new Funcionario();
		f.setId(idProperty.get());
		f.setNome(nomeProperty.get());
		f.setNascimento(nascimentoProperty.get());
		f.setTelefone(telefoneProperty.get());
		return f;
	}
	
	public void adicionar() throws FuncionarioException {
		funcionarioDAO.adicionar(getFuncionario());
	}
	public void pesquisarPorNome() throws FuncionarioException {
		List<Funcionario> lista = funcionarioDAO.pesquisarPorNome(this.getNomeProperty().get());
		
		this.funcionarios.clear();
		this.funcionarios.addAll(lista);
	}
	
	public void carregar() throws FuncionarioException {
		List<Funcionario> lista = funcionarioDAO.carregar();
		
		this.funcionarios.clear();
		this.funcionarios.addAll(lista);
	}
	
	public void update() throws FuncionarioException {
		Funcionario f = new Funcionario();
		f.setId(idProperty.get());
		f.setNome(nomeProperty.get());
		f.setNascimento(nascimentoProperty.get());
		f.setTelefone(telefoneProperty.get());
		funcionarioDAO.update(f);
	}
	
	public void delete() throws FuncionarioException {
		funcionarioDAO.delete(idProperty.get());
	}
	
	public ObservableList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public LongProperty getIdProperty() {
		return idProperty;
	}

	public StringProperty getNomeProperty() {
		return nomeProperty;
	}

	public StringProperty getCpfProperty() {
		return cpfProperty;
	}

	public ObjectProperty<LocalDate> getNascimentoProperty() {
		return nascimentoProperty;
	}

	public StringProperty getTelefoneProperty() {
		return telefoneProperty;
	}
}
