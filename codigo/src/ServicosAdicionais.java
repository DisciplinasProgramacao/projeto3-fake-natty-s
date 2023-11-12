package src;

import java.io.Serializable;

public enum ServicosAdicionais implements Serializable {
    MANOBRISTA("Manobrista", 5.0, 0),
    LAVAGEM("Lavagem", 20.0, 60),
    POLIMENTO("Polimento (inclui lavagem)", 45.0, 120);

    private final String descricao;
    private final double valor;
    private final int tempoMinimo;

    ServicosAdicionais(String descricao, double valor, int tempoMinimo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tempoMinimo = tempoMinimo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getTempoMinimo() {
        return tempoMinimo;
    }
}
