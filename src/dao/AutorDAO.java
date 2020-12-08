package dao;

import java.util.List;

import entity.Autor;
import exceptions.AutorException;

public interface AutorDAO {
	
	void adicionar(Autor a) throws AutorException;
	List<Autor> pesquisarPorNome(String nome) throws AutorException;
	List<Autor> carregar(String nome) throws AutorException;
	void update(Autor a) throws AutorException;
	void delete(Long id) throws AutorException;

}
