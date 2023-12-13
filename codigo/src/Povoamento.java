package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.Exceptions.ExcecaoCadastrarVeiculoExistente;
import src.Exceptions.ExcecaoClientejaExistente;
import src.Exceptions.ExcecaoEstacionarSemSair;
import src.Exceptions.ExcecaoSairFinalizada;
import src.dao.SerializationUtils;
import src.entities.*;
import src.enums.ServicosAdicionais;
import src.interfaces.Veiculo;

public class Povoamento {

    public static void popular()
            throws ExcecaoClientejaExistente, ExcecaoEstacionarSemSair, ExcecaoCadastrarVeiculoExistente,
            ExcecaoSairFinalizada {

        // Criando os 3 estacionamentos
        Estacionamento est1 = new Estacionamento("Estacionamento A", 5, 10);
        Estacionamento est2 = new Estacionamento("Estacionamento B", 10, 15);
        Estacionamento est3 = new Estacionamento("Estacionamento C", 7, 12);

        // Salvando os estacionamentos
        SerializationUtils<Estacionamento> serializableEstacionamento = new SerializationUtils<>();
        serializableEstacionamento.add(est1);
        serializableEstacionamento.add(est2);
        serializableEstacionamento.add(est3);

        // Criando 10 clientes com diferentes planos de uso
        Cliente cliente1 = new Cliente("Cliente1", "1");
        cliente1.setModalidadeByNumber(1);
        cliente1.setTurnoByNumber(1);

        Cliente cliente2 = new Cliente("Cliente2", "2");
        cliente2.setModalidadeByNumber(2);
        cliente2.setTurnoByNumber(2);

        Cliente cliente3 = new Cliente("Cliente3", "3");
        cliente3.setModalidadeByNumber(3);
        cliente3.setTurnoByNumber(3);

        Cliente cliente4 = new Cliente("Cliente4", "4");
        cliente4.setModalidadeByNumber(1);
        cliente4.setTurnoByNumber(2);

        Cliente cliente5 = new Cliente("Cliente5", "5");
        cliente5.setModalidadeByNumber(2);
        cliente5.setTurnoByNumber(3);

        Cliente cliente6 = new Cliente("Cliente6", "6");
        cliente6.setModalidadeByNumber(3);
        cliente6.setTurnoByNumber(1);

        Cliente cliente7 = new Cliente("Cliente7", "7");
        cliente7.setModalidadeByNumber(1);
        cliente7.setTurnoByNumber(3);

        Cliente cliente8 = new Cliente("Cliente8", "8");
        cliente8.setModalidadeByNumber(2);
        cliente8.setTurnoByNumber(1);

        Cliente cliente9 = new Cliente("Cliente9", "9");
        cliente9.setModalidadeByNumber(3);
        cliente9.setTurnoByNumber(2);

        Cliente cliente10 = new Cliente("Cliente10", "10");
        cliente10.setModalidadeByNumber(1);
        cliente10.setTurnoByNumber(2);

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2, cliente3, cliente4, cliente5,
                cliente6, cliente7, cliente8, cliente9, cliente10);

        // Adicionando os estacionamentos a uma lista
        List<Estacionamento> estacionamentos = Arrays.asList(est1, est2, est3);

        // Adicionar todos os clientes em cada estacionamento

        est1.addCliente(cliente1);
        est1.addCliente(cliente2);
        est1.addCliente(cliente3);
        est1.addCliente(cliente4);
        est2.addCliente(cliente5);
        est2.addCliente(cliente6);
        est2.addCliente(cliente7);
        est3.addCliente(cliente8);
        est3.addCliente(cliente9);
        est3.addCliente(cliente10);

        Carro carro1 = new Carro("ABC123");
        Carro carro2 = new Carro("DEF456");
        Carro carro3 = new Carro("GHI789");
        Carro carro4 = new Carro("JKL012");
        Carro carro5 = new Carro("MNO345");
        Carro carro6 = new Carro("PQR678");
        Carro carro7 = new Carro("STU901");
        Carro carro8 = new Carro("VWX234");
        Carro carro9 = new Carro("YZA567");
        Carro carro10 = new Carro("BCD890");

        est3.addVeiculo(carro10, "10");
        est3.addVeiculo(carro9, "9");
        est3.addVeiculo(carro8, "8");
        est2.addVeiculo(carro7, "7");
        est2.addVeiculo(carro6, "6");
        est2.addVeiculo(carro5, "5");
        est1.addVeiculo(carro4, "4");
        est1.addVeiculo(carro3, "3");
        est1.addVeiculo(carro2, "2");
        est1.addVeiculo(carro1, "1");

        List<Carro> carros = Arrays.asList(carro1, carro2, carro3, carro4, carro5,
                carro6, carro7, carro8, carro9,
                carro10);

        List<ServicosAdicionais> servicosAdicionais1 = Arrays.asList(ServicosAdicionais.MANOBRISTA,
                ServicosAdicionais.LAVAGEM);
        List<ServicosAdicionais> servicosAdicionais2 = Arrays.asList(ServicosAdicionais.LAVAGEM);
        List<ServicosAdicionais> servicosAdicionais3 = Arrays.asList(ServicosAdicionais.POLIMENTO);
        
        for (Cliente cliente : est1.getClientes()) {
            for (Veiculo carroz : cliente.getVeiculos()){
                est1.estacionar(carroz.getPlaca(), servicosAdicionais1);
                est1.sair(carroz.getPlaca());
            }
        }

        for (Cliente cliente : est2.getClientes()) {
            for (Veiculo carroz : cliente.getVeiculos()){
                est2.estacionar(carroz.getPlaca(), servicosAdicionais1);
                est2.sair(carroz.getPlaca());
            }
        }

        for (Cliente cliente : est3.getClientes()) {
            for (Veiculo carroz : cliente.getVeiculos()){
                est3.estacionar(carroz.getPlaca(), servicosAdicionais1);
                est3.sair(carroz.getPlaca());
            }
        }

        serializableEstacionamento.update(estacionamentos);
    }

    public static void main(String[] args)
            throws ExcecaoClientejaExistente, ExcecaoEstacionarSemSair, ExcecaoCadastrarVeiculoExistente,
            ExcecaoSairFinalizada {
        popular();
    }
}
