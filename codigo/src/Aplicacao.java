package src;

import src.entities.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import src.dao.GenericDAO;

import src.exceptions.*;

public class Aplicacao {

    public static GenericDAO<Cliente, String> clienteDAO;
    public static GenericDAO<Estacionamento, String> estacionamentoDAO;
    public static GenericDAO<Vaga, String> vagaDAO;
    public static GenericDAO<Veiculo, String> veiculoDAO;
    public static GenericDAO<UsoDeVaga, String> usoDeVagaDAO;

    public static String estacionamentoAtrabalhar;

    static List<Estacionamento> estacionamentos;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            clienteDAO = new GenericDAO<>("codigo/files/clientes.dat");
            estacionamentoDAO = new GenericDAO<>("codigo/files/estacionamentos.dat");
            vagaDAO = new GenericDAO<>("codigo/files/vagas.dat");
            veiculoDAO = new GenericDAO<>("codigo/files/veiculos.dat");
            usoDeVagaDAO = new GenericDAO<>("codigo/files/usodevaga.dat");

            estacionamentos = estacionamentoDAO.getAll();
            for (Estacionamento estacionamento : estacionamentos) {
                estacionamento.setClientes(clienteDAO.getAll().stream()
                        .filter(cliente -> cliente.getEstacionamento().contains(estacionamento))
                        .collect(Collectors.toList()));

                estacionamento.setVagas(vagaDAO.getAll().stream()
                        .filter(vaga -> vaga.getEstacionamento().equals(estacionamento))
                        .collect(Collectors.toList()));

                for (Cliente cliente : estacionamento.getClientes()) {
                    cliente.setVeiculos(veiculoDAO.getAll().stream()
                            .filter(veiculo -> veiculo.getCliente().equals(cliente))
                            .collect(Collectors.toList()));

                    for (Veiculo veiculo : cliente.getVeiculos()) {
                        veiculo.setUsos(usoDeVagaDAO.getAll().stream()
                                .filter(e -> e.getVeiculo().equals(veiculo)).collect(Collectors.toList()));
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {

            System.out.println("Menu Principal do Estacionamento:");
            System.out.println("0. Adicionar Novo Estacionamento");
            System.out.println("1. Menu Estacionamento");
            System.out.println("2. Menu Cliente");
            System.out.println("3. Menu Veículo");
            System.out.println("4. Menu Vaga");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 0:
                    System.out.print("Digite o nome do estacionamento: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o número de fileiras: ");
                    int fileiras = scanner.nextInt();
                    System.out.print("Digite o número de vagas por fileira: ");
                    int vagasPorFila = scanner.nextInt();

                    Estacionamento estacionamentoNovo = new Estacionamento(name, fileiras, vagasPorFila);

                    try {
                        estacionamentoDAO.add(estacionamentoNovo);
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 1:
                    System.out.println("Digite o nome do estacionamento a se trabalhar: ");
                    estacionamentoAtrabalhar = scanner.nextLine();
                    menuEstacionamento(estacionamentoDAO.get(estacionamentoAtrabalhar));
                    break;
                case 2:
                    System.out.println("Digite o nome estacionamento a se trabalhar: ");
                    estacionamentoAtrabalhar = scanner.nextLine();
                    menuCliente(estacionamentoDAO.get(estacionamentoAtrabalhar));
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
                    System.out.println("Opção inválida. Tente novamente...");
            }
            try {
                clienteDAO.close();
                estacionamentoDAO.close();
                vagaDAO.close();
                veiculoDAO.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void menuEstacionamento(Estacionamento estacionamento) {

        Scanner scanner = new Scanner(System.in);
        String id;
        int mes;

        while (true) {
            System.out.println("Menu do Estacionamento - " + estacionamento.getNome());
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Encontrar Cliente Por Id");
            System.out.println("3. Calcular Total Arrecadado");
            System.out.println("4. Calcular Arrecadado no Mês");
            System.out.println("5. Valor Médio Por Uso");
            System.out.println("6. Top 5 Clientes");
            System.out.println("7. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {
                case 1:
                    System.out.println("Digite as informações do cliente: ");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("id: ");
                    id = scanner.nextLine();
                    Cliente cliente = new Cliente(nome, id);
                    cliente.addEstacionamento(estacionamento);
                    try {
                        estacionamento.addCliente(cliente);
                        clienteDAO.add(cliente);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                case 2:
                    id = scanner.nextLine();
                    try {
                        Cliente clientePorId = clienteDAO.get(id);
                        System.out.println("Nome: " + clientePorId.getNome() + " Id: " + clientePorId.getId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    double soma;
                    List<UsoDeVaga> usosDeVagas = usoDeVagaDAO.getAll();

                    soma = usosDeVagas.stream()
                            .filter(e -> e.getEstacionamento().equals(estacionamento))
                            .mapToDouble(UsoDeVaga::getValorPago)
                            .sum();

                    System.out.println("O estacionamento " + estacionamento.getNome() + "Arrecadou no total: "
                            + soma);
                    break;
                case 4:
                    System.out.println(
                            "Digite o mes que deseja verificar... \n 1- Janeiro \n 2- Fevereiro \n 3- Março...");
                    mes = scanner.nextInt();
                    System.out.println("O total arrecadado foi: " + estacionamento.arrecadacaoNoMes(mes));
                    break;
                case 5:
                    System.out.println("O valor medio por uso é de: " + estacionamento.valorMedioPorUso());

                    break;
                case 6:
                    System.out.println("Calcular top5 clientes no mes de: ");
                    System.out.println(" 1- Janeiro \n 2- Fevereiro \n 3- Março... ");
                    mes = scanner.nextInt();
                    //estacionamento.top5Clientes(mes);
                    break;
                case 7:
                    scanner.close();
                    System.out.println("Saindo do Menu do Estacionamento.");
                    return; // Retorna ao do-while do main
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuCliente(Estacionamento estacionamento) {
        Scanner scanner = new Scanner(System.in);
        Cliente clienteSelecionado = null;
         String placa;

        System.out.print("Digite o ID do cliente que deseja trabalhar: ");
        String idCliente = scanner.nextLine();

        try {
            clienteSelecionado = estacionamento.encontrarClientePorId(idCliente);
            System.out.println("Cliente selecionado: " + clienteSelecionado.getNome());
        } catch (ExcecaoClientejaExistente e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.println("Menu do Cliente - " + clienteSelecionado.getNome());
            System.out.println("1. Adicionar Veículo");
            System.out.println("2. Possui Veículo");
            System.out.println("3. Total de Usos");
            System.out.println("4. Arrecadado por Veículo");
            System.out.println("5. Arrecadado Total");
            System.out.println("6. Arrecadado no Mês");
            System.out.println("7. Obter Histórico de Usos");
            System.out.println("8. Remover Veículo");
            System.out.println("9. Voltar ao Menu Principal");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {

                case 1:
                        System.out.print("Digite a placa do veículo: ");
                        placa = scanner.nextLine();
                        Veiculo veiculo = new Veiculo(placa);
                        try {
                            veiculoDAO.add(veiculo);
                            veiculo.setCliente(clienteSelecionado);
                        } catch (ExcecaoCadastrarVeiculoExistente | ExcecaoClientejaExistente e) {
                            System.out.println(e.getMessage());
                        }
                    break;
                case 2:
                    System.out.println("Digite a placa do veiculo: ");
                    
                    placa = scanner.nextLine();

                    System.out.println("O cliente: " + clienteSelecionado.getNome() + " Possui o veículo de placa = "
                         + clienteSelecionado.possuiVeiculo(placa));
                    break;
                case 3:
                    List<UsoDeVaga> usos = clienteSelecionado.obterHistoricoDeUsos();
                    int totalusos = usos.lastIndexOf(usos);
                    System.out.println("Total de usos do cliente " + clienteSelecionado.getNome() + " é " + totalusos);
                    break;
                case 4:
                    // ... Restante das opções do menu
                    break;
                case 5:
                    // ... Restante das opções do menu
                    break;
                case 6:
                    // ... Restante das opções do menu
                    break;
                case 7:
                    // ... Restante das opções do menu
                    break;
                case 8:
                    // ... Restante das opções do menu
                    break;
                case 9:
                    System.out.println("Voltando ao Menu Principal.");
                    scanner.close();
                    return; // Retorna ao do-while do main
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuVeiculo() {

    }

    private static void menuVaga() {

    }

}
