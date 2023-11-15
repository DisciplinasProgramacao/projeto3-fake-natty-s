package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe fornece métodos utilitários para a manipulação de arquivos,
 * incluindo a escrita e leitura de objetos serializáveis.
 */

public class ManipuladorDeArquivo {

    // Constantes com os caminhos dos arquivos
    public static final String ARQ_CLIENTES = "codigo/files/clientes.ser";
    public static final String ARQ_ESTACIONAMENTOS = "codigo/files/estacionamentos.ser";
    public static final String ARQ_VAGAS = "codigo/files/vagas.ser";
    public static final String ARQ_VEICULOS = "codigo/files/veiculos.ser";

    /**
     * Escreve um objeto serializável no arquivo especificado.
     *
     * @param nomeArquivo O caminho do arquivo.
     * @param objeto      O objeto a ser gravado.
     * @param <T>         O tipo de objeto.
     */
    public static <T extends Serializable> void escreverObjetos(String nomeArquivo, T objeto) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo, true))) {
            outputStream.writeObject(objeto);
            System.out.println("Objetos adicionados com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gravar os objetos em " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public static <T extends Serializable> void escreverObjeto(String nomeArquivo, T objeto) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo, true))) {
            outputStream.writeObject(objeto);
            System.out.println("Objeto gravado com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o objeto em " + nomeArquivo + ": " + e.getMessage());
        }
    }

    /**
     * Lê um objeto do arquivo especificado.
     *
     * @param nomeArquivo    O caminho do arquivo.
     * @param classeDoObjeto A classe do objeto a ser lido.
     * @param <T>            O tipo de objeto.
     * @return O objeto lido.
     */
    public static <T> T lerObjeto(String nomeArquivo, Class<T> classeDoObjeto) {
        T objeto = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            Object obj = inputStream.readObject();
            if (classeDoObjeto.isInstance(obj)) {
                objeto = classeDoObjeto.cast(obj);
            } else {
                System.err.println("O objeto lido não é do tipo esperado: " + classeDoObjeto.getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler o objeto de " + nomeArquivo + ": " + e.getMessage());
        }
        return objeto;
    }

    /**
     * Lê todos os registros do arquivo especificado.
     *
     * @param nomeArquivo O caminho do arquivo.
     * @param classe      A classe dos registros a serem lidos.
     * @param <T>         O tipo de objeto.
     * @return Uma lista dos registros lidos.
     */
    public static <T extends Serializable> List<T> lerTodosRegistros(String nomeArquivo, Class<T> classe) {
        List<T> registros = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            Object obj = inputStream.readObject();
            if (obj instanceof List) {
                List<?> lista = (List<?>) obj;
                for (Object item : lista) {
                    if (classe.isInstance(item)) {
                        registros.add(classe.cast(item));
                    } else {
                        System.err.println("O objeto lido não é do tipo esperado: " + classe.getName());
                    }
                }
            } else {
                System.err.println("O conteúdo do arquivo não é uma lista serializada.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler registros de " + nomeArquivo + ": " + e.getMessage());
        }
        return registros;
    }

    public static String getArqClientes() {
        return ARQ_CLIENTES;
    }

    public static String getArqEstacionamentos() {
        return ARQ_ESTACIONAMENTOS;
    }

    public static String getArqVagas() {
        return ARQ_VAGAS;
    }

    public static String getArqVeiculos() {
        return ARQ_VEICULOS;
    }
}
