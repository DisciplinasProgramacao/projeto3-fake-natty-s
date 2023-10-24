package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import src.ManipuladorDeArquivo;

public class Vaga implements Serializable {

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

	/**
	 * Valida se o carro pode ser estacionado na vaga 
	 *
	 * @return true se ele pode estacionar ou false se ele não pode estacionar
	 */	

	public boolean estacionar() {
		if (!disponivel) {
			disponivel = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verifica se a vaga esta disponivel e retorna se o usuario pode sair dela
	 * 
	 * @return true se ele puder sair ou false se a vaga estiver vazia
	 */

	public boolean sair() {
		if (!disponivel) {
			disponivel = true;
			return true;
		} else {
			return false;
		}

	}

/**
	 * Verifica se esta disponivel
	 *
	 * @return true se estiver disponivel e false se não estiver disponivel
	 */

	public boolean disponivel() {
		return disponivel;

	}

}
