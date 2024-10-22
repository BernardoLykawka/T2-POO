package dados;

public abstract class Video implements Imprimivel {

	private int codigo;

	private String titulo;

	public Video(int codigo, String titulo) {

	}


	/**
	 * @see dados.Imprimivel#geraTexto()
	 */
	public String geraTexto() {
		return null;
	}


	/**
	 * @see dados.Imprimivel#calculaCusto()
	 */
	public double calculaCusto() {
		return 0;
	}

}
