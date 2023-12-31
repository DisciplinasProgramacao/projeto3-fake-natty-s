package src.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import src.enums.ServicosAdicionais;
import src.enums.Turno;
import src.interfaces.UsoDeVaga;

/**
 * Classe que representa o uso de uma vaga de estacionamento.
 * 
 * Esta classe permite registrar a entrada, saída e cálculo do valor pago por um
 * cliente ao utilizar uma vaga de estacionamento.
 */
public class UsoDeVagaCarro implements Serializable, UsoDeVaga {

	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private List<ServicosAdicionais> servicosAdicionais;

	/**
	 * Cria uma instância de UsoDeVaga associada a uma vaga específica.
	 * 
	 * @param vaga A vaga de estacionamento utilizada.
	 */
	public UsoDeVagaCarro(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.servicosAdicionais = new ArrayList<>();
	}

	public UsoDeVagaCarro(Vaga vaga, LocalDateTime entrada, LocalDateTime saida, double valorPago,
			List<ServicosAdicionais> servicosAdicionais) {
		this.vaga = vaga;
		this.entrada = entrada;
		this.saida = saida;
		this.valorPago = valorPago;
		this.servicosAdicionais = servicosAdicionais;

	}

	public UsoDeVagaCarro(Vaga vaga, LocalDateTime entrada, List<ServicosAdicionais> servicosAdicionais) {
		this.vaga = vaga;
		this.saida = null;
		this.entrada = entrada;
		this.servicosAdicionais = servicosAdicionais;

	}

	/**
	 * Registra a saída do cliente da vaga e calcula o valor a ser pago.
	 * 
	 * @return O valor a ser pago pelo uso da vaga.
	 */

	public double sair(Cliente cliente) throws RuntimeException {
		this.saida = LocalDateTime.now();
		long minutosEstacionado = Duration.between(entrada, saida).toMinutes();

		for (ServicosAdicionais servico : servicosAdicionais) {
			if (servico == ServicosAdicionais.POLIMENTO && minutosEstacionado < 120) {
				throw new RuntimeException("Tempo mínimo de permanência para polimento não atendido.");
			} else if (servico == ServicosAdicionais.LAVAGEM && minutosEstacionado < 60) {
				throw new RuntimeException("Tempo mínimo de permanência para lavagem não atendido.");
			}
		}

		double valorAPagar;

		switch (cliente.getModalidade()) {
			case HORISTA:
				valorAPagar = calcularValorHorista(minutosEstacionado);
				break;
			case DE_TURNO:
				valorAPagar = calcularValorDeTurno(minutosEstacionado, cliente.getTurnoEscolhido());
				break;
			case MENSALISTA:
				valorAPagar = 0.0; // Mensalistas não pagam pelo estacionamento por tempo.
				break;
			default:
				throw new RuntimeException("Modalidade de cliente desconhecida.");
		}

		// Adicione o valor dos serviços adicionais
		double valorServicosAdicionais = servicosAdicionais.stream()
				.mapToDouble(ServicosAdicionais::getValor)
				.sum();

		valorAPagar += valorServicosAdicionais;

		this.vaga.sair();
		this.valorPago = valorAPagar;
		return valorAPagar;
	}

	private double calcularValorHorista(long minutosEstacionado) {
		return (minutosEstacionado / 15) * VALOR_FRACAO;
	}

	private double calcularValorDeTurno(long minutosEstacionado, Turno turnoEscolhido) {
		if (minutosEstacionado <= Duration.between(turnoEscolhido.getInicio(), turnoEscolhido.getFim()).toMinutes()) {
			return 0.0; // Está dentro do turno, não paga estacionamento.
		} else {
			return calcularValorHorista(minutosEstacionado);
		}
	}

	/**
	 * @param servico
	 */
	public void adicionarServicos(ServicosAdicionais servico) {
		servicosAdicionais.add(servico);
	}

	/**
	 * @return double
	 */
	public double valorPago() {
		return valorPago;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public List<ServicosAdicionais> getServicosAdicionais() {
		return servicosAdicionais;
	}

	public static double getValorFracao() {
		return VALOR_FRACAO;
	}

	public static double getValorMaximo() {
		return VALOR_MAXIMO;
	}

}
