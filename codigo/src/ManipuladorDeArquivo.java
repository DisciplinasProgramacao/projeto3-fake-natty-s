package src;

import java.io.*;

public class ManipuladorDeArquivo {

    private static final String NOME_DO_ARQUIVO = "estacionamentos.ser";

    public static void salvar(Estacionamento[] estacionamentos) throws IOException {

        try (FileOutputStream fileOut = new FileOutputStream(NOME_DO_ARQUIVO)) {
            try(ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(estacionamentos);
            }
        }

    }

    public static Estacionamento[] carregar() throws IOException, ClassNotFoundException {

        Estacionamento[] estacionamentos;

        try (FileInputStream fileIn = new FileInputStream(NOME_DO_ARQUIVO)) {
             try(ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                estacionamentos = (Estacionamento[]) objectIn.readObject();
            }
        }

        return estacionamentos;

    }

    /*  MÉTODO GLOBAL PARA QUALQUER TIPO DE OBJETO
    public static <T extends Serializable> void escreverObjeto(T objeto) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(NOME_DO_ARQUIVO))) {
            outputStream.writeObject(objeto);
            System.out.println("Objeto gravado com sucesso em " + NOME_DO_ARQUIVO);
        } catch (IOException e) {
            System.err.println("Erro ao gravar o objeto em " + NOME_DO_ARQUIVO + ": " + e.getMessage());
        }
    }

    public static <T extends Serializable> T lerObjeto(Class<T> classeDoObjeto) {
        T objeto = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(NOME_DO_ARQUIVO))) {
            Object obj = inputStream.readObject();
            if (classeDoObjeto.isInstance(obj)) {
                objeto = classeDoObjeto.cast(obj);
            } else {
                throw new ClassCastException("O objeto lido não é do tipo esperado.");
            }
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Erro ao ler o objeto de " + NOME_DO_ARQUIVO + ": " + e.getMessage());
        }
        return objeto;
    }
    */
}
