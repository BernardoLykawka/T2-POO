package dados;

public class Filme extends Video {
	private String diretor;
	private double duracao;

	public Filme(int codigo, String titulo, String diretor, double duracao) {
		super(codigo,titulo);
		this.diretor = diretor;
		this.duracao = duracao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	@Override
    public String geraTexto(){
		return "1: " + getCodigo() + " - " + getTitulo() + " - " + getDiretor() + " - " + String.format("%.2f", getDuracao());

	}
	@Override
	public double calculaCusto(){
		return duracao * 0.3;
	}
}
