package src.entities;

import java.time.LocalDateTime;
import java.util.List;

import src.enums.ServicosAdicionais;
import src.interfaces.UsoDeVaga;
import src.interfaces.UsoDeVagaFactory;

public class UsoCarroFactoryImp implements UsoDeVagaFactory{
    public UsoDeVaga criarUsoDeVaga(Vaga vaga, LocalDateTime entrada, LocalDateTime saida, double valorPago,
			List<ServicosAdicionais> servicosAdicionais){
                return new UsoDeVagaCarro(vaga, entrada, saida, valorPago,servicosAdicionais);
            }
}
