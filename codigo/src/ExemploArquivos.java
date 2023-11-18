package src;

import java.io.Serializable;
import src.Exemplo;
import src.dao.GenericDAO;
import src.interfaces.Entidade;

// Classe principal para testar o GenericDAO com Veiculo
public class ExemploArquivos {
    public static void main(String[] args) {
        // Criando um DAO para Veiculo
        GenericDAO<Exemplo, String> veiculoDAO;
        try {
            veiculoDAO = new GenericDAO<>("exemplos.ser");

            // Adicionando alguns veículos
            Exemplo veiculo1 = new Exemplo("ABC1234");
            Exemplo veiculo2 = new Exemplo("XYZ9876");
            Exemplo veiculo3 = new Exemplo("12345667");
            Exemplo veiculo4 = new Exemplo("12345667");

            veiculoDAO.add(veiculo1);
            veiculoDAO.add(veiculo2);
            veiculoDAO.add(veiculo3);
            veiculoDAO.add(veiculo4);

            // Obtendo um veículo com base na placa
            Exemplo veiculoBuscado = veiculoDAO.get("XYZ9876");
            if (veiculoBuscado != null) {
                System.out.println("Veículo encontrado: Placa = " + veiculoBuscado.getPlaca());
            } else {
                System.out.println("Veículo não encontrado.");
            }

            // Atualizando um veículo
            Exemplo veiculoAtualizado = new Exemplo("AAA1111");
            veiculoDAO.update(veiculoAtualizado, "ABC1234");

            // Deletando um veículo
            Exemplo veiculoParaDeletar = new Exemplo("ABC1234");
            veiculoDAO.delete(veiculoParaDeletar);

            // Obtendo todos os veículos
            System.out.println("Todos os veículos:");
            for (Exemplo v : veiculoDAO.getAll()) {
                System.out.println("Placa = " + v.getPlaca());
            }

            // Fechando o DAO
            veiculoDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
