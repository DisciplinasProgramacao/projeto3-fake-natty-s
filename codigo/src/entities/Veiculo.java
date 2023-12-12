package src.entities;

import java.util.List;

import javax.swing.text.html.HTMLDocument.BlockElement;

import src.enums.ServicosAdicionais;
import src.Exceptions.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Veiculo implements Serializable {

    private String placa;
    private List<UsoDeVaga> usos;
    

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
    }

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param vaga A vaga onde o veículo deve estacionar.
     */
    public void estacionar(Vaga vaga, List<ServicosAdicionais> servicosAdicionais) throws ExcecaoEstacionarSemSair {
        for (UsoDeVaga usoDeVaga : usos) {

            if (!usoDeVaga.getSaida().isBefore(LocalDateTime.now()) || usoDeVaga.getSaida() == null) {
                throw new ExcecaoEstacionarSemSair(usoDeVaga.getVaga());
            } else {
                if (vaga.disponivel()) {
                    usos.add(new UsoDeVaga(vaga, LocalDateTime.now(), servicosAdicionais));
                    vaga.estacionar();
                } else {
                    throw new ExcecaoEstacionarSemSair(usoDeVaga.getVaga());
                }
            }

        }

    }

    /**
     * Registra a saída do veículo de uma vaga e calcula o valor a ser pago.
     * 
     * @param vaga A vaga da qual o veículo está saindo.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public Double sair(Vaga vaga, Cliente cliente) throws ExcecaoSairFinalizada {
        Double totalPago = 0.0;
        Boolean encontrado = false;
        for (UsoDeVaga usoDeVaga : usos) {
            if (usoDeVaga.getVaga() == vaga) {
                if (usoDeVaga.getSaida() == null) {
                    encontrado = true;
                    totalPago = usoDeVaga.sair(cliente);
                } else {
                    
                    
                }
            }
        }

        if(!encontrado){
            throw new ExcecaoSairFinalizada(vaga);
        }
        return totalPago;
    }
    

    /**
     * Calcula o valor total arrecadado pela empresa de estacionamento com este
     * veículo.
     * 
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        return usos.stream().mapToDouble(UsoDeVaga::valorPago).sum();
    }

    /**
     * Calcula o valor arrecadado pela empresa de estacionamento com este veículo em
     * um mês específico.
     * 
     * @param mes O mês para o qual o valor arrecadado deve ser calculado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        return usos.stream()
                   .filter(uso -> uso.getEntrada().getMonthValue() == mes)
                   .mapToDouble(UsoDeVaga::valorPago)
                   .sum();
    }

    /**
     * Obtém o número total de usos de vaga feitos por este veículo.
     * 
     * @return O número total de usos de vaga.
     */
    public int totalDeUsos() {
        return usos.size();
    }

    
    /** 
     * @return String
     */
    public String getPlaca() {
        return placa;
    }

    
    /** 
     * @return List<UsoDeVaga>
     */
    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setUsos(List<UsoDeVaga> usos) {
        this.usos = usos;
    }

    public void addUsos(UsoDeVaga uso){
        this.usos.add(uso);
    }

    
}
