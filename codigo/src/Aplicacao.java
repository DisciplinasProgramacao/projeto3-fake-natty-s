package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import src.Exceptions.ExcecaoCadastrarVeiculoExistente;
import src.Exceptions.ExcecaoClientejaExistente;
import src.Exceptions.ExcecaoEstacionarSemSair;
import src.Exceptions.ExcecaoSairFinalizada;

public class Aplicacao {
    public static void main(String[] args) throws  /*excecoes*/ {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu Principal do Estacionamento:");
            System.out.println("1. Estacionamento");
            System.out.println("2. Cliente");
            System.out.println("3. Veiculo");
            System.out.println("4. Vaga");
            System.out.println("5. Criar Estacionamento");
            System.out.println("6. Sair");

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

                System.out.println("Informe os detalhes do Estacionamento:");
                System.out.print("Numero de fileiras: ");
                int numFil = scanner.nextInt();
                scanner.nextLine();


                System.out.print("Nome");
                String nome = scanner.nextLine();

                System.out.print("Vagas por fila ");
                int vagaporFila = scanner.nextInt();
                scanner.nextLine();

                Estacionamento estacionamento = new Estacionamento(nome, numFil, vagaporFila);

                case 6:
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
            System.out.println("1. Estacionar ");
            System.out.println("2. Sair da vaga ");
            System.out.println("3. Total arrecadado ");
            System.out.println("4. Total arrecadado no mes ");
            System.out.println("5. Valor medio por uso ");
            System.out.println("6. Top 5 clientes ");
            System.out.println("7. Voltar ao menu principal");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:

                System.out.println("Informe a placa:");
                String placa = scanner.nextLine();

                try{
            
                  Estacionamento.estacionar(placa);
                  }
                catch(ExcecaoEstacionarSemSair e){
                        System.out.println(e.getMessage());
                  }

                break;

                case 2:
                  System.out.println("Informe a placa:");
                String placa1 = scanner.nextLine();

                try{
                Estacionamento.sair(placa1);
                  }
                catch(ExcecaoSairFinalizada e){
                        System.out.println(e.getMessage());
                  }


                    break;

                case 3:

                
                  Estacionamento.totalArrecadado();


                    break;

                case 4:

                 System.out.print("Numero do mes de interesse:");
                    int mes = scanner.nextInt();
                    scanner.nextLine();

                 Estacionamento.arrecadacaoNoMes(mes);

                    break;

                case 5:

                Estacionamento.valorMedioPorUso();

                    break;

                case 6:

                Estacionamento.top5Clientes();

                    break;

                case 7:
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
            System.out.println("1. Cadastrar-se");
            System.out.println("2. Encontrar cliente por id");
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
            System.out.println("1. Adicionar veiculo ");
            System.out.println("2. Possui veiculo ");
            System.out.println("3. Arrecadado por veiculo");
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
            System.out.println("1. gerar vaga ");
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



    

