package Control;

import java.time.LocalDate;

import Entity.Funcionario;
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
	
	public void adicionar() {
		getFuncionarios().add(getFuncionario());
	}
	public void pesquisarPorNome() {
		for (Funcionario f : getFuncionarios()) { 
			if (f.getNome().contains(nomeProperty.get())) { 
				setFuncionario(f);
			}
		}	
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
