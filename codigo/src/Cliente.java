package src;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import src.ManipuladorDeArquivo;


/**
 * Classe que representa um cliente e seus veículos.
 */
public class Cliente implements Serializable {

    private String nome;
    private String id;
    private List<Veiculo> veiculos;
    private double totalArrecadadoveiculo = 0.0;

    /**
     * Construtor da classe Cliente.
     * 
     * @param nome Nome do cliente.
     * @param id   ID do cliente.
     */
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<Veiculo>();
    }

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     * 
     * @param veiculo Veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     * 
     * @param placa Placa do veículo.
     * @return      O veículo com a placa especificada ou nulo se não encontrado.
     */
    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    /**
     * Calcula o total de usos de todos os veículos do cliente.
     * 
     * @return O número total de usos.
     */
    public int totalDeUsos() {
        int total = 0;
        for (Veiculo veiculo : veiculos) {
            total += veiculo.totalDeUsos();
        }
        return total;
    }

    /**
     * Calcula a arrecadação de um veículo específico com base na placa.
     * 
     * @param placa Placa do veículo.
     * @return      O valor total arrecadado pelo veículo.
     */
    public double arrecadadoPorVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
               totalArrecadadoveiculo = veiculo.totalArrecadado();
               return totalArrecadadoveiculo;
            }
        }
        return totalArrecadadoveiculo;
    }

    /**
     * Calcula a arrecadação total de todos os veículos do cliente.
     * 
     * @return O valor total arrecadado.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (Veiculo veiculo : veiculos) {
            totalArrecadado += veiculo.totalArrecadado();
        }
        return totalArrecadado;
    }

    /**
     * Calcula a arrecadação total do cliente no mês especificado.
     * 
     * @param mes Mês para o qual a arrecadação será calculada.
     * @return    O valor total arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (Veiculo veiculo : veiculos) {
            arrecadadoMes += veiculo.arrecadadoNoMes(mes);
        }
        return arrecadadoMes;
    }

    /**
     * Obtém o histórico de usos de todas as vagas de estacionamento deste cliente.
     * 
     * @return Uma lista de usos de vaga do cliente.
    */
    public List<UsoDeVaga> obterHistoricoDeUsos() {
        List<UsoDeVaga> historico = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            historico.addAll(veiculo.getUsos());
        }
        return historico;
    }

    /**
     * Obtém o nome do cliente.
     * 
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     * 
     * @param nome Novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o ID do cliente.
     * 
     * @return O ID do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o ID do cliente.
     * 
     * @param id Novo ID do cliente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtém a lista de veículos do cliente.
     * 
     * @return A lista de veículos do cliente.
     */
    public ArrayList<Veiculo> getVeiculos() {
        return (ArrayList<Veiculo>) veiculos;
    }

    /**
     * Define a lista de veículos do cliente.
     * 
     * @param veiculos Nova lista de veículos do cliente.
     */
    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
