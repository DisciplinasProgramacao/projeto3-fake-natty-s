package tests;
import src.*;
import src.entities.Cliente;
import src.entities.Carro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class clienteTest {

    private Cliente cliente;
    private Carro veiculo1;
    private Carro veiculo2;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Cliente Teste", "123");
        veiculo1 = new Carro("ABC123");
        veiculo2 = new Carro("DEF456");
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
    }

    @Test
    public void testAddVeiculo() {
        Carro novoVeiculo = new Carro("GHI789");
        cliente.addVeiculo(novoVeiculo);
        assertEquals(3, cliente.getVeiculos().size());
    }

    @Test
    public void testPossuiVeiculoInexistente() {
        Carro veiculo = cliente.possuiVeiculo("XYZ999");
        assertNull(veiculo);
    }

    @Test
    public void testTotalDeUsos() {
        int totalUsos = cliente.totalDeUsos();
        assertEquals(0, totalUsos); 
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        double arrecadado = cliente.arrecadadoPorVeiculo("ABC123");
        assertEquals(0.0, arrecadado); 
    }

    @Test
    public void testArrecadadoTotal() {
        double arrecadadoTotal = cliente.arrecadadoTotal();
        assertEquals(0.0, arrecadadoTotal); 
    }

    @Test
    public void testArrecadadoNoMes() {
        double arrecadadoMes = cliente.arrecadadoNoMes(1); 
        assertEquals(0.0, arrecadadoMes);
    }
}
