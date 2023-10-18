package tests;
import src.*;

import org.junit.Before;
import org.junit.Test;
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

    /* 
    @Test
    public void testAddCliente() {
        estacionamento.addCliente(cliente1);
        assertTrue(estacionamento.getClientes().contains(cliente1));
    }*/

    @Test
    public void testAddVeiculo() {
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        assertTrue(cliente1.getVeiculos().contains(veiculo1));
    }

    @Test
    public void testEstacionar() {
        estacionamento.estacionar("ABC123");
        assertEquals(4, estacionamento.getValorArrecadado(), 0.01);
    }

    @Test
    public void testSair() {
        estacionamento.estacionar("ABC123");
        estacionamento.sair("ABC123");
        assertEquals(0, estacionamento.getValorArrecadado(), 0.01);
    }

    @Test
    public void testTotalArrecadado() {
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        estacionamento.estacionar("ABC123");
        assertEquals(4, estacionamento.totalArrecadado(), 0.01);
    }

    @Test
    public void testArrecadacaoNoMes() {
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        estacionamento.estacionar("ABC123");
        // Assumindo que o mês atual é janeiro (mês 0 no Java)
        assertEquals(4, estacionamento.arrecadacaoNoMes(0), 0.01);

    }

    @Test
    public void testValorMedioPorUso() {
        estacionamento.addCliente(cliente1);
        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        estacionamento.estacionar("ABC123");
        assertEquals(4, estacionamento.valorMedioPorUso(), 0.01);
    }

    /*
    @Test
    public void testTop5Clientes() {
        // Adicione clientes e estacionamentos aqui
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente4);
        estacionamento.addCliente(cliente5);

        // Defina as métricas de uso dos clientes (totalDeUsos) conforme necessário

        // Classifique os clientes de acordo com a métrica (totalDeUsos) antes de chamar
        // top5Clientes

        String top5 = estacionamento.top5Clientes();

        // Verifique se o resultado contém os nomes dos cinco principais clientes
        assertTrue(top5.contains("Cliente1"));
        assertTrue(top5.contains("Cliente2"));
        assertTrue(top5.contains("Cliente3"));
        assertTrue(top5.contains("Cliente4"));
        assertTrue(top5.contains("Cliente5"));

        // Certifique-se de que a ordem está correta (os mais usados primeiro)
        int pos1 = top5.indexOf("Cliente1");
        int pos2 = top5.indexOf("Cliente2");
        int pos3 = top5.indexOf("Cliente3");
        int pos4 = top5.indexOf("Cliente4");
        int pos5 = top5.indexOf("Cliente5");
        assertTrue(pos1 < pos2);
        assertTrue(pos2 < pos3);
        assertTrue(pos3 < pos4);
        assertTrue(pos4 < pos5);
    }
    */
}
