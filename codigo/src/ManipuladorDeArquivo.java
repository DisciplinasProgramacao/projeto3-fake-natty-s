package src;

import java.io.*;
import java.util.List;

public class ManipuladorDeArquivo {

    public static final String ARQ_CLIENTES = "codigo/files/clientes.ser";
    public static final String ARQ_ESTACIONAMENTOS = "codigo/files/estacionamentos.ser";
    public static final String ARQ_VAGAS = "codigo/files/vagas.ser";
    public static final String ARQ_VEICULOS = "codigo/files/veiculos.ser";

    
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

    public static <T extends Serializable> void escreverObjeto(String nomeArquivo, T objeto) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(objeto);
            System.out.println("Objeto gravado com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o objeto em " + nomeArquivo + ": " + e.getMessage());
        }
    }

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
    

}
