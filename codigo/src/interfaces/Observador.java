package src.interfaces;

import src.Exceptions.ExcecaoMesInvalido;

public interface Observador {
    public void atualizar(int mes) throws ExcecaoMesInvalido;
}
