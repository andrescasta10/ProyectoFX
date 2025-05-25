// LibroDigital.java
package org.example.proyectofx.model;

public class LibroDigital extends Libro {

    private Formato formato;      // PDF, EPUB, MOBI
    private String enlaceDescarga; // URL o ruta

    public LibroDigital(int id, String titulo, String autor, String genero, int anioPublicacion, Estado estado,
                        Formato formato, String enlaceDescarga) {
        super(id, titulo, autor, genero, anioPublicacion, estado);
        if (formato == null || enlaceDescarga == null)
            throw new IllegalArgumentException("Formato y enlace no pueden ser nulos");
        this.formato = formato;
        this.enlaceDescarga = enlaceDescarga;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        if (formato == null) throw new IllegalArgumentException("Formato no puede ser nulo");
        this.formato = formato;
    }

    public String getEnlaceDescarga() {
        return enlaceDescarga;
    }

    public void setEnlaceDescarga(String enlaceDescarga) {
        if (enlaceDescarga == null) throw new IllegalArgumentException("Enlace no puede ser nulo");
        this.enlaceDescarga = enlaceDescarga;
    }

    @Override
    public void setEstado(Estado estado) {
        if (estado == Estado.REFERENCIA) {
            throw new IllegalArgumentException("Un Libro Digital no puede ser de referencia");
        }
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "LibroDigital{" +
                super.toString() +
                ", formato=" + formato +
                ", enlaceDescarga='" + enlaceDescarga + '\'' +
                '}';
    }
}
