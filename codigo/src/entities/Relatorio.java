package src.entities;

import java.util.List;

import src.exceptions.ExcecaoMesInvalido;

public class Relatorio implements Observer {
    List<Cliente> observers;

    
    /** 
     * @param mes
     * @return String
     * @throws ExcecaoMesInvalido
     */

    @Override
    public void notificar(){
        top5clientesMes(mes);
    }
    
    
    
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
    
}
