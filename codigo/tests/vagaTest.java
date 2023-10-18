package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Vaga;


public class vagaTest {

        private Vaga vaga;
    
        @BeforeEach
        public void setUp() {
            vaga = new Vaga("A", 1);
        }
    
        @Test
        public void testEstacionar() {
            assertTrue(vaga.estacionar());
            assertFalse(vaga.estacionar());
        }
    
        @Test
        public void testSair() {
            assertFalse(vaga.sair());
            vaga.estacionar();
            assertTrue(vaga.sair());
            assertTrue(vaga.disponivel());
        }
    
        @Test
        public void testDisponivel() {
            assertTrue(vaga.disponivel());
            vaga.estacionar();
            assertFalse(vaga.disponivel());
            vaga.sair();
            assertTrue(vaga.disponivel());
        }
    }
    
    
