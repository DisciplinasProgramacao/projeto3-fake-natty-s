package tests;
import src.*;
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
        vaga = new Vaga("Vaga de Teste", 1);
        uso = new UsoDeVaga(vaga, true, false, true);
    }

    @Test
    public void testSair() {
        double valorPago = uso.sair(true, false, true);
        assertEquals(true, valorPago > 0);
    }
   
    @Test
    public void testValorPago() {
        assertEquals(0.0, uso.valorPago(), 0.01);
        double valorPago = uso.sair(true, false, true);
        assertEquals(true, uso.valorPago() > 0);
    }

}
