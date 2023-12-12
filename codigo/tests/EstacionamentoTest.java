package tests;
import org.junit.Before;
import org.junit.Test;

import src.entities.Cliente;
import src.entities.Estacionamento;
import src.entities.Veiculo;
import src.exceptions.*;

import static org.junit.Assert.*;

public class EstacionamentoTest {
    private Estacionamento estacionamento;
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
    private Cliente cliente4;
    private Cliente cliente5;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

@Before
    public void setUp() {
        estacionamento = new Estacionamento("Nome do Estacionamento", 5, 10);
        cliente1 = new Cliente("Cliente1", "1");
        cliente2 = new Cliente("Cliente2", "2");
        cliente3 = new Cliente("Cliente3", "3");
        cliente4 = new Cliente("Cliente4", "4");
        cliente5 = new Cliente("Cliente5", "5");
        veiculo1 = new Veiculo("ABC123");
        veiculo2 = new Veiculo("XYZ789");
    }

    
    
    /** 
     * @throws ExcecaoClientejaExistente
     */
    @Test
    public void testAddCliente() throws ExcecaoClientejaExistente {
        estacionamento.addCliente(cliente1);
        assertTrue(estacionamento.getClientes().contains(cliente1));
    }

    @Test
    public void testAddVeiculo() throws ExcecaoClientejaExistente, ExcecaoCadastrarVeiculoExistente {
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        assertTrue(cliente1.getVeiculos().contains(veiculo1));
    }

}
