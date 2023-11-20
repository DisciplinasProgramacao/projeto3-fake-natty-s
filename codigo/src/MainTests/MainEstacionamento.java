import java.util.Scanner;
import src.Cliente;
import src.Estacionamento;
import src.Veiculo;
import src.Exceptions.ExcecaoCadastrarVeiculoExistente;
import src.Exceptions.ExcecaoClientejaExistente;
import src.Exceptions.ExcecaoEstacionarSemSair;
import src.Exceptions.ExcecaoSairFinalizada;

public class MainEstacionamento {

    public static void main(String[] args) {
        String arq = "codigo/files/estacionamento1.ser";
        Estacionamento estacionamento = new Estacionamento("Estacionamento Teste", 5, 10);
        Scanner scanner = new Scanner(System.in);
        int opcao;
        String placa;

        do {
            System.out.println("MENU ESTACIONAMENTO:");
            System.out.println("1- Adicionar Cliente");
            System.out.println("2- Adicionar Veículo a Cliente");
            System.out.println("3- Estacionar Veículo");
            System.out.println("4- Sair Veículo");
            System.out.println("5- Total Arrecadado");
            System.out.println("6- Arrecadação no Mês");
            System.out.println("7- Valor Médio por Uso");
            System.out.println("8- Top 5 Clientes no Mês");
            System.out.println("9- Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha pendente

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.println("Digite o ID do cliente: ");
                    String idCliente = scanner.nextLine();
                    Cliente novoCliente = new Cliente(nomeCliente, idCliente);
                    try {
                        estacionamento.addCliente(novoCliente, arq);
                        System.out.println("Cliente adicionado com sucesso!");
                    } catch (ExcecaoClientejaExistente e) {
                        System.out.println("Erro ao adicionar cliente: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Digite o id do cliente: ");
                    String id = scanner.nextLine();
                    System.out.println("Digite a placa do veiculo: ");
                    placa = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    try {
                        estacionamento.addVeiculo(veiculo, id, arq);
                    } catch (ExcecaoClientejaExistente e) {
                        System.out.println(e.getMessage());
                    } catch (ExcecaoCadastrarVeiculoExistente e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("Digite a placa do veículo: ");
                    placa = scanner.nextLine();
                    try {
                        estacionamento.estacionar(placa);
                        System.out.println("Veículo estacionado com sucesso!");
                    } catch (ExcecaoEstacionarSemSair e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Digite a placa do veículo que está saindo: ");
                    String placaSaida = scanner.nextLine();
                    try {
                        estacionamento.sair(placaSaida);
                        System.out.println("Veículo saiu com sucesso!");
                    } catch (ExcecaoSairFinalizada e) {
                        System.out.println("Erro ao sair veículo: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Total arrecadado: " + estacionamento.totalArrecadado());
                    break;
                case 6:
                    System.out.println("Digite o mês: ");
                    int mes = scanner.nextInt();
                    System.out.println("Arrecadação no mês: " + estacionamento.arrecadacaoNoMes(mes));
                    break;
                case 7:
                    System.out.println("Valor médio por uso: " + estacionamento.valorMedioPorUso());
                    break;
                case 8:
                    System.out.println(estacionamento.top5Clientes(11)); // Exemplo com mês 11 (novembro)
                    break;
                case 9:
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 9);
    }
}
