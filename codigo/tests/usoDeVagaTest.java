package tests;
import src.*;
import src.entities.Estacionamento;
import src.entities.UsoDeVaga;
import src.entities.Vaga;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class usoDeVagaTest {

    private UsoDeVaga uso;
    private Vaga vaga;
    
    @Before
    public void setUp() {
        Estacionamento estacionamento = new Estacionamento("Estacionamento", 10, 10);
        vaga = new Vaga("Vaga de Teste", 1, estacionamento);
        uso = new UsoDeVaga(vaga);
    }

    @Test
    public void testSair() {
        double valorPago = uso.sair();
        assertEquals(false, valorPago > 0);
    }
   
    @Test
    public void testValorPago() {
        assertEquals(0.0, uso.valorPago(), 0.01);
        double valorPago = uso.sair();
        assertEquals(false, uso.valorPago() > 0);
    }

}
