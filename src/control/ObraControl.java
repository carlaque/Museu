package control;

import java.util.List;

import dao.ObraDAO;
import dao.ObraDAOImpl;
import entity.Obra;
import exceptions.ObraException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
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
	
	private LongProperty autorIdProperty = new SimpleLongProperty(0);

	private ObraDAO obraDAO = new ObraDAOImpl();
	
	public Obra getObra() { 
		Obra o = new Obra();
		o.setId(idProperty.get());
		o.setTitulo(tituloProperty.get());
		o.setDescricao(descricaoProperty.get());
		o.setPeriodo(periodoProperty.get());
		o.setAutorId((int) autorIdProperty.get());
		return o;
	}
	
	
	public void setObra(Obra o) { 
		if (o != null)  { 
			idProperty.set(o.getId());
			tituloProperty.set(o.getTitulo());
			descricaoProperty.set(o.getDescricao());
			periodoProperty.set(o.getPeriodo());
			autorIdProperty.set(o.getAutorId());
		}
	}
	
	public void adicionar() throws ObraException {
		obraDAO.adicionar(getObra());
	}
	
	public void pesquisarPorNome()throws ObraException {
		List<Obra> lista = obraDAO.pesquisarPorNome(this.getTituloProperty().get(), (int) this.getAutorIdProperty().get());
		
		this.obras.clear();
		this.obras.addAll(lista);
	}
	
	public void carregar()throws ObraException {
		List<Obra> lista = obraDAO.carregar();
		
		this.obras.clear();
		this.obras.addAll(lista);
	}
	
	public void update() throws ObraException {
		obraDAO.update(getObra());
	}
	
	public void delete() throws ObraException {
		obraDAO.delete(getObra().getId());
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
	
	public LongProperty getAutorIdProperty() {
		return autorIdProperty;
	}

	public ObservableList<Obra> getObras() {
		return obras;
	}

}
