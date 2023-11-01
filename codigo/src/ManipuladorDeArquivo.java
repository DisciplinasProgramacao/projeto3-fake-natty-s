package src;

import java.io.*;
import java.util.List;

public class ManipuladorDeArquivo {

    private static final String NOME_DO_ARQUIVO = "estacionamentos.ser";

    public static void salvar(List<Estacionamento> estacionamentos) throws IOException {

        try (FileOutputStream fileOut = new FileOutputStream(NOME_DO_ARQUIVO)) {
            try(ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(estacionamentos);
            }
        }
    }

    public static List<Estacionamento> carregar() throws IOException, ClassNotFoundException {

        List<Estacionamento> estacionamentos;

        try (FileInputStream fileIn = new FileInputStream(NOME_DO_ARQUIVO)) {
             try(ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
                estacionamentos = (List<Estacionamento>) objectIn.readObject();
            }
        }

        return estacionamentos;

    }

}
