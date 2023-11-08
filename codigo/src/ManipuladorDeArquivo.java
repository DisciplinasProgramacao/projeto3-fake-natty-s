package src;

import java.io.*;

public class ManipuladorDeArquivo {

    public static final String ARQ_ESTACIONAMENTO1 = "codigo/files/estacionamento1.ser";
    public static final String ARQ_ESTACIONAMENTO2 = "codigo/files/estacionamento2.ser";
    public static final String ARQ_ESTACIONAMENTO3 = "codigo/files/estacionamento3.ser";
    
    public static <T extends Serializable> void escreverObjeto(String nomeArquivo, T objeto) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(objeto);
            System.out.println("Objeto gravado com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o objeto em " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public static <T extends Serializable> T lerObjeto(String nomeArquivo) {
        T objeto = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {

        } catch (IOException | ClassCastException e) {
            System.err.println("Erro ao ler o objeto de " + nomeArquivo + ": " + e.getMessage());
        }
        return objeto;
    }
    
}