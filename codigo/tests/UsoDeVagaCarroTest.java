package tests;
import src.*;
import src.entities.*;
import src.enums.*;
import src.enums.ModalidadeCliente;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


/**
 * Testes unitários para a classe UsoDeVaga.
 */
public class UsoDeVagaCarroTest {

    private UsoDeVagaCarro usoDeVaga;
    private Vaga vaga;
    private Cliente cliente;

    /**
     * Configuração inicial antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        vaga = new Vaga("A", 1);
        cliente = new Cliente("NomeCliente", "IDCliente");
        usoDeVaga = new UsoDeVagaCarro(vaga);
    }

    /**
     * Testa o método sair() da classe UsoDeVaga.
     * 
     * @lorem Testa a saída normal do cliente da vaga.
     * @lorem Testa a tentativa de saída sem tempo mínimo para polimento.
     * @lorem Testa a tentativa de saída sem tempo mínimo para lavagem.
     */
    @Test
    public void testSair() {
        
        double valorPago = usoDeVaga.sair(cliente);
        assertTrue(valorPago >= 0);
        
        
        usoDeVaga.adicionarServicos(ServicosAdicionais.POLIMENTO);
        assertThrows(RuntimeException.class, () -> usoDeVaga.sair(cliente));

        
        usoDeVaga.adicionarServicos(ServicosAdicionais.LAVAGEM);
        assertThrows(RuntimeException.class, () -> usoDeVaga.sair(cliente));
    }

    /**
     * Testa o método calcularValorHorista() da classe UsoDeVaga.
     * 
     * @lorem Testa o cálculo do valor para um período de tempo normal.
     * @lorem Testa o cálculo do valor para um período de tempo mínimo.
     * @lorem Testa o cálculo do valor para um período de tempo longo.
     */
    @Test
    public void testCalcularValorHorista() {
        
        assertEquals(8.0, usoDeVaga.calcularValorHorista(30));
        
        
        assertEquals(0.0, usoDeVaga.calcularValorHorista(0));

        
        assertEquals(32.0, usoDeVaga.calcularValorHorista(120));
    }

    /**
     * Testa o método calcularValorDeTurno() da classe UsoDeVaga.
     * 
     * @lorem Testa o cálculo do valor para um cliente que não paga pelo turno.
     * @lorem Testa o cálculo do valor para um cliente que paga pelo turno.
     */
    @Test
    public void testCalcularValorDeTurno() {
        
        Cliente clienteSemPagamentoTurno = new Cliente(Modalidade.HORISTA);
        assertEquals(16.0, usoDeVaga.calcularValorDeTurno(60, Turno.TARDE, clienteSemPagamentoTurno));

        
        Cliente clienteComPagamentoTurno = new Cliente(Modalidade.DE_TURNO, Turno.TARDE);
        assertEquals(0.0, usoDeVaga.calcularValorDeTurno(60, Turno.TARDE, clienteComPagamentoTurno));
    }

    /**
     * Testa o método adicionarServicos() da classe UsoDeVaga.
     * 
     * @lorem Testa adicionar um serviço adicional à lista.
     * @lorem Testa adicionar vários serviços adicionais à lista.
     */
    @Test
    public void testAdicionarServicos() {
        
        usoDeVaga.adicionarServicos(ServicosAdicionais.LAVAGEM);
        assertEquals(1, usoDeVaga.getServicosAdicionais().size());

       
        usoDeVaga.adicionarServicos(Arrays.asList(ServicosAdicionais.LAVAGEM, ServicosAdicionais.LAVAGEM));
        assertEquals(3, usoDeVaga.getServicosAdicionais().size());
    }

    /**
     * Testa o método valorPago() da classe UsoDeVaga.
     * 
     * @lorem Testa obter o valor pago após a saída do cliente.
     * @lorem Testa obter o valor pago sem a saída do cliente.
     */
    @Test
    public void testValorPago() {
        
        usoDeVaga.sair(new Cliente(ModalidadeCliente.HORISTA));
        assertTrue(usoDeVaga.valorPago() >= 0);

        
        assertEquals(0.0, usoDeVaga.valorPago());
    }

}

