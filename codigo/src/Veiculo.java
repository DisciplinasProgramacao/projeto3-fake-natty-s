package src;
import java.util.List;
import java.util.ArrayList;

public class Veiculo {

    private String placa;
    private List<UsoDeVaga> usos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
    }

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * @param vaga A vaga onde o veículo deve estacionar.
     */
    public void estacionar(Vaga vaga) {
        if (vaga.disponivel()) {
            vaga.estacionar();
        } else {
            System.out.println("A vaga não está disponível.");
        }
    }

    /**
     * Registra a saída do veículo de uma vaga e calcula o valor a ser pago.
     * @param vaga A vaga da qual o veículo está saindo.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(Vaga vaga) {
        boolean veiculoEstacionadoNaVaga = false;
        for (UsoDeVaga usoDeVaga : usos) {
            if (usoDeVaga.getVaga() == vaga) {
                veiculoEstacionadoNaVaga = true;
                //ATENÇÃO AQUI GAARD. TEMOS QUE ANALISAR ESSES PARAMETROS. FOI MIGUE EM
                double valorPago = usoDeVaga.sair(true, false, true);
                usos.remove(usoDeVaga);
                return valorPago;
            }
        }
        
        if (!veiculoEstacionadoNaVaga) {
            System.out.println("O veículo não está estacionado na vaga.");
        }
        
        return 0.0;
    }

    /**
     * Calcula o valor total arrecadado pela empresa de estacionamento com este veículo.
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double totalValor = 0.0;
        for (UsoDeVaga usoDeVaga : usos) {
            totalValor += usoDeVaga.valorPago();
        }
        return totalValor;
    }

    /**
     * Calcula o valor arrecadado pela empresa de estacionamento com este veículo em um mês específico.
     * @param mes O mês para o qual o valor arrecadado deve ser calculado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double totalArrecadadoNoMes = 0.0;
        for (UsoDeVaga usoDeVaga : usos) {
            if (usoDeVaga.getEntrada().getMonthValue() == mes) {
                totalArrecadadoNoMes += usoDeVaga.valorPago();
            }
        }
        return totalArrecadadoNoMes;
    }

    /**
     * Obtém o número total de usos de vaga feitos por este veículo.
     * @return O número total de usos de vaga.
     */
    public int totalDeUsos() {
        return usos.size();
    }

    public String getPlaca() {
        return placa;
    }

    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setUsos(List<UsoDeVaga> usos) {
        this.usos = usos;
    }
}