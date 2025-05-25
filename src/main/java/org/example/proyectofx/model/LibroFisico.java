// LibroFisico.java
package org.example.proyectofx.model;

public class LibroFisico extends Libro {

    private int numeroPaginas;
    private String editorial;
    private String ubicacionEstanteria;

    public LibroFisico(int id, String titulo, String autor, String genero, int anioPublicacion, Estado estado,
                       int numeroPaginas, String editorial, String ubicacionEstanteria) {
        super(id, titulo, autor, genero, anioPublicacion, estado);
        if (numeroPaginas <= 0) throw new IllegalArgumentException("Número de páginas inválido");
        if (editorial == null || ubicacionEstanteria == null)
            throw new IllegalArgumentException("Editorial y ubicación no pueden ser nulos");
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.ubicacionEstanteria = ubicacionEstanteria;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) throw new IllegalArgumentException("Número de páginas inválido");
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        if (editorial == null) throw new IllegalArgumentException("Editorial no puede ser nulo");
        this.editorial = editorial;
    }

    public String getUbicacionEstanteria() {
        return ubicacionEstanteria;
    }

    public void setUbicacionEstanteria(String ubicacionEstanteria) {
        if (ubicacionEstanteria == null) throw new IllegalArgumentException("Ubicación no puede ser nulo");
        this.ubicacionEstanteria = ubicacionEstanteria;
    }

    @Override
    public void setEstado(Estado estado) {
        if (estado == Estado.REFERENCIA) {
            throw new IllegalArgumentException("Un Libro Físico no puede ser de referencia");
        }
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "LibroFisico{" +
                super.toString() +
                ", numeroPaginas=" + numeroPaginas +
                ", editorial='" + editorial + '\'' +
                ", ubicacionEstanteria='" + ubicacionEstanteria + '\'' +
                '}';
    }
}
