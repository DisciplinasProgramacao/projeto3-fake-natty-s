package src.entities;

import java.util.List;
import javax.swing.text.html.HTMLDocument.BlockElement;

import src.enums.ServicosAdicionais;
import src.interfaces.Observador;
import src.interfaces.UsoDeVaga;
import src.interfaces.Veiculo;
import src.Exceptions.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Carro implements Serializable, Veiculo {

    private String placa;
    private List<UsoDeVaga> usos;
    private List<Observador> observadores;

    public Carro(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<UsoDeVaga>();
        this.observadores = new ArrayList<>();
    }

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param vaga A vaga onde o veículo deve estacionar.
     */
    @Override
    public void estacionar(Vaga vaga, List<ServicosAdicionais> servicosAdicionais) throws ExcecaoEstacionarSemSair {
        UsoCarroFactoryImp factory = new UsoCarroFactoryImp();
        for (UsoDeVaga usoDeVaga : usos) {
            if (!usoDeVaga.getSaida().isBefore(LocalDateTime.now()) || usoDeVaga.getSaida() == null) {
                throw new ExcecaoEstacionarSemSair(usoDeVaga.getVaga());
            }

        }

        if (vaga.disponivel()) {
            usos.add(factory.criarUsoDeVaga(vaga, LocalDateTime.now(), null, 0, servicosAdicionais));
            vaga.estacionar();
        } else {
            throw new ExcecaoEstacionarSemSair(vaga);
        }

    }

    /**
     * Registra a saída do veículo de uma vaga e calcula o valor a ser pago.
     * 
     * @param vaga A vaga da qual o veículo está saindo.
     * @return O valor a ser pago pelo uso da vaga.
     */
    @Override
public double sair(Vaga vaga, Cliente cliente) throws ExcecaoSairFinalizada {
    double totalPago = 0.0;
    boolean encontrado = false;
    boolean foiEstacionado = false;
    
    for (UsoDeVaga usoDeVaga : usos) {
        if (usoDeVaga.getVaga() == vaga) {
            
            foiEstacionado = true;
            
            if (usoDeVaga.getSaida() == null) {
                
                encontrado = true;
                totalPago = usoDeVaga.sair(cliente);
                System.out.println("total pago: " + totalPago);
            }
        }
    }


    if (!foiEstacionado) {
        throw new ExcecaoSairFinalizada(vaga);
    } else if (!encontrado) {
        throw new ExcecaoSairFinalizada(vaga);
    }

    this.notificarObservadores();
    
    return totalPago; 
}

    /**
     * Calcula o valor total arrecadado pela empresa de estacionamento com este
     * veículo.
     * 
     * @return O valor total arrecadado.
     */
    @Override
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
    @Override
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
    @Override
    public int totalDeUsos() {
        return usos.size();
    }

    /**
     * @return String
     */
    @Override
    public String getPlaca() {
        return placa;
    }

    /**
     * @return List<UsoDeVaga>
     */
    @Override
    public List<UsoDeVaga> getUsos() {
        return usos;
    }

    @Override
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public void setUsos(List<UsoDeVaga> usos) {
        this.usos = usos;
    }

    @Override
    public void addUsos(UsoDeVaga uso) {
        this.usos.add(uso);
    }

    @Override
    public void notificarObservadores() {
        observadores.stream().forEach(observador -> observador.atualizar(LocalDateTime.now().getMonthValue()));
    }

    @Override
    public void addObserver(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObserver(Observador observador) {
        observadores.remove(observador);
    }

}
