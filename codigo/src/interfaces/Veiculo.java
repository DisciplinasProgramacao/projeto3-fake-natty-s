package src.interfaces;

import java.util.List;


import src.enums.ServicosAdicionais;
import src.Exceptions.*;
import src.entities.*;


public interface Veiculo extends Observavel {
    
    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param vaga A vaga onde o veículo deve estacionar.
     */
    public void estacionar(Vaga vaga, List<ServicosAdicionais> servicosAdicionais) throws ExcecaoEstacionarSemSair;

    /**
     * Registra a saída do veículo de uma vaga e calcula o valor a ser pago.
     * 
     * @param vaga A vaga da qual o veículo está saindo.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(Vaga vaga, Cliente cliente) throws ExcecaoSairFinalizada;
    

    /**
     * Calcula o valor total arrecadado pela empresa de estacionamento com este
     * veículo.
     * 
     * @return O valor total arrecadado.
     */
    public double totalArrecadado();

    /**
     * Calcula o valor arrecadado pela empresa de estacionamento com este veículo em
     * um mês específico.
     * 
     * @param mes O mês para o qual o valor arrecadado deve ser calculado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes);

    /**
     * Obtém o número total de usos de vaga feitos por este veículo.
     * 
     * @return O número total de usos de vaga.
     */
    public int totalDeUsos();

    
    /** 
     * @return String
     */
    public String getPlaca();
    
    /** 
     * @return List<UsoDeVaga>
     */
    public List<UsoDeVaga> getUsos();

    public void setPlaca(String placa);

    public void setUsos(List<UsoDeVaga> usos);

    public void addUsos(UsoDeVaga uso);

    public void notificarObservadores();

    public void addObserver(Observador observador);
    
    public void removeObserver(Observador observador);
}
