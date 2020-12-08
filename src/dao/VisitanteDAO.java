package dao;

import java.util.List;

import entity.Visitante;
import exceptions.VisitanteException;

public interface VisitanteDAO {
	
	void adicionar(Visitante v) throws VisitanteException;
	List<Visitante> pesquisarPorNome(String nome) throws VisitanteException;
	List<Visitante> carregar() throws VisitanteException;
	void update(Visitante a) throws VisitanteException;
	void delete(String cpf) throws VisitanteException;
}
