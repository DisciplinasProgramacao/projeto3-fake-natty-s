

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class veiculoTest {

    private Veiculo veiculo;
    private Vaga vaga;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo("ABC123");
        vaga = new Vaga("A1");
    }

    @Test
    void testEstacionar() {
        veiculo.estacionar(vaga);
        assertFalse(vaga.disponivel());
    }

    @Test
    void testEstacionarNaoDisponivel() {
        vaga.estacionar();
        veiculo.estacionar(vaga);
        assertTrue(vaga.disponivel()); // A vaga deve continuar ocupada
    }

    @Test
    void testSair() {
        vaga.estacionar();
        veiculo.sair(vaga);
        assertTrue(vaga.disponivel());
    }

    @Test
    void testSairNaoEstacionado() {
        veiculo.sair(vaga);
        assertTrue(vaga.disponivel()); // A vaga deve continuar disponível
    }

    @Test
    void testTotalArrecadado() {
        UsoDeVaga uso1 = new UsoDeVaga();
        uso1.registrarValorPago(50.0);
        UsoDeVaga uso2 = new UsoDeVaga();
        uso2.registrarValorPago(75.0);

        veiculo.adicionarUso(uso1);
        veiculo.adicionarUso(uso2);

        assertEquals(125.0, veiculo.totalArrecadado());
    }

    @Test
    void testArrecadadoNoMes() {
        UsoDeVaga uso1 = new UsoDeVaga();
        uso1.registrarEntrada(LocalDateTime.of(2023, 1, 15, 12, 0));
        uso1.registrarValorPago(50.0);
        UsoDeVaga uso2 = new UsoDeVaga();
        uso2.registrarEntrada(LocalDateTime.of(2023, 2, 10, 15, 0));
        uso2.registrarValorPago(75.0);

        veiculo.adicionarUso(uso1);
        veiculo.adicionarUso(uso2);

        assertEquals(50.0, veiculo.arrecadadoNoMes(1));
        assertEquals(75.0, veiculo.arrecadadoNoMes(2));
        assertEquals(0.0, veiculo.arrecadadoNoMes(3)); // Sem uso em março
    }
}