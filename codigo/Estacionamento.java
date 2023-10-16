

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
        this.nome = nome;
        this.fileiras = fileiras;
        this.colunas = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.vagas = new ArrayList<>();
        this.valorArrecadado = 0;
        gerarVagas();
    }


	/*
	 * Adiciona veiculo a um cliente (faz a verificação se aql cliente existe cadastrado e 
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
	  @param cliente: Cliente
	 */

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }


	/*Faz a verificação de vagas e fileiras e 
	 * adiciona uma vaga na lista de vagas
	 * 
	 */

    private void gerarVagas() {
		
		int fila = 1;
		int posicaoLista;
		for(int i = 0; i <= fileiras; i++){

			for(int j = 0; j <= colunas; j++){
				posicaoLista = i * j + (j * i - 1);
				if(vagas.get(posicaoLista) == null){
					Vaga vaga = new Vaga(i, j);
					vagas.add(vaga);
				}
			}
			fila++;
		}
    }

	/* Encontra vaga disponivel e chama metodo estacionar(placa) desta vaga
	 * @param String placa
	 */

    public void estacionar(String placa) {
        for (Vaga vaga : vagas) { //procura vaga
            if (vaga.disponivel()) {
                vaga.estacionar(placa);
                valorArrecadado += 4; // Adicionar ao valor arrecadado a cada 15 minutos
                break;
            }
        }
    }

	/*
	 * Encontra cliente por um id e retorna este cliente
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

