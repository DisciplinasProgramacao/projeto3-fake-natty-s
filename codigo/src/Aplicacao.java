package src;

import src.entities.*;
import java.util.Scanner;
import src.dao.GenericDAO;

import src.exceptions.*;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Aplicacao {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        GenericDAO<Cliente, String> clienteDAO;
        GenericDAO<Estacionamento, String> estacionamentoDAO;
        GenericDAO<Vaga, String> vagaDAO;
        GenericDAO<Veiculo, String> veiculoDAO;

        try {
            clienteDAO = new GenericDAO<>("codigo/files/clientes.ser");
            estacionamentoDAO = new GenericDAO<>("codigo/files/estacionamentos.ser");
            vagaDAO = new GenericDAO<>("codigo/files/vagas.ser");
            veiculoDAO = new GenericDAO<>("codigo/files/veiculos.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
    
            System.out.println("Menu Principal do Estacionamento:");
            System.out.println("1. Menu Estacionamento");
            System.out.println("2. Menu Cliente");
            System.out.println("3. Menu Veículo");
            System.out.println("4. Menu Vaga");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                
                case 1:
                    selecionarEstacionamento();
                break;            
                case 2:
                    menuEstacionamento();
                    break;
                case 3:
                    menuCliente();
                    break;
                case 4:
                    menuVeiculo();
                    break;
                case 5:
                    menuVaga();
                    break;
                case 6:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void menuEstacionamento (int estacionamento){
        
        
    }

    private void selecionarEstacionamento() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um estacionamento:");
        System.out.println("1 - ESTACIONAMENTO 1");
        System.out.println("2 - ESTACIONAMENTO 2");
        System.out.println("3 - ESTACIONAMENTO 3");
        
        int escolha = scanner.nextInt();


        switch (escolha) {
            Estacionamento estacionamento;
            
            case 1:
                ManipuladorDeArquivo.escreverObjeto(ManipuladorDeArquivo.ARQ_ESTACIONAMENTO1, estacionamento);
            break;
            case 2:
                ManipuladorDeArquivo.escreverObjeto(ManipuladorDeArquivo.ARQ_ESTACIONAMENTO2, estacionamento);
            break;
            case 3:
                ManipuladorDeArquivo.escreverObjeto(ManipuladorDeArquivo.ARQ_ESTACIONAMENTO3, estacionamento);
            break;

            default:
             System.out.println("Opção inválida. Tente novamente.");
        }  

        scanner.close();
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

                    try {

                        Estacionamento.estacionar(placa);
                    } catch (ExcecaoEstacionarSemSair e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("Informe a placa:");
                    String placa1 = scanner.nextLine();

                    try {
                        Estacionamento.sair(placa1);
                    } catch (ExcecaoSairFinalizada e) {
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
                Cliente cliente;
                case 1:
                ManipuladorDeArquivo.escreverObjeto(ManipuladorDeArquivo.ARQ_CLIENTE, cliente);
                    break;

                case 2:
                    System.out.println("Informe o Id:");
                    String ID = scanner.nextLine();

                    estacionamento.encontrarClientePorId(ID);
                    break;

                case 3:
                    scanner.close();
                    return; // Voltar ao menu principal

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuVeiculo(Estacionamento estacionamento) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Veiculo veiculo;

            System.out.println("Informe a placa do veículo que deseja estacionar: ");
            String placa = scanner.nextLine();
            
            for(Cliente cliente : estacionamento.getClientes()){
                veiculo = cliente.possuiVeiculo(placa);
            }
            if (veiculo == null){
                System.out.println("Veiculo não encontrado");
                return;
            }


            
            
            System.out.println("Menu Veiculo:");
            System.out.println("1. Estacionar");
            System.out.println("2. Sair ");
            System.out.println("3. Total arrecadado");
            System.out.println("4. Total arrecadado no mes");
            System.out.println("5. Total de usos");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            double resposta = 0;
            
            scanner.nextLine();
            

            switch (opcao) {

                case 1:

                    try{
                        estacionamento.estacionar(placa);
                    }catch(ExcecaoEstacionarSemSair e){
                        System.out.println(e.getMessage());
                    }
                    
                    break;

                case 2:
                    try{
                        estacionamento.sair(placa);
                    }catch(ExcecaoSairFinalizada e){
                        System.out.println(e.getMessage());
                    }
                        
                    
                    
                    break;

                case 3:
                    veiculo.totalArrecadado();
                    break;

                case 4:
                    System.out.println("Informe o mes:");
                    int mes = scanner.nextInt();
                    resposta = veiculo.arrecadadoNoMes(mes);
                    System.out.println(resposta);
                    break; 

                case 5:
                    resposta = veiculo.totalDeUsos();
                    System.out.println(resposta);
                    break;

                case 6:
                    scanner.close();
                    return; 
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
