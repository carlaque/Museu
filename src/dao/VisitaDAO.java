package dao;

import java.util.List;

import entity.Visita;
import exceptions.VisitaException;

public interface VisitaDAO {
	
	void adicionar(Visita o) throws VisitaException;
	List<Visita> pesquisarPorId(Long id) throws VisitaException;
	List<Visita> pesquisarPorVisitante(Long visitanteId) throws VisitaException;
	List<Visita> pesquisarPorTour(Long tourId) throws VisitaException;
	void update(Visita v) throws VisitaException;
	void delete(Long id) throws VisitaException;
}
