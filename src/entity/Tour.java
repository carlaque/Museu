package entity;

import java.time.LocalDate;

public class Tour {

	private Long id;
	private LocalDate data;
//	private LocalDate hora;
	private Long idFuncionario;
	
	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
