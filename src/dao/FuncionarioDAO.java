package dao;

import java.util.List;

import entity.Funcionario;
import exceptions.FuncionarioException;

public interface FuncionarioDAO {
	
	void adicionar(Funcionario f) throws FuncionarioException;
	List<Funcionario> pesquisarPorNome(String nome) throws FuncionarioException;

}
