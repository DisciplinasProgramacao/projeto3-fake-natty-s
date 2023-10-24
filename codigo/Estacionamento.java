package src;

//Classe estacionamento - Gabriel Pongelupe e Felipe Picinin

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Collections;

public class Estacionamento {

	private String nome;
	private List<Cliente> clientes;
	private List<Vaga> vagas;
	private int fileiras;
	private int colunas;
	private double valorArrecadado;
	private int valorTotal;
	private int valorUso;

	/*
	 * Construtor Estacionamento
	 * 
	 * @param nome, fileiras, vagasPorFila
	 */
	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		this.nome = nome;
		this.fileiras = fileiras;
		this.colunas = vagasPorFila;
		this.clientes = new ArrayList<>();
		this.vagas = new ArrayList<>();
		this.valorArrecadado = 0;
		this.valorTotal = 0;
		this.valorUso = 0;
		gerarVagas();
	}

	/*
	 * Adiciona veiculo a um cliente (faz a verificação se aql cliente existe
	 * cadastrado e
	 * chama o medoto addVeiculo(veiculo) do cliente especifico)
	 */
	public void addVeiculo(Veiculo veiculo, String idCli) {
		Cliente cliente = encontrarClientePorId(idCli);
		if (cliente != null) {
			cliente.addVeiculo(veiculo);
		}
	}

	/*
	 * Adiciona cliente na lista de clientes do estacionamento,
	 * 
	 * @param cliente: Cliente
	 */

	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	/*
	 * Faz a verificação de vagas e fileiras e
	 * adiciona uma vaga na lista de vagas
	 * 
	 */

	private void gerarVagas() {

		char filaChar = 'A';

		for (int fila = 1; fila <= fileiras; fila++) {
			for (int numero = 1; numero <= colunas; numero++) {

				int index_vaga = numero + (colunas * (fila - 1)); // pega a posição da lista em que esta

				if (vagas.get(index_vaga) == null) {
					String filaString = String.valueOf(filaChar); // Converte char para String
					Vaga vaga = new Vaga(filaString, numero);
					vagas.add(vaga);
					return;
				}

			}
			filaChar++; // Avança para a próxima letra da fila (B, C, ...)
		}
	}

	/*
	 * Encontra vaga disponivel e chama metodo estacionar(placa) desta vaga
	 * 
	 * @param String placa
	 */

	public void estacionar(String placa) {

		for (Cliente cliente : clientes) {
			if (cliente.possuiVeiculo(placa) != null) {
				Veiculo veiculo_cliente = cliente.possuiVeiculo(placa);

				for (Vaga vaga : vagas) { // procura vaga
					if (vaga.disponivel()) {
						veiculo_cliente.estacionar(vaga);

						break;
					}
				}
			}
		}

	}

	/*
	 * Encontra cliente por um id e retorna este cliente
	 * 
	 * @param String idCli
	 */

	private Cliente encontrarClientePorId(String idCli) {
		for (Cliente cliente : clientes) {
			if (cliente.getId().equals(idCli)) {
				return cliente;
			}
		}
		return null; // Cliente não encontrado
	}

	public double getValorArrecadado() {
		return valorArrecadado;
	}

/**
 * Método para permitir que um veículo saia de uma vaga com base na placa.
 * 
 * @param placa A placa do veículo que deseja sair.
 */
public void sair(String placa) {
    for (Vaga vaga : vagas) {
        if (vaga.disponivel()) {
            vaga.sair();
            break;
        }
    }
}

/**
 * Calcula o valor total arrecadado pela empresa a partir de todos os clientes.
 * 
 * @return O valor total arrecadado.
 */
public double totalArrecadado() {
    for (Cliente cliente : clientes) {
        valorTotal += cliente.arrecadadoTotal();
    }
    return valorTotal;
}

/**
 * Calcula a arrecadação da empresa para um mês específico.
 * 
 * @param mes O mês para o qual deseja calcular a arrecadação.
 * @return A arrecadação do mês especificado.
 */
public double arrecadacaoNoMes(int mes) {
    double arrecadadoMes = 0.0;
    for (Cliente cliente : clientes) {
        arrecadadoMes += cliente.arrecadadoNoMes(mes);
    }
    return arrecadadoMes;
}

/**
 * Calcula o valor médio arrecadado por uso pelos clientes da empresa.
 * 
 * @return O valor médio por uso.
 */
public double valorMedioPorUso() {
    for (Cliente cliente : clientes) {
        int totalUsos = cliente.totalDeUsos();
        double arrecadacaoTotal = cliente.arrecadadoTotal();
        
        if (totalUsos > 0) {
            return arrecadacaoTotal / totalUsos;
        } else {
            return 0.0; // Evita divisão por zero.
        }
    }
    return valorUso;
}

/**
 * Retorna os 5 principais clientes com base no número total de usos no mês especificado.
 * 
 * @param mes O mês para o qual deseja listar os principais clientes.
 * @return Uma string que contém os nomes e o total de usos dos 5 principais clientes no mês especificado.
 */
public String top5Clientes(int mes) {
    if (mes < 1 || mes > 12) {
        return "Mês inválido. O mês deve estar entre 1 e 12.";
    }

    List<Cliente> clientesOrdenados = new ArrayList<>(clientes); // Crie uma nova lista para evitar a modificação da lista original

    // Ordene os clientes com base no total de usos em ordem decrescente
    Collections.sort(clientesOrdenados, new Comparator<Cliente>() {
        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.totalDeUsos() - c1.totalDeUsos();
        }
    });

    String result = "Top 5 Clientes no mês " + mes + ":\n";

    int count = Math.min(5, clientesOrdenados.size());

    for (int i = 0; i < count; i++) {
        Cliente cliente = clientesOrdenados.get(i);
        result += "Nome: " + cliente.getNome() + ", Total de Usos: " + cliente.totalDeUsos() + "\n";
    }

    return result;
}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQuantFileiras(int fileiras) {
		this.fileiras = fileiras;
	}

	public void setcoluna(int colunas) {
		this.colunas = colunas;
	}
}
