package src.exceptions;
import src.entities.Vaga;

public class ExcecaoSairFinalizada extends Exception{
    public ExcecaoSairFinalizada (Vaga vaga){
        super("Não foi possível sair da vaga " + vaga.getPosicao() + "pois este veículo não está ocupando ela.");
    }
}
