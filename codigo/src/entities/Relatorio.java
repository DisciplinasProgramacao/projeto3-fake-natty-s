package src.entities;

import java.util.List;
import java.util.Map;

import src.Exceptions.ExcecaoMesInvalido;
import src.interfaces.*;
import src.*;

public class Relatorio implements Observador {
    List<Observavel> observaveis;
    Map<Integer, List<Cliente>> top5clientes;
    
    /** 
     * @param mes
     * @return String
     * @throws ExcecaoMesInvalido
     */
    public String top5clientesMes(int mes) throws ExcecaoMesInvalido {
        
        if (mes < 1 || mes > 12) {
            throw new ExcecaoMesInvalido();
        }

        return "clientes";
    }

    @Override
    public void atualizar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
    
}
