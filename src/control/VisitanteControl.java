package control;

import java.time.LocalDate;
import java.util.List;

import dao.VisitanteDAO;
import dao.VisitanteDAOImpl;
import entity.Visitante;
import exceptions.VisitanteException;
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
	
	private VisitanteDAO visitanteDAO = new VisitanteDAOImpl();

	public Visitante getVisitante() {
		Visitante v = new Visitante();
		v.setCpf(cpfProperty.get());
		v.setNome(nomeProperty.get());
		v.setNascimento(nascimentoProperty.get());
		return v;
	}
	
	public void setVisitante(Visitante v) {
		if(v != null) {
//			cpfProperty.set(v.getCpf());
			nomeProperty.set(v.getNome());
			nascimentoProperty.set(v.getNascimento());
			
			cpfProperty.set(v.getCpf());
		}
	}
	
	public void adicionar() throws VisitanteException {
		visitanteDAO.adicionar(getVisitante());
	}
	
	public void pesquisarPorNome() throws VisitanteException  {
		List<Visitante> lista = visitanteDAO.pesquisarPorNome(this.getNomeProperty().get());
		
		this.visitantes.clear();
		this.visitantes.addAll(lista);
	}
	
	public void carregar() throws VisitanteException  {
		List<Visitante> lista = visitanteDAO.carregar();
		
		this.visitantes.clear();
		this.visitantes.addAll(lista);
	}

	public ObservableList<Visitante> getVisitantes() {
		return visitantes;
	}

	public StringProperty getCpfProperty() {
		return cpfProperty;
	}

	public StringProperty getNomeProperty() {
		return nomeProperty;
	}

	public ObjectProperty<LocalDate> getNascimentoProperty() {
		return nascimentoProperty;
	}
	
	
}
