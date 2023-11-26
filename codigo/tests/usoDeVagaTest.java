package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import src.entities.Cliente;
import src.entities.Estacionamento;
import src.entities.UsoDeVaga;
import src.entities.Vaga;

public class UsoDeVagaTest {

    private UsoDeVaga uso;
    private Vaga vaga;
    private Cliente cliente;

    @Before
    public void setUp() {
        Estacionamento estacionamento = new Estacionamento("Estacionamento", 10, 10);
        vaga = new Vaga("Vaga de Teste", 1);
        cliente = new Cliente("John Doe", "123456"); // Adjust as per your actual ID format
        uso = new UsoDeVaga(vaga);
    }

    @Test
    public void testSair() {
        double valorPago = uso.sair(cliente); // Pass the Cliente parameter
        assertEquals(0.0, valorPago, 0.01); // Assuming the initial value is 0
    }

    @Test
    public void testValorPago() {
        assertEquals(0.0, uso.valorPago(), 0.01); // Assuming the initial value is 0
        double valorPago = uso.sair(cliente); // Pass the Cliente parameter
        assertEquals(0.0, uso.valorPago(), 0.01); // Assuming no payment for the test case
    }

    @Test
    public void testEntrada() {
        assertNotNull(uso.getEntrada());
    }



}