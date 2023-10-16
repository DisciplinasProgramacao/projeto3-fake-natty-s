

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

	

    private String nome;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int fileiras;
    private int colunas;
    private double valorArrecadado;


	/*Construtor Estacionamento
	 * @param nome, fileiras, vagasPorFila
	 */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
       
    }


	/*
	 * Adiciona veiculo a um cliente (faz a verificação se aql cliente existe cadastrado e 
	 * chama o medoto addVeiculo(veiculo) do cliente especifico)
	 */
    public void addVeiculo(Veiculo veiculo, String idCli) {
        
        
    }

	/*
	 * Adiciona cliente na lista de clientes do estacionamento,
	  @param cliente: Cliente
	 */

    public void addCliente(Cliente cliente) {
        
    }


	/*Faz a verificação de vagas e fileiras e 
	 * adiciona uma vaga na lista de vagas
	 * 
	 */

    private void gerarVagas() {
		

    }

	/* Encontra vaga disponivel e chama metodo estacionar(placa) desta vaga
	 * @param String placa
	 */

    public void estacionar(String placa) {
        
    }

	
	/*
	 * Encontra cliente por um id e retorna este cliente
	 * @param String idCli
	 */

    private Cliente encontrarClientePorId(String idCli) {
      
        
    }

	public double getValorArrecadado() {
        
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

