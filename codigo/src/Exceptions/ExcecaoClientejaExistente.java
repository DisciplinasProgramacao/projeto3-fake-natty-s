package src.Exceptions;
// Exception cliente ja existente
import src.Cliente;

public class ExcecaoClientejaExistente extends Exception{

    public ExcecaoClientejaExistente(Cliente cliente){
        super("o Cliente " + cliente.getNome() + "ja existe no sistema, portanto o id " + cliente.getId() + "ja está cadastrado");
    }
}
