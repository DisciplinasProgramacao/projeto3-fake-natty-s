package tests;
import src.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.Veiculo;
import src.Vaga;
import src.UsoDeVaga;

import java.time.LocalDateTime;



public class veiculoTest {

    private Veiculo veiculo;
    private Vaga vaga;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo("ABC123");
        vaga = new Vaga("A", 1);
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
        assertFalse(vaga.disponivel()); // A vaga deve continuar ocupada
    }

    

    @Test
    void testSair() {
        vaga.estacionar();
        veiculo.sair(vaga);
        assertTrue(vaga.disponivel());
    }

    @Test
    void testSairNaoEstacionado() {
        // veiculo nao estaciona em nenhuma vaga, logo retorna zero quando chama metodo veiculo.sair();

        assertEquals(0.0, veiculo.sair(vaga)); // A vaga deve continuar disponível
    }


    
    @Test
    void testTotalArrecadado() {
        double total = 0;

        veiculo.estacionar(vaga); // estacionamos diversas vezes e saimos, adicionando o valor da saida a variavel total
        total += veiculo.sair(vaga);
        veiculo.estacionar(vaga);
        total += veiculo.sair(vaga);
        veiculo.estacionar(vaga);
        total += veiculo.sair(vaga);
        
        assertEquals(total, veiculo.totalArrecadado()); // compara o total com o retorno do metodo totalArrecadada();
    }

    @Test
    void testArrecadadoNoMes() {

        // Cria alguns UsoDeVaga com datas de entrada e saída para um mês específico (por exemplo, mês 3)
        LocalDateTime entrada1 = LocalDateTime.of(2023, 3, 1, 8, 0);
        LocalDateTime saida1 = LocalDateTime.of(2023, 3, 1, 10, 0);
        UsoDeVaga uso1 = new UsoDeVaga(vaga, false, false, false);
        uso1.setEntrada(entrada1);
        uso1.setSaida(saida1);

        // Cria alguns UsoDeVaga com datas de entrada e saída para um mês específico (por exemplo, mês 3)
        LocalDateTime entrada2 = LocalDateTime.of(2023, 3, 2, 9, 0);
        LocalDateTime saida2 = LocalDateTime.of(2023, 3, 2, 12, 0);
        UsoDeVaga uso2 = new UsoDeVaga(vaga, false, false, false);
        uso2.setEntrada(entrada2);
        uso2.setSaida(saida2);

        // Adicione os Usos de Vaga ao Veiculo
        veiculo.getUsos().add(uso1);
        veiculo.getUsos().add(uso2);

        // Verifique se o método totalArrecadadoNoMes() retorna o valor esperado para o mês 3
        double totalArrecadadoNoMes = veiculo.arrecadadoNoMes(3);
        assertEquals(10.0, totalArrecadadoNoMes); // O valor total esperado para o mês 3 é 10.0
    }
}






