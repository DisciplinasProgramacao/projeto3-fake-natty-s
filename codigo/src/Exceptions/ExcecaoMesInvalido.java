package src.exceptions;

public class ExcecaoMesInvalido extends Exception {
    public ExcecaoMesInvalido() {
        super("Mês inválido. O mês deve estar entre 1 e 12.");
    }
    
}