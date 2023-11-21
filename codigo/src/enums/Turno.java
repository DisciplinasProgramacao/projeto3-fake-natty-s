package src.enums;

import java.time.LocalTime;

public enum Turno {
    MANHA("Manhã", "08:00", "12:00"),
    TARDE("Tarde", "12:01", "18:00"),
    NOITE("Noite", "18:01", "23:59");

    private final String nome;
    private final LocalTime inicio;
    private final LocalTime fim;


     public String getNome() {
        return nome;
    }  
   public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFim() {
        return fim;
    }

    Turno(String nome, String inicio, String fim) {
        this.nome = nome;
        this.inicio = LocalTime.parse(inicio);
        this.fim = LocalTime.parse(fim);
    }

 
}
