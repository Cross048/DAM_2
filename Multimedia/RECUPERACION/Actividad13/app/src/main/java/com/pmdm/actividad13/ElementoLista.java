package com.pmdm.actividad13;

public class ElementoLista {
    private String titulo;
    private String cuerpo;
    private String footer;

    public ElementoLista(String titulo) {
        this.titulo = titulo;
    }

    public ElementoLista(String titulo, String cuerpo, String footer) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.footer = footer;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
