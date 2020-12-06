package dao;

import java.util.List;

import entity.Tour;
import exceptions.TourException;

public interface TourDAO {
	
	void adicionar(Tour t) throws TourException;
	List<Tour> pesquisarPorId(int idTour) throws TourException;
	List<Tour> carregar() throws TourException;

}
