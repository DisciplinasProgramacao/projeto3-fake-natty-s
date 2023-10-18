package tests;
import src.Vaga;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class vagaTest {

        private Vaga vaga;
    
        @BeforeEach
        public void setUp() {
            vaga = new Vaga("A", 1);
        }
    
        @Test
        public void testEstacionar() {
            assertFalse(vaga.estacionar());
        }
    
        @Test
        public void testSair() {
            assertFalse(vaga.sair());
            vaga.estacionar();
            assertTrue(vaga.disponivel());
        }
    
        @Test
        public void testDisponivel() {
            assertTrue(vaga.disponivel());
            vaga.estacionar();
            vaga.sair();
            assertTrue(vaga.disponivel());
        }
    }
    
    
