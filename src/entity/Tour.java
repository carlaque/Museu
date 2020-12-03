package entity;

import java.time.LocalDate;
import java.util.Set;

public class Tour {

	private Long id;

	private Set<Visitante> visitantes;
	
	private LocalDate data;
	
	private LocalDate hora;
	
	private Funcionario guia;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Set<Visitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(Set<Visitante> visitantes) {
		this.visitantes = visitantes;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getHora() {
		return hora;
	}

	public void setHora(LocalDate hora) {
		this.hora = hora;
	}

	public Funcionario getGuia() {
		return guia;
	}

	public void setGuia(Funcionario guia) {
		this.guia = guia;
	}
	
}
