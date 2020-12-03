package control;

import java.time.LocalDate;

import entity.Visitante;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VisitanteControl {
	
	private ObservableList<Visitante> visitantes = FXCollections.observableArrayList();
	
	private StringProperty cpfProperty = new SimpleStringProperty("");
	
	private StringProperty nomeProperty = new SimpleStringProperty("");
	
	private ObjectProperty<LocalDate> nascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());

	public Visitante getVisitante() {
		Visitante v = new Visitante();
		v.setCpf(cpfProperty.get());
		v.setNome(nomeProperty.get());
		v.setNascimento(nascimentoProperty.get());
		return v;
	}
	
	public void setVisitante(Visitante v) {
		if(v != null) {
			cpfProperty.set(v.getCpf());
			nomeProperty.set(v.getNome());
			nascimentoProperty.set(v.getNascimento());
		}
	}
	
	public void adicionar() {
		getVisitantes().add(getVisitante());
	}
	
	public void pesquisarPorNome() {
		getVisitantes().forEach(v -> {
			if (v.getNome().contains(nomeProperty.get())) {
				setVisitante(v);
			}
		});
	}
	
	public ObservableList<Visitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(ObservableList<Visitante> vistantes) {
		this.visitantes = vistantes;
	}

	public StringProperty getCpfProperty() {
		return cpfProperty;
	}

	public void setCpfProperty(StringProperty cpfProperty) {
		this.cpfProperty = cpfProperty;
	}

	public StringProperty getNomeProperty() {
		return nomeProperty;
	}

	public void setNomeProperty(StringProperty nomeProperty) {
		this.nomeProperty = nomeProperty;
	}

	public ObjectProperty<LocalDate> getNascimentoProperty() {
		return nascimentoProperty;
	}

	public void setNascimentoProperty(ObjectProperty<LocalDate> nascimentoProperty) {
		this.nascimentoProperty = nascimentoProperty;
	}
}
