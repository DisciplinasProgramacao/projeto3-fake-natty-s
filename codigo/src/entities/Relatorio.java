package src.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.interfaces.*;

public class Relatorio implements Serializable, Observador {
    List<Observavel> observaveis;
    Map<Integer, List<Cliente>> top5clientes;
    Estacionamento estacionamento;
    
    public Relatorio(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
        this.observaveis = new ArrayList<>();
        top5clientes = new HashMap<>();
    }

    @Override
    public void atualizar(int mes)  {
        List<Cliente> topClientes = estacionamento.top5Clientes(mes);
        top5clientes.put(mes, topClientes);
    }

    public List<Observavel> getObservaveis() {
        return observaveis;
    }

    public void setObservaveis(List<Observavel> observaveis) {
        this.observaveis = observaveis;
    }

    public Map<Integer, List<Cliente>> getTop5clientes() {
        return top5clientes;
    }

    public void setTop5clientes(Map<Integer, List<Cliente>> top5clientes) {
        this.top5clientes = top5clientes;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }
    
    public void addObservavel(Observavel observavel) {
        observaveis.add(observavel);
        observavel.addObserver(this);
    }

}
