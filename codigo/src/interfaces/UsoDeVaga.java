package src.interfaces;

import java.time.LocalDateTime;

import src.entities.Cliente;
import src.entities.Vaga;
import src.enums.ServicosAdicionais;

public interface UsoDeVaga {
    double sair(Cliente cliente);
    void adicionarServicos(ServicosAdicionais servico);
    double valorPago();
    LocalDateTime getEntrada();
    Vaga getVaga();
    LocalDateTime getSaida();
    void setValorPago(double valorPago);
    
}
