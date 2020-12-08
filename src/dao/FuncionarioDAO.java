package dao;

import java.util.List;

import entity.Funcionario;
import exceptions.FuncionarioException;

public interface FuncionarioDAO {
	
	void adicionar(Funcionario f) throws FuncionarioException;
	List<Funcionario> pesquisarPorNome(String nome) throws FuncionarioException;
	List<Funcionario> carregar() throws FuncionarioException;
	void update(Funcionario f) throws FuncionarioException;
	void delete(Long id) throws FuncionarioException;
}
