package src.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import src.Exceptions.ExcecaoMesInvalido;
import src.interfaces.*;
import src.*;

public class Relatorio implements Serializable, Observador {
    List<Observavel> observaveis;
    Map<Integer, Set<Cliente>> top5clientes;
    Estacionamento estacionamento;
    
    public Relatorio(Estacionamento estacionamento, Observavel observavel) {
        this.estacionamento = estacionamento;
        this.observaveis = new ArrayList<>();
        this.observaveis.add(observavel);
        observavel.addObserver(this);
        top5clientes = new HashMap<>();
    }

    @Override
    public void atualizar(int mes) throws ExcecaoMesInvalido {
        Set<Cliente> topClientes = estacionamento.top5Clientes(mes);
        top5clientes.put(mes, topClientes);
    }

    public List<Observavel> getObservaveis() {
        return observaveis;
    }

    public void setObservaveis(List<Observavel> observaveis) {
        this.observaveis = observaveis;
    }

    public Map<Integer, Set<Cliente>> getTop5clientes() {
        return top5clientes;
    }

    public void setTop5clientes(Map<Integer, Set<Cliente>> top5clientes) {
        this.top5clientes = top5clientes;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }
    
}
