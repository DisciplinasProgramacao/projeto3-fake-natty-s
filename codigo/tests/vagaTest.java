package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import src.entities.Vaga;





public class vagaTest {

    private Vaga vaga;

    /**
     * Configuração inicial antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        vaga = new Vaga("A", 1);
    }

    /**
     * Testa o método estacionar() da classe Vaga.
     * 
     *  Testa o estacionamento normal em uma vaga disponível.
     *  Testa tentar estacionar em uma vaga já ocupada.
     *  Testa estacionar em uma vaga vazia após o carro ter saído.
     */
    @Test
    public void testEstacionarCarro() {
        assertTrue(vaga.estacionar());
        assertFalse(vaga.estacionar());
        vaga.sair();
        assertTrue(vaga.estacionar());
    }

    /**
     * Testa o método sair() da classe Vaga.
     * 
     *  Testa sair de uma vaga após estacionar.
     *  Testa tentar sair de uma vaga vazia.
     *  Testa tentar sair de uma vaga que já foi liberada.
     */
    @Test
    public void testSairCarro() {
        vaga.estacionar();
        assertTrue(vaga.sair());
        assertFalse(vaga.sair());
        vaga.sair();
        assertFalse(vaga.sair());
    }

    /**
     * Testa o método getPosicao() da classe Vaga.
     * 
     *  Testa obter a posição de uma vaga normal.
     *  Testa obter a posição de uma vaga sem número.
     *  Testa obter a posição de uma vaga com número alto.
     */
    @Test
    public void testGetPosicao() {
        assertEquals("A1", vaga.getPosicao());
        Vaga vagaSemNumero = new Vaga("B", 0);
        assertEquals("B0", vagaSemNumero.getPosicao());
        Vaga vagaComNumeroAlto = new Vaga("C", 999);
        assertEquals("C999", vagaComNumeroAlto.getPosicao());
    }

    /**
     * Testa o método disponivel() da classe Vaga.
     * 
     *  Testa a disponibilidade inicial de uma vaga.
     *  Testa a disponibilidade após estacionar um carro.
     *  Testa a disponibilidade após sair de uma vaga.
     */
    @Test
    public void testDisponivel() {
        assertTrue(vaga.disponivel());
        vaga.estacionar();
        assertFalse(vaga.disponivel());
        vaga.sair();
        assertTrue(vaga.disponivel());
    }
}

    
