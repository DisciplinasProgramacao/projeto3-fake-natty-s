package src.entities;

import java.util.List;

import src.exceptions.ExcecaoMesInvalido;

public class Relatorio extends Observer {
    List<Cliente> observers;

    public String top5clientesMes(int mes) throws ExcecaoMesInvalido {
        
        if (mes < 1 || mes > 12) {
            throw new ExcecaoMesInvalido();
        }

        return "clientes";
    }
    
}