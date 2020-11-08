package Entity;

public class Obra {
	
	private Long id; 
	private String titulo; 
	private String descrição;
	private String período;
	private Autor autor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescrição() {
		return descrição;
	}
	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public String getPeríodo() {
		return período;
	}
	public void setPeríodo(String período) {
		this.período = período;
	}
	
}
