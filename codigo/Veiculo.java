
public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos;

	
	public Veiculo(String placa) {
		this.placa = placa;
		this.usos = new ArrayList<UsoDeVaga>(); 
	}

	
	public void estacionar(Vaga vaga) {
		
		if (vaga.disponivel()) {
			vaga.estacionar(); 
		} else {
			System.out.println("A vaga não está disponível.");
		}
	}
	
	public double sair(Vaga vaga) {
		
		if (!vaga.disponivel()) {
			vaga.sair();
			
		} else {
			System.out.println("O veículo não está estacionado na vaga.");
		}
		
	}
	

	public double totalArrecadado() {
		double totalValor = 0.0;
		for (UsoDeVaga usoDeVaga : usos) {
			totalValor += usoDeVaga.valorPago();
		}
		return totalValor;
	}
	

	public double arrecadadoNoMes(int mes) {
		double totalArrecadadoNoMes = 0.0;
		for (UsoDeVaga usoDeVaga : usos) {
			if (usoDeVaga.getEntrada().getMonthValue() == mes) {
				totalArrecadadoNoMes += usoDeVaga.valorPago();
			}
		}
		return totalArrecadadoNoMes;
	}
	
	public int totalDeUsos() {
		return usos.length;
	}

	public String getPlaca() {
		return placa;
	}

	public UsoDeVaga[] getUsos() {
		return usos;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setUsos(UsoDeVaga[] usos) {
		this.usos = usos;
	}


}
