package src;

import java.io.*;

public class ManipuladorDeArquivo {

    public static final String ARQ_CLIENTE = "codigo/files/cliente.ser";
    public static final String ARQ_ESTACIONAMENTO = "codigo/files/estacionamento.ser";
    public static final String ARQ_USODEVAGA = "codigo/files/usodevaga.ser";
    public static final String ARQ_VAGA = "codigo/files/vaga.ser";
    public static final String ARQ_VEICULO = "codigo/files/veiculo.ser";
    

    public static <T extends Serializable> void escreverObjeto(String nomeArquivo, T objeto) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(objeto);
            System.out.println("Objeto gravado com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o objeto em " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public static <T extends Serializable> T lerObjeto(String nomeArquivo, Class<T> classeDoObjeto) {
        T objeto = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            Object obj = inputStream.readObject();
            if (classeDoObjeto.isInstance(obj)) {
                objeto = classeDoObjeto.cast(obj);
            } else {
                throw new ClassCastException("O objeto lido não é do tipo esperado.");
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Erro ao ler o objeto de " + nomeArquivo + ": " + e.getMessage());
        }
        return objeto;
    }
    
}
