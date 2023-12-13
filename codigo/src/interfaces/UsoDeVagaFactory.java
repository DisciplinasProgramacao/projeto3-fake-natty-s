package src.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import src.entities.Vaga;
import src.enums.ServicosAdicionais;

public interface UsoDeVagaFactory {
    UsoDeVaga criarUsoDeVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida, double valorPago,
			List<ServicosAdicionais> servicosAdicionais);
    
} 
