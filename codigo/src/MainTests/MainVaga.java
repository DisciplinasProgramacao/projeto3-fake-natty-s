package src.MainTests;

import java.util.Scanner;
import src.entities.Vaga;

public class MainVaga {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando uma vaga
        System.out.print("Digite a fila da vaga: ");
        String fila = scanner.nextLine();
        System.out.print("Digite o número da vaga: ");
        int numero = scanner.nextInt();
        Vaga vaga = new Vaga(fila, numero);

        int opcao;
        do {
            System.out.println("MENU VAGA:");
            System.out.println("1- Estacionar");
            System.out.println("2- Sair");
            System.out.println("3- Verificar disponibilidade");
            System.out.println("4- Ver posição da vaga");
            System.out.println("5- Sair do Menu");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    boolean estacionado = vaga.estacionar();
                    if (estacionado) {
                        System.out.println("Carro estacionado com sucesso!");
                    } else {
                        System.out.println("Vaga já ocupada. Não foi possível estacionar.");
                    }
                    break;
                case 2:
                    boolean saiu = vaga.sair();
                    if (saiu) {
                        System.out.println("Carro saiu da vaga com sucesso!");
                    } else {
                        System.out.println("Vaga já está vazia. Não foi possível sair.");
                    }
                    break;
                case 3:
                    boolean disponivel = vaga.disponivel();
                    System.out.println("A vaga está " + (disponivel ? "disponível" : "ocupada"));
                    break;
                case 4:
                    String posicao = vaga.getPosicao();
                    System.out.println("Posição da vaga: " + posicao);
                    break;
                case 5:
                    // Sair do Menu
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 5);

        scanner.close();
    }
}
