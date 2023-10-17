import java.util.Arrays;
import java.util.Comparator;

public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;
	private int valorTotal;
	private int valorMes;
	private int valorUso;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		this.valorTotal = 0;
		this.valorMes = 0;
		this.valorUso = 0;
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
		for (Vaga vaga : vagas){
			if(vaga.disponivel()) {
				vaga.sair()
				break;
			}
		}
	}

	public double totalArrecadado() {
		for (Cliente cliente : clientes) {
			valorTotal += cliente.getArrecadadoTotal();
		}
		return valorTotal;
	}

	public double arrecadacaoNoMes(int mes) {
		for (Cliente cliente : clientes) {
			 int mesCliente = cliente.GetData().getMonth();
			 if (mesCliente == mes) {
				 valorMes += cliente.getArrecadadoNoMes();
			 }
			 return valorMes;
		}
	}

	public double valorMedioPorUso() {
		for (Cliente cliente : clientes) {
			valorUso = cliente.getArrecadadoPorVeiculo() / cliente.getTotalDeUsos();
		}
		return valorUso;
	}

	public String top5Clientes(int mes) {
		Cliente[] clientesOrdenados = Arrays.copyOf(clientes, clientes.length);
    Arrays.sort(clientesOrdenados, new Comparator<Cliente>() {
        @Override
        public int compare(Cliente c1, Cliente c2) {
            return c2.totalDeUsos() - c1.totalDeUsos();
        }
    });
	StringBuilder top5 = new StringBuilder("Top 5 Clientes:\n");
    for (int i = 0; i < 5 && i < clientesOrdenados.length; i++) {
        Cliente cliente = clientesOrdenados[i];
        top5.append("Nome: ").append(cliente.getNome()).append(", Total de Usos: ").append(cliente.totalDeUsos()).append("\n");
    }

    return top5.toString();
	}

}
