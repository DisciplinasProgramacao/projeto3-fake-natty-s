package src;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o uso de uma vaga de estacionamento.
 * 
 * Esta classe permite registrar a entrada, saída e cálculo do valor pago por um
 * cliente ao utilizar uma vaga de estacionamento.
 */
public class UsoDeVaga {

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
	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.servicosAdicionais = new ArrayList<>();
	}

	/**
	 * Registra a saída do cliente da vaga e calcula o valor a ser pago.
	 * 
	 * @return O valor a ser pago pelo uso da vaga.
	 */
	public double sair() {
		this.saida = LocalDateTime.now();
		long minutosEstacionado = Duration.between(entrada, saida).toMinutes();
		double valorAPagar = (minutosEstacionado / 15) * VALOR_FRACAO;
		valorAPagar = Math.min(valorAPagar, VALOR_MAXIMO);
	
		// Adicione o valor dos serviços adicionais
		double valorServicosAdicionais = servicosAdicionais.stream()
				.mapToDouble(ServicosAdicionais::getValor)
				.sum();
	
		valorAPagar += valorServicosAdicionais; // Adiciona o valor dos serviços ao valor total
	
		this.valorPago = valorAPagar;
		return valorAPagar;
	}
	

	public void adicionarServicos(ServicosAdicionais servico) {
		servicosAdicionais.add(servico);
	}

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

}
