package dados;

public class Seriado extends Video {
	private int anoInicio;
	private int anoFim;
	private int numEpisodios;

	public Seriado(int codigo, String titulo, int anoInicio, int anoFim, int numEpisodios) {
		super(codigo,titulo);
		this.anoInicio = anoInicio;
		this.anoFim = anoFim;
		this.numEpisodios = numEpisodios;
	}

	public int getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}

	public int getNumEpisodios() {
		return numEpisodios;
	}

	public void setNumEpisodios(int numEpisodios) {
		this.numEpisodios = numEpisodios;
	}

	public int getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(int anoFim) {
		this.anoFim = anoFim;
	}

	@Override
	public String geraTexto(){
		return getCodigo() + " - " + getTitulo() + " - " + getAnoInicio() + " - " + getAnoFim() + " - "
				+ getNumEpisodios() + " - " + String.format("%.2f", calculaCusto());
	}

	@Override
	public double calculaCusto(){
        return numEpisodios * 0.5;
    }

	public int calculaTempo(){
		return getAnoFim()-getAnoInicio();
	}
}
