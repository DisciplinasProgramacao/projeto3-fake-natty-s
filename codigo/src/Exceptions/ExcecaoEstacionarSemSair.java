package src.Exceptions;

import src.entities.Vaga;

public class ExcecaoEstacionarSemSair extends Exception{
    public ExcecaoEstacionarSemSair(Vaga vaga){
        super("Não é possivel estacionar pois este veículo ja esta estacionado na vaga " + vaga.getPosicao());
    }
}
