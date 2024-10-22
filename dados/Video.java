package dados;

public abstract class Video implements Imprimivel {

	private int codigo;

	private String titulo;

	public Video(int codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
	}


	public abstract String geraTexto();

	public abstract double calculaCusto();

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
