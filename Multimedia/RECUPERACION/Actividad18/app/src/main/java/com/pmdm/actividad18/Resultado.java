package com.pmdm.actividad18;

public class Resultado {
    private String pais;
    private String interprete;
    private String cancion;
    private int votosJurado;
    private int votosAudiencia;

    public Resultado(String pais, String interprete, String cancion, int votosJurado, int votosAudiencia) {
        this.pais = pais;
        this.interprete = interprete;
        this.cancion = cancion;
        this.votosJurado = votosJurado;
        this.votosAudiencia = votosAudiencia;
    }

    // Getters
    public String getPais() {
        return pais;
    }

    public String getInterprete() {
        return interprete;
    }

    public String getCancion() {
        return cancion;
    }

    public int getVotosJurado() {
        return votosJurado;
    }

    public int getVotosAudiencia() {
        return votosAudiencia;
    }

    // Setters
    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setInterprete(String interprete) {
        this.interprete = interprete;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public void setVotosJurado(int votosJurado) {
        this.votosJurado = votosJurado;
    }

    public void setVotosAudiencia(int votosAudiencia) {
        this.votosAudiencia = votosAudiencia;
    }
}
