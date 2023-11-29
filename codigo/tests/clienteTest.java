package tests;
import src.*;
import src.entities.Cliente;
import src.entities.Estacionamento;
import src.entities.Veiculo;
import src.exceptions.ExcecaoClientejaExistente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class clienteTest {

    private Cliente cliente;
    private Veiculo veiculo1;
    private Veiculo veiculo2;
    private Estacionamento estacionamento;

    @BeforeEach
    public void setUp() throws ExcecaoClientejaExistente {
        cliente = new Cliente("Cliente Teste", "123");
        veiculo1 = new Veiculo("ABC123");
        veiculo2 = new Veiculo("DEF456");
        estacionamento = new Estacionamento("Estacionamento Teste", 10, 10);
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        estacionamento.addCliente(cliente);
    }

    @Test
    public void testAddVeiculo() {
        Veiculo novoVeiculo = new Veiculo("GHI789");
        cliente.addVeiculo(novoVeiculo);
        assertEquals(3, cliente.getVeiculos().size());
    }

    @Test
    public void testPossuiVeiculo() {
        Veiculo veiculoCliente = cliente.possuiVeiculo("ABC123");
        assertEquals(veiculoCliente, veiculo1);
    }

    @Test
    public void testPossuiVeiculoInexistente() {
        Veiculo veiculo = cliente.possuiVeiculo("XYZ999");
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
