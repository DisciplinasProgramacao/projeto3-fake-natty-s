package src.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtils<T> {

    private final String arquivo = "dados.ser";  // Nome do arquivo constante
    private List<T> objetos;  // Lista para armazenar os objetos

    public SerializationUtils() {
        this.objetos = new ArrayList<>();
        carregarDados();  // Carregar dados do arquivo ao inicializar a classe
    }

    public void add(T objeto) {
        objetos.add(objeto);
        salvarDados();  // Salvar dados após adicionar um objeto
    }

    public List<T> getAll() {
        return new ArrayList<>(objetos);  // Retorna uma cópia da lista para evitar modificações externas
    }

    public void update(List<T> novosObjetos) {
        objetos = novosObjetos;
        salvarDados();  // Atualiza a lista e salva os dados no arquivo
    }

    // Métodos privados para salvar e carregar dados do arquivo
    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(objetos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                objetos = (List<T>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar exceções na leitura (pode ocorrer se o arquivo ainda não existir)
        }
    }
}
