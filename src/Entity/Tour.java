package Entity;

import java.time.LocalDateTime;
import java.util.Set;

public class Tour {
	
	private Set<Visitante> visitantes;
	
	private LocalDateTime data;
	
	private LocalDateTime hora;
	
	private Funcionario guia;

	public Set<Visitante> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(Set<Visitante> visitantes) {
		this.visitantes = visitantes;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public Funcionario getGuia() {
		return guia;
	}

	public void setGuia(Funcionario guia) {
		this.guia = guia;
	}
	
}
