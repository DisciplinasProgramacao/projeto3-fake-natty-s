package src;

import java.util.List;
import src.Exceptions.ExcecaoEstacionarSemSair;
import src.Exceptions.ExcecaoSairFinalizada;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Representa um veículo que pode estacionar em vagas e registrar seu uso.
 */
public class Veiculo {

    private String placa;
    private List<UsoDeVaga> usos;

    /**
     * Construtor para a classe Veiculo.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
    }

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param vaga A vaga onde o veículo deve estacionar.
     * @throws ExcecaoEstacionarSemSair Exceção lançada se o veículo tentar estacionar sem ter saído de uma vaga anterior.
     */
    public void estacionar(Vaga vaga) throws ExcecaoEstacionarSemSair {
        for (UsoDeVaga usoDeVaga : usos) {

            if (!usoDeVaga.getSaida().isBefore(LocalDateTime.now()) || usoDeVaga.getSaida() == null) {
                throw new ExcecaoEstacionarSemSair(usoDeVaga.getVaga());
            } else {
                if (vaga.disponivel()) {
                    vaga.estacionar();
                } else {
                    System.out.println("A vaga não está disponível.");
                }
            }

        }

    }

    /**
     * Registra a saída do veículo de uma vaga e calcula o valor a ser pago.
     * 
     * @param vaga A vaga da qual o veículo está saindo.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws ExcecaoSairFinalizada Exceção lançada se o veículo tentar sair de uma vaga que já foi finalizada.
     */
    public double sair(Vaga vaga) throws ExcecaoSairFinalizada {
        boolean veiculoEstacionadoNaVaga = false;
        for (UsoDeVaga usoDeVaga : usos) {
            if (usoDeVaga.getVaga() == vaga) {
                if (usoDeVaga.getSaida().isBefore(LocalDateTime.now())) {
                    throw new ExcecaoSairFinalizada(vaga);
                } else {
                    veiculoEstacionadoNaVaga = true;

                    double valorPago = usoDeVaga.sair();

                    return valorPago;
                }

            }
        }

        if (!veiculoEstacionadoNaVaga) {
            System.out.println("O veículo não está estacionado na vaga.");
        }

        return 0.0;
    }

    /**
     * Classe interna para representar um registro de estacionamento.
     */
    public static class RegistroEstacionamento {
        private String data;
        private double valor;

        public RegistroEstacionamento(String data, double valor) {
            this.data = data;
            this.valor = valor;
        }

        public String getData() {
            return data;
        }

        public double getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return "Data: " + data + ", Valor: " + valor;
        }
    }

    private List<RegistroEstacionamento> registros;

    /**
     * Construtor para a classe RegistroEstacionamento.
     *
     * @param registros Uma lista de registros de estacionamento.
     */
    public Veiculo(List<RegistroEstacionamento> registros) {
        this.registros = registros;
    }

    /**
     * Gera um relatório de estacionamento ordenado por data crescente ou valor decrescente.
     * 
     * @param ordenarPorDataCrescente True para ordenar por data crescente, False para ordenar por valor decrescente.
     * @return Uma lista de registros de estacionamento ordenada de acordo com a opção selecionada.
     */
    public List<RegistroEstacionamento> gerarRelatorio(boolean ordenarPorDataCrescente) {
        return registros.stream()
            .sorted(ordenarPorDataCrescente ? Comparator.comparing(RegistroEstacionamento::getData) : Comparator.comparing(RegistroEstacionamento::getValor).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Calcula o valor total arrecadado pela empresa de estacionamento com este veículo.
     * 
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
     * 
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
     * 
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
