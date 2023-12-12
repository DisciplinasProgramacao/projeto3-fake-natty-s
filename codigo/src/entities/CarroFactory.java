package src.entities;

import java.io.Serializable;

import src.interfaces.Veiculo;
import src.interfaces.VeiculoFactory;

public class CarroFactory implements Serializable, VeiculoFactory {

    @Override
    public Veiculo criarVeiculo(String placa) {
        return new Carro(placa);
    }
    
}
