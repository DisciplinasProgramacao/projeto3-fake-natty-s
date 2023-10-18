

package codigo.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import codigo.Cliente;

public class clienteTest {

    private Cliente cliente;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Cliente Teste", "123");
        veiculo1 = new Veiculo("ABC123", "Carro");
        veiculo2 = new Veiculo("DEF456", "Moto");
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
    }

    @Test
    public void testAddVeiculo() {
        Veiculo novoVeiculo = new Veiculo("GHI789", "Caminhão");
        cliente.addVeiculo(novoVeiculo);
        assertEquals(3, cliente.getVeiculos().size());
    }

    @Test
    public void testPossuiVeiculo() {
        Veiculo veiculo = cliente.possuiVeiculo("ABC123");
        assertNotNull(veiculo);
        assertEquals("Carro", veiculo.getTipo());
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
