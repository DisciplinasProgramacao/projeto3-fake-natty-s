package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestandoArquivo implements Serializable {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();

        // Criando alguns clientes e veículos de exemplo
        Cliente cliente1 = new Cliente("João", "123");
        Veiculo veiculo1 = new Veiculo("ABC123");
        cliente1.addVeiculo(veiculo1);
        clientes.add(cliente1);

        Cliente cliente2 = new Cliente("Maria", "456");
        Veiculo veiculo2 = new Veiculo("XYZ789");
        cliente2.addVeiculo(veiculo2);  
        clientes.add(cliente2);

        // Salvando a lista de clientes em um arquivo
        ManipuladorDeArquivo.escreverObjeto(ManipuladorDeArquivo.getArqClientes(), cliente2);

        // Carregando a lista de clientes do arquivo
        Cliente clientesCarregados = ManipuladorDeArquivo.lerObjeto(ManipuladorDeArquivo.getArqClientes(), Cliente.class);
        
        System.out.println("Nome do Cliente: " + clientesCarregados.getNome());
        System.out.println("ID do Cliente: " + clientesCarregados.getId());
    }
}
