package src.MainTests;

import java.util.Scanner;
import src.entities.UsoDeVaga;
import src.entities.Vaga;
import src.enums.ServicosAdicionais;

public class MainUsoDeVaga {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando uma vaga
        Vaga vaga = new Vaga("A", 1);

        // Criando um uso de vaga associado à vaga criada
        UsoDeVaga usoDeVaga = new UsoDeVaga(vaga);

        int opcao;
        do {
            System.out.println("MENU USO DE VAGA:");
            System.out.println("1- Adicionar Serviço Adicional");
            System.out.println("2- Sair e Calcular Valor");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Escolha um serviço adicional:");
                    for (ServicosAdicionais servico : ServicosAdicionais.values()) {
                        System.out.println(servico.ordinal() + 1 + "- " + servico.name());
                    }
                    int escolhaServico = scanner.nextInt();
                    ServicosAdicionais servicoAdicional = ServicosAdicionais.values()[escolhaServico - 1];
                    usoDeVaga.adicionarServicos(servicoAdicional);
                    System.out.println("Serviço adicional adicionado: " + servicoAdicional.name());
                    break;
                case 2:
                    try {
                        double valorAPagar = usoDeVaga.sair();
                        System.out.println("Cliente saiu da vaga. Valor a ser pago: " + valorAPagar);
                    } catch (RuntimeException e) {
                        System.out.println("Erro ao sair: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 2);

        scanner.close();
    }
}
