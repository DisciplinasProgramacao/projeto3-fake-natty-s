
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsoDeVagaTest {

    private UsoDeVaga uso;
    private Vaga vaga;

    @Before
    public void setUp() {
        vaga = new Vaga("Vaga de Teste");
        uso = new UsoDeVaga(vaga);
    }

    @Test
    public void testSair() {
        double valorPago = uso.sair();
        assertEquals(true, valorPago > 0);
    }
   
    @Test
    public void testValorPago() {
        assertEquals(0.0, uso.valorPago(), 0.01);
        double valorPago = uso.sair();
        assertEquals(true, uso.valorPago() > 0);
    }

}
