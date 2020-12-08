package dao;

import java.util.List;

import entity.Tour;
import exceptions.TourException;

public interface TourDAO {
	
	void adicionar(Tour t) throws TourException;
	List<Tour> pesquisarPorId(int idTour) throws TourException;
	List<Tour> carregar() throws TourException;
	void update(Tour t) throws TourException;
	void delete(Long id) throws TourException;
}
