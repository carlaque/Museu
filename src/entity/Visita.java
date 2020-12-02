package entity;

import java.time.LocalDateTime;

public class Visita {
	
	private Visitante visitante;
	
	private LocalDateTime data;

	public Visitante getVisitante() {
		return visitante;
	}

	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
