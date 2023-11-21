package src.MainTests;

import java.util.Scanner;
import src.entities.UsoDeVaga;
import src.entities.Vaga;
import src.entities.Veiculo;
import src.exceptions.ExcecaoEstacionarSemSair;
import src.exceptions.ExcecaoSairFinalizada;

public class MainVeiculo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
        Veiculo veiculo = new Veiculo(placa);

        Vaga vaga = new Vaga();

        int opcao;
        do {
            System.out.println("MENU VEÍCULO:");
            System.out.println("1- Estacionar");
            System.out.println("2- Sair");
            System.out.println("3- Total arrecadado");
            System.out.println("4- Sair do Menu");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    try {
                        veiculo.estacionar(vaga);
                        System.out.println("Veículo estacionado com sucesso!");
                    } catch (ExcecaoEstacionarSemSair e) {
                        System.out.println("Erro ao estacionar: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        double valorPago = veiculo.sair(vaga);
                        System.out.println("Veículo saiu. Valor a ser pago: " + valorPago);
                    } catch (ExcecaoSairFinalizada e) {
                        System.out.println("Erro ao sair: " + e.getMessage());
                    }
                    break;
                case 3:
                    double totalArrecadado = veiculo.totalArrecadado();
                    System.out.println("Total arrecadado pelo veículo: " + totalArrecadado);
                    break;
                case 4:
            
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }
}