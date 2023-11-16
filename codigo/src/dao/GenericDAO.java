package src.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.interfaces.DAO;
import src.interfaces.Entidade;

/**
 * Classe genérica de um DAO que manipula objetos serializáveis em um arquivo.
 * 
 * @param <T> Tipo de objeto a ser manipulado
 * @param <K> Tipo da chave do objeto
 */
public class GenericDAO<T extends Serializable, K> implements DAO<T, K>, Closeable {
    private final File file;
    private ObjectOutputStream outputStream;

    /**
     * Construtor da classe que inicializa o DAO com o nome do arquivo.
     * 
     * @param filename Nome do arquivo a ser utilizado para armazenar os objetos
     * @throws IOException se houver um problema ao criar ou acessar o arquivo
     */
    public GenericDAO(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        outputStream = new ObjectOutputStream(new FileOutputStream(file, true));
    }

    /**
     * Adiciona um objeto ao armazenamento persistente.
     *
     * @param object O objeto a ser adicionado.
     * @throws IOException Se ocorrer um erro de I/O ao gravar o objeto no disco.
     */
    @Override
    public void add(T object) {
        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Erro ao gravar o objeto no disco!");
            e.printStackTrace();
        }
    }

    /**
     * Obtém um objeto com base na chave fornecida.
     *
     * @param key A chave para buscar o objeto.
     * @return O objeto correspondente à chave ou null se não encontrado.
     */
    @Override
    public T get(K key) {
        T object = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object obj = inputStream.readObject();
                if (obj instanceof Serializable) {
                    @SuppressWarnings("unchecked")
                    T temp = (T) obj;
                    Entidade entidade = (Entidade) temp;
                    if (entidade.getId().equals(key)) {
                        object = temp;
                        break;
                    }
                }
            }
        } catch (EOFException e) {
            // Fim do arquivo, objeto não encontrado
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o objeto do disco!");
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Obtém uma lista contendo todos os objetos armazenados.
     *
     * @return Uma lista com todos os objetos armazenados.
     */
    @Override
    public List<T> getAll() {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object obj = inputStream.readObject();
                if (obj instanceof Serializable) {
                    objects.add((T) obj);
                }
            }
        } catch (EOFException e) {
            // Fim do arquivo
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler objetos do disco!");
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * Atualiza um objeto no armazenamento persistente.
     *
     * @param updatedObject O objeto a ser atualizado.
     */
    @Override
    public void update(T updatedObject, K key) {
        List<T> objects = getAll();
        boolean found = false;
    
        for (int i = 0; i < objects.size(); i++) {
            T object = objects.get(i);
            if (object instanceof Entidade) {
                Entidade entidade = (Entidade) object;
                if (entidade.getId().equals(key)) {
                    objects.set(i, updatedObject);
                    found = true;
                    break;
                }
            }
        }
    
        if (!found) {
            System.out.println("Objeto não encontrado para atualização");
        } else {
            saveToFile(objects);
        }
    }
    

    /**
     * Remove um objeto do armazenamento persistente.
     *
     * @param object O objeto a ser removido.
     */
    @Override
    public void delete(T object) {
        List<T> objects = getAll();
        Iterator<T> iterator = objects.iterator();
        while (iterator.hasNext()) {
            T temp = iterator.next();
            if (temp.equals(object)) {
                iterator.remove();
                break;
            }
        }
        saveToFile(objects);
    }

    /**
     * Salva a lista de objetos no arquivo no armazenamento persistente.
     *
     * @param objects Lista de objetos a serem salvos.
     */
    private void saveToFile(List<T> objects) {
        try {
            close();
            outputStream = new ObjectOutputStream(new FileOutputStream(file, false));

            for (T obj : objects) {
                outputStream.writeObject(obj);
                outputStream.flush();
            }
        } catch (IOException e) {
            System.err.println("Erro ao gravar objetos no disco!");
            e.printStackTrace();
        }
    }

    /**
     * Fecha o fluxo de saída utilizado para escrever no arquivo.
     *
     * @throws IOException Se ocorrer um erro de I/O ao fechar o fluxo.
     */
    @Override
    public void close() throws IOException {
        if (outputStream != null) {
            outputStream.close();
        }
    }

    /**
     * Libera recursos antes que o objeto seja destruído pelo coletor de lixo.
     *
     * @throws Throwable Se ocorrer um erro durante a finalização do objeto.
     */
    @Override
    protected void finalize() throws Throwable {
        this.close();
    }
}
