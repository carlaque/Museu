package Entity;

import java.time.LocalDate;

public class Autor {
	
	private Long id;
	private String nome;
	private String nacionalidade; 
	private LocalDate nascimento;
	private LocalDate falecimento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public LocalDate getFalecimento() {
		return falecimento;
	}
	public void setFalecimento(LocalDate falecimento) {
		this.falecimento = falecimento;
	}
	
	

}
