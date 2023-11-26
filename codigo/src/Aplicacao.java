package src;

import src.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import src.dao.GenericDAO;
import src.dao.SerializationUtils;
import src.Exceptions.*;

public class Aplicacao {



    public static int estacionamentoAtrabalhar;

    static Estacionamento estacionamento;

    static List<Estacionamento> estacionamentos;
    static SerializationUtils<Estacionamento> serializableEstacionamento;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            Aplicacao.serializableEstacionamento = new SerializationUtils<>();

            Aplicacao.estacionamentos = serializableEstacionamento.getAll();

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
                        Aplicacao.serializableEstacionamento.add(estacionamentoNovo);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 1:
                    System.out.println("Digite o nome do estacionamento a se trabalhar: ");
                    System.out.println("1 - " + estacionamentos.get(0).getNome());
                    System.out.println("2 - " + estacionamentos.get(1).getNome());
                    System.out.println("3 - " + estacionamentos.get(2).getNome());

                    estacionamentoAtrabalhar = scanner.nextInt();
                    estacionamento = estacionamentos.get(estacionamentoAtrabalhar);

                    menuEstacionamento();
                    break;
                case 2:
                    System.out.println("Digite o nome do estacionamento a se trabalhar: ");
                    System.out.println("1 - " + estacionamentos.get(0).getNome());
                    System.out.println("2 - " + estacionamentos.get(1).getNome());
                    System.out.println("3 - " + estacionamentos.get(2).getNome());

                    estacionamentoAtrabalhar = scanner.nextInt();
                    estacionamento = estacionamentos.get(estacionamentoAtrabalhar);
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
                    System.out.println("Opção inválida. Tente novamente...");
            }

        }

    }

    private static void menuEstacionamento() {

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
                        Aplicacao.serializableEstacionamento.update(estacionamentos);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    id = scanner.nextLine();
                    try {
                        Cliente clientePorId = estacionamento.encontrarClientePorId(id);
                        System.out.println("Nome: " + clientePorId.getNome() + " Id: " + clientePorId.getId());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                double soma = estacionamento.getClientes().stream()
                .flatMap(cli -> cli.getVeiculos().stream())
                .flatMap(veiculo -> veiculo.getUsos().stream())
                .mapToDouble(usoa -> usoa.valorPago())  // Corrigido para usar o método getValor
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
                    estacionamento.top5Clientes(mes);
                    break;
                case 7:
                    scanner.close();
                    System.out.println("Saindo do Menu do Estacionamento.");
                    
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuCliente() {

        
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
                        estacionamento.addVeiculo(veiculo, idCliente);
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
                    // Arrecadado por Veículo
                    System.out.print("Digite a placa do veículo: ");
                    placa = scanner.nextLine();
                    veiculo = clienteSelecionado.possuiVeiculo(placa);
                    if (veiculo != null) {
                        double arrecadacaoVeiculo = veiculo.totalArrecadado();
                        System.out.println("Arrecadação total do veículo de placa " + placa + " é " + arrecadacaoVeiculo);
                    } else {
                        System.out.println("O cliente não possui um veículo com a placa informada.");
                    }
                    break;
                
                case 5:
                    // Arrecadado Total
                    double arrecadacaoTotal = clienteSelecionado.arrecadadoTotal();
                    System.out.println("Arrecadação total do cliente " + clienteSelecionado.getNome() + " é " + arrecadacaoTotal);
                    break;
                
                case 6:
                    // Arrecadado no Mês
                    System.out.print("Digite o número do mês: ");
                    int mes = scanner.nextInt();
                    double arrecadacaoMes = clienteSelecionado.arrecadadoNoMes(mes);
                    System.out.println("Arrecadação do cliente " + clienteSelecionado.getNome() + " no mês " + mes + " é " + arrecadacaoMes);
                    break;
                
                case 7:
                    // Obter Histórico de Usos
                    List<UsoDeVaga> historicoUsos = clienteSelecionado.obterHistoricoDeUsos();
                    for (UsoDeVaga uso : historicoUsos) {
                        System.out.println("Veículo: " + uso.getVeiculo().getPlaca() + " - Vaga: " + uso.getVaga().getPosicao() +
                                " - Entrada: " + uso.getEntrada() + " - Saída: " + uso.getSaida() + " - Valor: " + uso.valorPago());
                    }
                    break;
                
                case 8:
                    // Remover Veículo
                    System.out.print("Digite a placa do veículo a ser removido: ");
                    placa = scanner.nextLine();
                    clienteSelecionado.removerVeiculo(placa);
                    System.out.println("Veículo removido com sucesso.");
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
