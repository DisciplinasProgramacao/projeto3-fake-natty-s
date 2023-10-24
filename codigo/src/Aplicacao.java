package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) throws  /*excecoes*/ {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Principal do Estacionamento:");
            System.out.println("1. Estacionamento");
            System.out.println("2. Cliente");
            System.out.println("3. Veiculo");
            System.out.println("4. Vaga");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    menuEstacionamento();
                    break;
                case 2:
                    menuCliente();
                    break;
                case 3:
                    menuVeiculo();
                    break;
                case 4:
                    menuVaga();
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuEstacionamento() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Estacionamento:");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. Voltar ao menu principal");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:
                     scanner.close();
                    return; // Voltar ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuCliente() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Cliente:");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. Voltar ao menu principal");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    
                    break;

                case 2:
                
                    break;

                case 3:
                    scanner.close();
                    return; // Voltar ao menu principal

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuVeiculo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Veiculo:");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {

                case 1:
                    
                    break;

                case 2:
                    
                    break;

                case 3:
                   
                    break;

                case 4:
                    scanner.close();
                    return; // Voltar ao menu principal
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuVaga() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Vaga:");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. Voltar ao menu principal");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1: 
                    
                break;

                case 2:
                   
                    break;

                case 3:
                   
                    break;

                case 4:
                    return; // Voltar ao menu principal

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}



    

