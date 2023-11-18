package src;
import java.io.Serializable;

import src.interfaces.Entidade;

// Classe Veiculo com o atributo placa
class Exemplo implements Serializable, Entidade {
    private String placa;

    public Exemplo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String getId() {
        return this.placa;
    }
}