package src.entities;

import java.io.Serializable;

//Classe estacionamento - Gabriel Pongelupe e Felipe Picinin

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import src.entities.UsoDeVagaCarro;
import src.enums.ServicosAdicionais;
import src.interfaces.UsoDeVaga;
import src.interfaces.Veiculo;
import src.Exceptions.*;

public class Estacionamento implements Serializable {

	private String nome;
	private List<Cliente> clientes;
	private List<Vaga> vagas;
	private int fileiras;
	private int colunas;
	private double valorArrecadado;
	private Relatorio relatorio;

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
		this.relatorio = new Relatorio(this);
		this.valorArrecadado = 0;
		char fila = 'A';

		for (int i = 1; i <= fileiras; i++) {
			for (int j = 1; j <= vagasPorFila; j++) {
				String nomeFila = String.valueOf(fila);
				this.vagas.add(new Vaga(nomeFila, j));
			}
			fila++;
		}
		// gerarVagas();
	}

	/**
	 * @param veiculo
	 * @param idCli
	 * @throws ExcecaoCadastrarVeiculoExistente
	 * @throws ExcecaoClientejaExistente
	 */
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
			throw new ExcecaoCadastrarVeiculoExistente(veiculo.getPlaca());
		} else {
			veiculo.addObserver(this.relatorio);
			cliente.addVeiculo(veiculo);
		}
	}

	/**
	 * @param cliente
	 * @throws ExcecaoClientejaExistente
	 */
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
	 * Encontra vaga disponivel e chama metodo estacionar(placa) desta vaga
	 * 
	 * @param String placa
	 */

	public void estacionar(String placa, List<ServicosAdicionais> servicosAdicionais)
			throws ExcecaoEstacionarSemSair, ExcecaoCadastrarVeiculoExistente {
		Boolean encontrado = false;
		
		for (Cliente cliente : clientes) {
			if (cliente.possuiVeiculo(placa) != null) {

				Veiculo veiculo = cliente.possuiVeiculo(placa);
				encontrado = true;

				for (Vaga vaga : vagas) { // procura vaga
					if (vaga.disponivel()) {
						for (UsoDeVaga vags : veiculo.getUsos()) {
							if (vags.getSaida() == null) {

								throw new ExcecaoEstacionarSemSair(vaga);
							}
						}
						
						veiculo.estacionar(vaga, servicosAdicionais);

						break;
					}
				}
			}
		}

		if (!encontrado) {
			throw new ExcecaoCadastrarVeiculoExistente("veiculo nao encontrado");
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
	public double sair(String placa) throws ExcecaoSairFinalizada, ExcecaoCadastrarVeiculoExistente {
		double totalPago = 0.0;
		Boolean encontrado = false;
		Boolean usoz = false;

		for (Cliente cliente : clientes) {
			if (cliente.possuiVeiculo(placa) != null) {
				Veiculo veiculo = cliente.possuiVeiculo(placa);
				encontrado = true;
				for (UsoDeVaga uso : veiculo.getUsos()) {
					if (uso.getSaida() == null) {
						usoz = true;
						totalPago = veiculo.sair(uso.getVaga(), cliente);
					}
				}
			}

		}
		if (!encontrado) {
			throw new ExcecaoCadastrarVeiculoExistente("veiculo nao encontrado");
		}
		if (!usoz) {
			throw new ExcecaoSairFinalizada();
		}
		return totalPago;
	}

	public Vaga getVagaDisponivel() {
		for (Vaga vaga : vagas) {
			if (vaga.disponivel()) {
				return vaga;
			}
		}
		return null; // Retorna null se não houver vaga disponível
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
		List<Double> valorMedio = new ArrayList<>();
		for (Cliente cliente : clientes) {
			for (Veiculo v : cliente.getVeiculos()) {
				for (UsoDeVaga vags : v.getUsos()) {
					valorMedio.add(vags.valorPago());
				}
			}
		}
		return valorMedio.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
	}
	
	/**
	 * Retorna os 5 principais clientes com base no número total de usos no mês
	 * especificado usando Streams.
	 *
	 * @param mes O mês para o qual deseja listar os principais clientes.
	 * @return Uma string que contém os nomes e o total de usos dos 5 principais
	 *         clientes no mês especificado.
	 */
	public List<Cliente> top5Clientes(int mes) {
		return clientes.stream()
				.sorted(Comparator.comparingDouble(c -> ((Cliente) c).arrecadadoNoMes(mes)).reversed())
				.limit(5)
				.collect(Collectors.toList());
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

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clienteNovos) {
		this.clientes = clienteNovos;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public String getNome() {
		return nome;
	}
}
