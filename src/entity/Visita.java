package entity;

import java.time.LocalDate;

public class Visita {
	// o ato de visitar 

	private Long id;
	private Long idVisitante;
	private Long idTour;
	private LocalDate data;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdVisitante() {
		return idVisitante;
	}
	public void setIdVisitante(Long idVisitante) {
		this.idVisitante = idVisitante;
	}
	public Long getIdTour() {
		return idTour;
	}
	public void setIdTour(Long idTour) {
		this.idTour = idTour;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
		

}
