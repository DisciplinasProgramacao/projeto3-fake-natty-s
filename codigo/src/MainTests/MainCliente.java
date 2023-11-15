package src.MainTests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import src.*;

public class MainCliente {

    public static void main(String[] args) {

        Estacionamento estacionamento = new Estacionamento("Estacionamento test", 10, 10);
        
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // Escolhendo cliente

        System.out.println("Digite o codigo de Cliente que você deseja verificar: ");
        String id = scanner.nextLine();
        Cliente cliente = estacionamento.encontrarClientePorId(id);

        do {
            System.out.println("MENU CLIENTE:");
            System.out.println("1- Verificar se possui veículo");
            System.out.println("2- Total de usos no estacionamento");
            System.out.println("3- Arrecadado por veículo");
            System.out.println("4- Arrecadado total");
            System.out.println("5- Arrecadado no mês");
            System.out.println("6- Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    String placa = scanner.next();

                    System.out.println("O cliente " + cliente.getNome()
                            + (possuiVeiculo(placa, cliente) ? " possui este veículo" : " não possui este veículo"));

                    break;
                case 2:
                    int totalUsos = cliente.totalDeUsos();
                    System.out.println(
                            "Total de usos do cliente " + cliente.getNome() + " no estacionamento: " + totalUsos);
                    break;
                case 3:
                    System.out.print("Digite a placa do veículo: ");
                    placa = scanner.next();
                    double arrecadadoPorVeiculo = cliente.arrecadadoPorVeiculo(placa);
                    System.out
                            .println("O veiculo de placa:  " + placa + " arrecadou no total: " + arrecadadoPorVeiculo);
                    break;
                case 4:
                    double arrecadadoTotal = cliente.arrecadadoTotal();
                    System.out.println("Arrecadado total do cliente: " + cliente.getNome() + " = " + arrecadadoTotal);
                    break;
                case 5:
                    System.out.print("Digite o mês: ");
                    int mes = scanner.nextInt();

                    System.out.println("Arrecadado no mês do cliente: " + cliente.getNome() + " = "
                            + cliente.arrecadadoNoMes(mes));
                    break;
                case 6:
                    scanner.close();
                    // return para quando aplicar no main geral
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (true);

    }

    public static boolean possuiVeiculo(String placa, Cliente cliente) {
        return (cliente.possuiVeiculo(placa) != null);
    }
}
