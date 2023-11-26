import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import src.enums.ModalidadeCliente;
import src.enums.ServicosAdicionais;
import src.enums.Turno;

public class UsoDeVagaTest {

    private Vaga vaga;
    private Cliente cliente;

    @Before
    public void setUp() {
        vaga = new Vaga();  // You may need to adjust this based on your Vaga class implementation
        cliente = new Cliente("John Doe", ModalidadeCliente.HORISTA);
    }

    @Test
    public void testEntrada() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);
        assertNotNull(usoDeVaga.getEntrada());
    }

    @Test
    public void testSairHorista() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);
        usoDeVaga.adicionarServicos(ServicosAdicionais.LAVAGEM);
        usoDeVaga.adicionarServicos(ServicosAdicionais.POLIMENTO);

        // Simulate parking for 2 hours
        usoDeVaga.sair(cliente);

        assertEquals(8.0, usoDeVaga.getValorPago(), 0.01); // 2 hours * VALOR_FRACAO
    }

    @Test
    public void testSairDeTurno() {
        Turno turno = new Turno(LocalDateTime.now(), LocalDateTime.now().plusHours(8)); // Assuming 8-hour shift
        cliente.setModalidade(ModalidadeCliente.DE_TURNO);
        cliente.setTurnoEscolhido(turno);

        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);

        // Simulate parking for 10 hours (beyond the shift duration)
        usoDeVaga.sair(cliente);

        assertEquals(16.0, usoDeVaga.getValorPago(), 0.01); // 10 hours * VALOR_FRACAO
    }

    @Test(expected = RuntimeException.class)
    public void testSairPolimentoTempoInsuficiente() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);

        // Try to add polishing service without meeting the minimum parking time
        usoDeVaga.adicionarServicos(ServicosAdicionais.POLIMENTO);
        usoDeVaga.sair(cliente);
    }

    @Test
    public void testAdicionarServicos() {
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);

        usoDeVaga.adicionarServicos(ServicosAdicionais.LAVAGEM);
        usoDeVaga.adicionarServicos(ServicosAdicionais.POLIMENTO);

        assertEquals(2, usoDeVaga.getServicosAdicionais().size());
    }

    // Add more test cases as needed

}
