package com.pmdm.actividad13;

public class ElementoLista {
    private String titulo;
    private String cuerpo;
    private String footer;
    private int imagen;

    public ElementoLista(String titulo, int imagen) {
        this.titulo = titulo;
        this.cuerpo = "";
        this.footer = "";
        this.imagen = imagen;
    }

    public ElementoLista(String titulo, String cuerpo, String footer, int imagen) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.footer = footer;
        this.imagen = imagen;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
