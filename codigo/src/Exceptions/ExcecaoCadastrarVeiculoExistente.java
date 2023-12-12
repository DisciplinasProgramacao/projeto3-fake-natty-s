package src.Exceptions;

import src.entities.Carro;

public class ExcecaoCadastrarVeiculoExistente extends Exception{

    public ExcecaoCadastrarVeiculoExistente(Carro veiculo){
        super("O veiculo de placa " + veiculo.getPlaca() + " ja está cadastrado no sistema");
    }

    public ExcecaoCadastrarVeiculoExistente(String veiculo){
        super(veiculo);
    }
}

