package dao;

import java.util.List;

import entity.Obra;
import exceptions.ObraException;

public interface ObraDAO {
	
	void adicionar(Obra o) throws ObraException;
	List<Obra> pesquisarPorNome(String nome, int autorId) throws ObraException;
	List<Obra> carregar() throws ObraException;
	void update(Obra o) throws ObraException;
	void delete(Long id) throws ObraException;
}
