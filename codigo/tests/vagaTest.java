package tests;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.entities.Estacionamento;
import src.entities.Vaga;


public class vagaTest {

        private Vaga vaga;
    
        @BeforeEach
        public void setUp() {
            Estacionamento estacionamento = new Estacionamento("Estacionamento1", 10, 10);
            vaga = new Vaga("A", 1, estacionamento);
        }
    
        @Test
        public void testEstacionar() {
            assertTrue(vaga.estacionar());
        }
    
        @Test
        public void testSair() {
            assertFalse(vaga.sair());
            vaga.estacionar();
            assertFalse(vaga.disponivel());
        }
    
        @Test
        public void testDisponivel() {
            assertTrue(vaga.disponivel());
            vaga.estacionar();
            vaga.sair();
            assertTrue(vaga.disponivel());
        }
    }
    
    
