package src.entities;

import java.util.List;
import java.util.Optional;
import java.io.Serializable;
import java.util.stream.Collectors;

import src.enums.ModalidadeCliente;
import src.enums.Turno;
import src.interfaces.UsoDeVaga;
import src.interfaces.Veiculo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe que representa um cliente e seus veículos.
 */
public class Cliente implements Serializable {

    private String nome;
    private String id;
    private List<Veiculo> veiculos;
    private ModalidadeCliente modalidade;
    private Turno turnoEscolhido;
   


    /**
     * Construtor da classe Cliente.
     *
     * @param nome Nome do cliente.
     * @param id   ID do cliente.
     */


    public Cliente(String nome, String id, List<Veiculo> veiculos, ModalidadeCliente modalidade, Turno turnoEscolhido) {
        this.nome = nome;
        this.id = id;
        this.veiculos = veiculos;
        this.modalidade = modalidade;
        this.turnoEscolhido = turnoEscolhido;
    }
    

    public Cliente(String nome, String id) { //passar a modalidae do cliente
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
        this.modalidade = modalidade;
    }


    /**
     * Adiciona um veículo à lista de veículos do cliente.
     *
     * @param veiculo Veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo) {
       this.veiculos.add(veiculo);
    }

    public void setModalidadeByNumber(int i){
        switch (i) {
            case 1:
                this.modalidade = ModalidadeCliente.HORISTA;
                break;
            case 2:
                this.modalidade = ModalidadeCliente.DE_TURNO;
                break;
            case 3:
                this.modalidade = ModalidadeCliente.MENSALISTA;
                break;
            default:
                break;
        }
    }

    public void setTurnoByNumber(int i){
        switch (i) {
            case 1:
                this.turnoEscolhido =  Turno.MANHA;
                break;
            case 2:
                this.turnoEscolhido = Turno.TARDE;
                break;
            case 3:
                this.turnoEscolhido = Turno.NOITE;
                break;
            default:
                break;
        }
    }


    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param placa Placa do veículo.
     * @return      O veículo com a placa especificada ou nulo se não encontrado.
     */
    public Veiculo possuiVeiculo(String placa) {
        return veiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst().orElse(null);
    }


/** 
 * @return int
 */
/* 
    public int totalDeUsosNoMes(int mes){
        
    }*/

    /**
     * Calcula o total de usos de todos os veículos do cliente.
     *
     * @return O número total de usos.
     */
    public int totalDeUsos() {
        return veiculos.stream().mapToInt(Veiculo::totalDeUsos).sum();
    }




    /**
     * Calcula a arrecadação de um veículo específico com base na placa.
     *
     * @param placa Placa do veículo.
     * @return      O valor total arrecadado pelo veículo.
     */
    public double arrecadadoPorVeiculo(String placa) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equals(placa))
                .findFirst()
                .map(Veiculo::totalArrecadado)
                .orElse(7.0);
    }



    /**
     * Calcula a arrecadação total de todos os veículos do cliente.
     *
     * @return O valor total arrecadado.
     */


 
     public double arrecadadoTotal() {
        return veiculos.stream()
                .mapToDouble(Veiculo::totalArrecadado)
                .sum();
    }
 


    /**
     * Calcula a arrecadação total do cliente no mês especificado.
     *
     * @param mes Mês para o qual a arrecadação será calculada.
     * @return    O valor total arrecadado no mês especificado.
     */


     public double arrecadadoNoMes(int mes) {
        return veiculos.stream()
        .mapToDouble(veiculo -> veiculo.arrecadadoNoMes(mes))
        .sum();
    }











    /**
     * Obtém o histórico de usos de todas as vagas de estacionamento deste cliente.
     *
     * @return Uma lista de usos de vaga do cliente.
    */
    public List<UsoDeVaga> obterHistoricoDeUsos() {
        return veiculos.stream()
                .flatMap(veiculo -> veiculo.getUsos().stream())
                .collect(Collectors.toList());
    }





   /**
     * Adiciona uma lista de veículos à lista de veículos do cliente.
     *
     * @param novosVeiculos Lista de veículos a serem adicionados.
     */
    public void adicionarVeiculos(List<Carro> novosVeiculos) {
        veiculos.addAll(novosVeiculos);
    }


    /**
     * Remove um veículo da lista de veículos do cliente com a placa especificada.
     *
     * @param placa Placa do veículo a ser removido.
     */
    public void removerVeiculo(String placa) {
        veiculos.removeIf(veiculo -> veiculo.getPlaca().equals(placa));
    }


    /**
     * Busca e retorna uma lista de veículos com a placa especificada.
     *
     * @param placa Placa do veículo a ser buscado.
     * @return      Lista de veículos com a placa especificada.
     */
    public Veiculo buscarVeiculoPorPlaca(String placa) {
        Optional<Veiculo> optionalVeiculo = veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
        return optionalVeiculo.orElse(null);
    }




    /**
     * Ordena os veículos com base no total de usos de forma decrescente.
     *
     * @return Lista de veículos ordenados por total de usos.
     */
    public List<Veiculo> ordenarVeiculosPorTotalDeUso() {
        return veiculos.stream()
                .sorted(Comparator.comparing(Veiculo::totalDeUsos).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Busca e retorna uma lista de veículos com arrecadação no mês especificado.
     *
     * @param mes Mês para o qual a arrecadação será verificada.
     * @return    Lista de veículos com arrecadação no mês especificado.
     */
    public List<Veiculo> buscarVeiculosPorArrecadadoMes(int mes) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.arrecadadoNoMes(mes) > 0)
                .collect(Collectors.toList());
    }


    /**
     * Altera a placa de um veículo de uma placa antiga para uma nova placa.
     *
     * @param placaAntiga Placa antiga a ser alterada.
     * @param novaPlaca   Nova placa a ser atribuída ao veículo.
     */
    public void alterarPlacaDoVeiculo(String placaAntiga, String novaPlaca) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placaAntiga);
        if(veiculo != null){
            veiculo.setPlaca(novaPlaca);
        }


    }

    public void attach(Carro veiculo){
        veiculos.add(veiculo);


    }

    public void notificar(Carro veiculo){
        //
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
    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
   
    /**
     * Define a lista de veículos do cliente.
     *
     * @param veiculos Nova lista de veículos do cliente.
     */
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ModalidadeCliente getModalidade() {
        return modalidade;
    }

    public void setModalidade(ModalidadeCliente modalidade) {
        this.modalidade = modalidade;
    }

     public Turno getTurnoEscolhido() {
        return turnoEscolhido;
    }
    
    public String getTurnoToString(){
        return this.turnoEscolhido.toString();
    }
    public String getModalidadeToString(){
        return this.modalidade.toString();
    }

    public void setTurnoEscolhido(Turno turnoEscolhido) {
        this.turnoEscolhido = turnoEscolhido;
    }
}
    
    


