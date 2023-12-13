package src.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;

import src.entities.Cliente;
import src.entities.Vaga;
import src.enums.ServicosAdicionais;

public interface UsoDeVaga {
    Double sair(Cliente cliente);
    void adicionarServicos(ServicosAdicionais servico);
    Double valorPago();
    LocalDateTime getEntrada();
    Vaga getVaga();
    LocalDateTime getSaida();
}
