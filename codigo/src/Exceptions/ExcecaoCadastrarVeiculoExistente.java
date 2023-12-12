package src.Exceptions;

import src.entities.Veiculo;

public class ExcecaoCadastrarVeiculoExistente extends Exception{

    public ExcecaoCadastrarVeiculoExistente(Veiculo veiculo){
        super("O veiculo de placa " + veiculo.getPlaca() + " ja está cadastrado no sistema");
    }

    public ExcecaoCadastrarVeiculoExistente(String veiculo){
        super(veiculo);
    }
}

