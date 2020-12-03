package dao;

import java.util.List;

import entity.Obra;
import exceptions.ObraException;

public interface ObraDAO {
	
	void adicionar(Obra o) throws ObraException;
	List<Obra> pesquisarPorNome(String nome, int autorId) throws ObraException;

}
