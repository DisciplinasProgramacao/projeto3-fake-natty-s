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

    
    /** 
     * @param args
     */
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
            System.out.println("");

            int escolha = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            switch (escolha) {
                case 0:
                    System.out.print("Digite o nome do estacionamento: ");
                    String name = scanner.nextLine();
                    System.out.println("\n");
                    System.out.print("Digite o número de fileiras: ");
                    int fileiras = scanner.nextInt();
                    System.out.println("\n");
                    System.out.print("Digite o número de vagas por fileira: ");
                    System.out.println("");
                    int vagasPorFila = scanner.nextInt();

                    Estacionamento estacionamentoNovo = new Estacionamento(name, fileiras, vagasPorFila);

                    System.out.println("");
                    try {
                        Aplicacao.serializableEstacionamento.add(estacionamentoNovo);
                        estacionamentos.add(estacionamentoNovo);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 1:

                    System.out.println("Digite o número do estacionamento a se trabalhar: ");
                    for (Estacionamento estacio : estacionamentos) {

                        System.out.println("1 - " + estacio.getNome());

                    }

                    estacionamentoAtrabalhar = scanner.nextInt();
                    try {
                        estacionamento = estacionamentos.get(estacionamentoAtrabalhar);
                        menuEstacionamento();
                    } catch (RuntimeException e) {
                        System.out.println("Não existe este estacionamento em nosso sistema, Escolhe outro");
                    }

                    break;
                case 2:
                    System.out.println("Digite o número do estacionamento a se trabalhar: ");
                    for (Estacionamento estacio : estacionamentos) {

                        System.out.println("1 - " + estacio.getNome());

                    }

                    estacionamentoAtrabalhar = scanner.nextInt();

                    try {
                        estacionamento = estacionamentos.get(estacionamentoAtrabalhar);
                        menuCliente();
                    } catch (Exception e) {
                        System.out.println("Não existe este estacionamento em nosso sistema, Escolhe outro");
                    }

                    break;
                case 3:
                    System.out.println("Digite o número do estacionamento a se trabalhar: ");
                    for (Estacionamento estacio : estacionamentos) {

                        System.out.println("1 - " + estacio.getNome());

                    }

                    estacionamentoAtrabalhar = scanner.nextInt();

                    try {
                        estacionamento = estacionamentos.get(estacionamentoAtrabalhar);
                        menuVeiculo();
                    } catch (Exception e) {
                        System.out.println("Não existe este estacionamento em nosso sistema, Escolhe outro");
                    }

                    break;
                case 4:
                    System.out.println("Digite o número do estacionamento a se trabalhar: ");
                    for (Estacionamento estacio : estacionamentos) {

                        System.out.println("1 - " + estacio.getNome());

                    }

                    estacionamentoAtrabalhar = scanner.nextInt();

                    try {
                        estacionamento = estacionamentos.get(estacionamentoAtrabalhar);
                        menuVaga();
                    } catch (Exception e) {
                        System.out.println("Não existe este estacionamento em nosso sistema, Escolhe outro");
                    }

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
            System.out.println("\n");
            System.out.println("Menu do Estacionamento - " + estacionamento.getNome());
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Encontrar Cliente Por Id");
            System.out.println("3. Calcular Total Arrecadado");
            System.out.println("4. Calcular Arrecadado no Mês");
            System.out.println("5. Valor Médio Por Uso");
            System.out.println("6. Top 5 Clientes");
            System.out.println("7. Sair");
            System.out.println("\n");

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
                            .mapToDouble(usoa -> usoa.valorPago()) // Corrigido para usar o método getValor
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
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuCliente() {
        System.out.println("\n");
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
            System.out.println("\n");
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
            System.out.println("\n");
            System.out.println("\n");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {

                case 1:
                    System.out.print("Digite a placa do veículo: ");
                    placa = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    try {
                        estacionamento.addVeiculo(veiculo, idCliente);
                        Aplicacao.serializableEstacionamento.update(estacionamentos);
                    } catch (ExcecaoCadastrarVeiculoExistente | ExcecaoClientejaExistente e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite a placa do veiculo: ");

                    placa = scanner.nextLine();

                    if (clienteSelecionado.possuiVeiculo(placa) != null) {
                        System.out
                                .println("O cliente: " + clienteSelecionado.getNome() + " Possui o veículo de placa = "
                                        + clienteSelecionado.possuiVeiculo(placa).getPlaca());
                    } else {
                        System.out.println(
                                "O cliente: " + clienteSelecionado.getNome() + " Não possui o veículo de placa = "
                                        + placa);
                    }

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
                        System.out
                                .println("Arrecadação total do veículo de placa " + placa + " é " + arrecadacaoVeiculo);
                    } else {
                        System.out.println("O cliente não possui um veículo com a placa informada.");
                    }
                    break;

                case 5:
                    // Arrecadado Total
                    double arrecadacaoTotal = clienteSelecionado.arrecadadoTotal();
                    System.out.println(
                            "Arrecadação total do cliente " + clienteSelecionado.getNome() + " é " + arrecadacaoTotal);
                    break;

                case 6:
                    // Arrecadado no Mês
                    System.out.print("Digite o número do mês: ");
                    int mes = scanner.nextInt();
                    double arrecadacaoMes = clienteSelecionado.arrecadadoNoMes(mes);
                    System.out.println("Arrecadação do cliente " + clienteSelecionado.getNome() + " no mês " + mes
                            + " é " + arrecadacaoMes);
                    break;

                case 7:
                    // Obter Histórico de Usos
                    List<UsoDeVaga> historicoUsos = clienteSelecionado.obterHistoricoDeUsos();
                    for (UsoDeVaga uso : historicoUsos) {
                        System.out.println(" - Vaga: " + uso.getVaga().getPosicao() +
                                " - Entrada: " + uso.getEntrada() + " - Saída: " + uso.getSaida() + " - Valor: "
                                + uso.valorPago());
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
                    // Retorna ao do-while do main
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuVeiculo() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Veículo:");
            System.out.println("1. Estacionar com Veículo");
            System.out.println("2. Sair com Veículo");
            System.out.println("3. Adicionar Veículo");
            System.out.println("4. Remover Veículo");
            System.out.println("5. Sair");

            System.out.println("\n");
            System.out.println("\n");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {
                case 1:
                    // Estacionar com Veículo
                    System.out.print("Digite a placa do veiculo: ");
                    String placa = scanner.nextLine();

                    try {

                        estacionamento.estacionar(placa);
                        Aplicacao.serializableEstacionamento.update(estacionamentos);
                    } catch (ExcecaoEstacionarSemSair e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    // Estacionar com Veículo
                    System.out.print("Digite a placa do veiculo: ");
                    String placa1 = scanner.nextLine();

                    try {
                        estacionamento.sair(placa1);
                        Aplicacao.serializableEstacionamento.update(estacionamentos);
                    } catch (ExcecaoSairFinalizada e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do cliente que deseja trabalhar: ");
                    String idCliente = scanner.nextLine();

                    try {
                        Cliente clienteSelecionado = estacionamento.encontrarClientePorId(idCliente);
                        System.out.println("Cliente selecionado: " + clienteSelecionado.getNome());

                    } catch (ExcecaoClientejaExistente e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.print("Digite a placa do veículo: ");
                    placa = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    try {
                        estacionamento.addVeiculo(veiculo, idCliente);
                        Aplicacao.serializableEstacionamento.update(estacionamentos);
                    } catch (ExcecaoCadastrarVeiculoExistente | ExcecaoClientejaExistente e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:
                    // Remover Veículo
                    System.out.print("Digite o ID do cliente: ");
                    idCliente = scanner.nextLine();
                    try {
                        Cliente cliente = estacionamento.encontrarClientePorId(idCliente);

                        System.out.print("Digite a placa do veículo a ser removido: ");
                        String placa2 = scanner.nextLine();
                        cliente.removerVeiculo(placa2);
                        System.out.println("Veículo removido com sucesso.");
                        Aplicacao.serializableEstacionamento.update(estacionamentos);

                    } catch (ExcecaoClientejaExistente e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Saindo do Menu de Veículo.");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuVaga() {

    }

}
