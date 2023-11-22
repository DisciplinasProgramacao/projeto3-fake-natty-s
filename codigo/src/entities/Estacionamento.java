package src.entities;

import java.io.Serializable;

//Classe estacionamento - Gabriel Pongelupe e Felipe Picinin

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.Exceptions.ExcecaoCadastrarVeiculoExistente;
import src.Exceptions.ExcecaoClientejaExistente;
import src.Exceptions.ExcecaoEstacionarSemSair;
import src.Exceptions.ExcecaoSairFinalizada;
import src.entities.UsoDeVaga;
import src.interfaces.Entidade;

public class Estacionamento implements Serializable, Entidade {

	private String nome;
	private List<Cliente> clientes;
	private List<Vaga> vagas;
	private int fileiras;
	private int colunas;
	private double valorArrecadado;
	private int valorTotal;
	private int valorMes;
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
		this.valorMes = 0;
		this.valorUso = 0;
		//gerarVagas();
	}

	/*
	 * Adiciona veiculo a um cliente (faz a verificação se aql cliente existe
	 * cadastrado e
	 * chama o medoto addVeiculo(veiculo) do cliente especifico)
	 */
	public void addVeiculo(Veiculo veiculo, String idCli)
			throws ExcecaoCadastrarVeiculoExistente, ExcecaoClientejaExistente {
		Cliente cliente = encontrarClientePorId(idCli);
		List<Veiculo> veiculos = cliente.getVeiculos();

		if (veiculos.contains(veiculo)) {
			throw new ExcecaoCadastrarVeiculoExistente(veiculo);
		} else {
			cliente.addVeiculo(veiculo);
		}

	}

	/*
	 * Adiciona cliente na lista de clientes do estacionamento,
	 * 
	 * @param cliente: Cliente
	 */

	public void addCliente(Cliente cliente) throws ExcecaoClientejaExistente {

		if (clientes.contains(cliente)) {
			throw new ExcecaoClientejaExistente(cliente);
		} else {
			this.clientes.add(cliente);
		}
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
					Vaga vaga = new Vaga(filaString, numero, this);
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

	public void estacionar(String placa) throws ExcecaoEstacionarSemSair {

		for (Cliente cliente : clientes) {
			if (cliente.possuiVeiculo(placa) != null) {
				Veiculo veiculo = cliente.possuiVeiculo(placa);

				for (Vaga vaga : vagas) { // procura vaga
					if (vaga.disponivel()) {
						veiculo.estacionar(vaga);

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

	public Cliente encontrarClientePorId(String idCli) throws ExcecaoClientejaExistente {
		boolean encontrado = false;
		Cliente clienteEncontrado = null;
		for (Cliente cliente : clientes) {
			if (cliente.getId().equals(idCli)) {
				clienteEncontrado = cliente;
				encontrado = true;
			}
		}
		if (!encontrado) {
			throw new ExcecaoClientejaExistente("O cliente nao pode ser encontrado");
		}
		return clienteEncontrado; // Cliente não encontrado
	}

	public double getValorArrecadado() {
		return valorArrecadado;
	}

	/**
	 * Método para permitir que um veículo saia de uma vaga com base na placa.
	 * 
	 * @param placa A placa do veículo que deseja sair.
	 */
	public void sair(String placa) throws ExcecaoSairFinalizada {
		for (Cliente cliente : clientes) {
			if (cliente.possuiVeiculo(placa) != null) {
				Veiculo veiculo = cliente.possuiVeiculo(placa);

				for (UsoDeVaga uso : veiculo.getUsos()) {
					if (uso.getSaida() == null) {
						veiculo.sair(uso.getVaga());
					}
				}
			}

		}
	}

	public double totalArrecadado() {
		return clientes.stream()
				.mapToDouble(Cliente::arrecadadoTotal)
				.sum();
	}

	/**
	 * Calcula a arrecadação da empresa para um mês específico usando Streams.
	 *
	 * @param mes O mês para o qual deseja calcular a arrecadação.
	 * @return A arrecadação do mês especificado.
	 */
	public double arrecadacaoNoMes(int mes) {
		return clientes.stream()
				.mapToDouble(cliente -> cliente.arrecadadoNoMes(mes))
				.sum();
	}

	/**
	 * Calcula o valor médio arrecadado por uso pelos clientes da empresa usando
	 * Streams.
	 *
	 * @return O valor médio por uso.
	 */
	public double valorMedioPorUso() {
		return clientes.stream()
				.mapToInt(Cliente::totalDeUsos)
				.average()
				.orElse(0.0);
	}

	/**
	 * Retorna os 5 principais clientes com base no número total de usos no mês
	 * especificado usando Streams.
	 *
	 * @param mes O mês para o qual deseja listar os principais clientes.
	 * @return Uma string que contém os nomes e o total de usos dos 5 principais
	 *         clientes no mês especificado.
	 */
	public String top5Clientes(int mes) {
		if (mes < 1 || mes > 12) {
			return "Mês inválido. O mês deve estar entre 1 e 12.";
		}

		Map<String, Integer> topClients = clientes.stream()
				.collect(Collectors.toMap(Cliente::getNome, cliente -> cliente.totalDeUsosNoMes(mes)));

		return topClients.entrySet().stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.limit(5)
				.map(entry -> "Nome: " + entry.getKey() + ", Total de Usos: " + entry.getValue())
				.collect(Collectors.joining("\n", "Top 5 Clientes no mês " + mes + ":\n", "")); */
			return "oi";
	}

	/**
	 * Calcula o valor total arrecadado pela empresa a partir de todos os clientes.
	 * 
	 * @return O valor total arrecadado.
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQuantFileiras(int fileiras) {
		this.fileiras = fileiras;
	}

	public void setcoluna(int colunas) {
		this.colunas = colunas;
	}

	public List<Cliente> getClientes() {
    return clientes;

	
	}

	public void setClientes(List<Cliente> clienteNovos){
		this.clientes = clienteNovos;
	}

	public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

	public String getNome() {
		return nome;
	}

	@Override
	public String getId() {
		return this.nome;
	}

}
