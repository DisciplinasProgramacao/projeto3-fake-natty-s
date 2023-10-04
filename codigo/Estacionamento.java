

public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		
	}

	public void addVeiculo(Veiculo veiculo, String idCli) {
		
	}

	public void addCliente(Cliente cliente) {
		
	}

	private void gerarVagas() {
		
	}

	public void estacionar(String placa) {
		
	}

	public double sair(String placa) {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public int getQuantFileiras(){
		return quantFileiras;
	}

	public void setQuantFIleiras(int quantFileiras){
		this.quantFileiras = quantFileiras;
	}

	public int getVagasPorFileira(){
		return vagasPorFileira;
	}

	public void setVagasPorFIleira(int vagasPorFileira){
		this.vagasPorFileira = vagasPorFileira;
	}

	

}
