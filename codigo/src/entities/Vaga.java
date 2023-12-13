package src.entities;
import java.io.Serializable;

/**
 * A classe Vaga representa uma vaga de estacionamento em um estacionamento.
 */
public class Vaga implements Serializable {

    private String fila;
    private int numero;
    private boolean disponivel;

    /**
     * Construtor da classe Vaga.
     *
     * @param fila    Uma string representando a fila da vaga.
     * @param numero  Um número inteiro representando o número da vaga.
     */
    public Vaga(String fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.disponivel = true;  
    }

    /**
     * Valida se o carro pode ser estacionado na vaga.
     *
     * @return true se o carro pode ser estacionado, ou false se a vaga já está ocupada.
     */
    public boolean estacionar() {
        if (disponivel) {
            disponivel = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se a vaga está disponível e permite que o usuário saia dela.
     *
     * @return true se o usuário puder sair da vaga, ou false se a vaga estiver vazia.
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
     * Obtém a posição da vaga, representada como uma combinação da fila e número.
     *
     * @return Uma string representando a posição da vaga.
     */
    public String getPosicao() {
        return this.fila + numero;
    }

    /**
     * Verifica se a vaga está disponível.
     *
     * @return true se a vaga estiver disponível, ou false se estiver ocupada.
     */
    public boolean disponivel() {
        return disponivel;
    }
    
}
