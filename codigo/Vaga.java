import java.util.ArrayList;
import java.util.List;

public class Vaga {

	private String fila;
	private int numero;
	private String id;
	private boolean disponivel;
	private String conversor;
	private List<Vaga> vagas;

	public Vaga(String fila, int numero) {
		this.fila = fila;
		this.numero = numero;
		this.vagas = new ArrayList<Vaga>();
		this.disponivel = true;
		this.conversor = String.valueOf(numero); // conversor de int pra string
		this.id = fila + numero; //

	}

	public boolean estacionar() {
		if (!disponivel) {
			disponivel = false;
			return true;
		} else {
			return false;
		}
	}

	public boolean sair() {
		if (!disponivel) {
			disponivel = true;
			return true;
		} else {
			return false;
		}

	}

	public boolean disponivel() {
		return disponivel;

	}

}
