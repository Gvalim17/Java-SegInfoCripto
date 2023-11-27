package modelo;

public class ValorPadrao {

    private int id;
    private String estado;
    private int limiteMax;
    private int limiteMin;
    private String unidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getLimiteMax() {
        return limiteMax;
    }

    public void setLimiteMax(int limiteMax) {
        this.limiteMax = limiteMax;
    }

    public int getLimiteMin() {
        return limiteMin;
    }

    public void setLimiteMin(int limiteMin) {
        this.limiteMin = limiteMin;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
