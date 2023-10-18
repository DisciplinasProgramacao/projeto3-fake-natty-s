import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Classe que representa o uso de uma vaga de estacionamento.
 * 
 * Esta classe permite registrar a entrada, saída e cálculo do valor pago por um
 * cliente ao utilizar uma vaga de estacionamento.
 */
public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private boolean manobrista;
	private boolean lavagem;
	private boolean polimento;

	/**
     * Cria uma instância de UsoDeVaga associada a uma vaga específica.
     * 
     * @param vaga A vaga de estacionamento utilizada.
     */
	public UsoDeVaga(Vaga vaga, boolean manobrista, boolean lavagem, boolean polimento) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.manobrista = manobrista;
		this.lavagem = lavagem;
		this.polimento = polimento;
	}

	/**
     * Registra a saída do cliente da vaga e calcula o valor a ser pago.
	 * 
     * @param manobrista Informa se o serviço do manobrista foi contratado
     * @param lavagem Informa se o serviço de lavagem foi contratado
	 * @param polimento Informa se o serviço do polimento foi contratado
	 * 
     * @return O valor a ser pago pelo uso da vaga.
     */
	public double sair(boolean manobrista, boolean lavagem, boolean polimento) {
		if (this.saida == null) {
			this.saida = LocalDateTime.now();
			long minutosEstacionado = Duration.between(entrada, saida).toMinutes();
			double valorAPagar = (minutosEstacionado / 15) * VALOR_FRACAO;
			valorAPagar = Math.min(valorAPagar, VALOR_MAXIMO);
			if(manobrista) {
				valorAPagar += 5.0;
			}
			if(lavagem && minutosEstacionado > 59) {
				valorAPagar += 20.0;
			}
			if(polimento && minutosEstacionado > 119) {
				valorAPagar += 45.0;
			}
			this.valorPago = valorAPagar;
			return valorAPagar;
		} else {
			System.out.println("O veículo já saiu da vaga.");
			return 0.0; 
		}
	}
	
	/**
     * Obtém o valor total pago pelo cliente pelo uso da vaga.
     * 
     * @return O valor total pago.
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

	public boolean getManobrista() {
		return manobrista;
	}

	public void setManobrista(boolean manobrista) {
		this.manobrista = manobrista;
	}

	public boolean getLavagem() {
		return lavagem;
	}

	public void setLavagem(boolean lavagem) {
		this.lavagem = lavagem;
	}

	public boolean getPolimento() {
		return polimento;
	}

	public void setPolimento(boolean polimento) {
		this.polimento = polimento;
	}

}
