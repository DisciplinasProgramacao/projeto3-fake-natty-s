package src.entities;

import java.util.List;
import src.Exceptions.*;
import src.interfaces.Entidade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Veiculo implements Serializable, Entidade{

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
    public void estacionar(Vaga vaga) throws ExcecaoEstacionarSemSair {
        for (UsoDeVaga usoDeVaga : usos) {

            if (!usoDeVaga.getSaida().isBefore(LocalDateTime.now()) || usoDeVaga.getSaida() == null) {
                throw new ExcecaoEstacionarSemSair(usoDeVaga.getVaga());
            } else {
                if (vaga.disponivel()) {
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
    public void sair(Vaga vaga) throws ExcecaoSairFinalizada {
        
        for (UsoDeVaga usoDeVaga : usos) {
            if (usoDeVaga.getVaga() == vaga) {
                if (usoDeVaga.getSaida().isBefore(LocalDateTime.now())) {
                    throw new ExcecaoSairFinalizada(vaga);
                } else {
                    usoDeVaga.sair();
                    
                }
            }
        }
         
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

    @Override
    public String getId() {
        return this.placa;
    }

    public void addUsos(UsoDeVaga uso){
        this.usos.add(uso);
    }

    
}
