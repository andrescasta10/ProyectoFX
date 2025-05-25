// Libro.java
package org.example.proyectofx.model;

public abstract class Libro {
    protected int id;
    protected int anioPublicacion;
    protected String titulo;
    protected String autor;
    protected String genero;
    protected Estado estado;

    // Constructor de la clase Libro
    public Libro(int id, String titulo, String autor, String genero, int anioPublicacion, Estado estado) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido");
        if (titulo == null || autor == null || genero == null)
            throw new IllegalArgumentException("Título, autor y género no pueden ser nulos");
        if (anioPublicacion <= 0) throw new IllegalArgumentException("Año de publicación inválido");
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
        this.estado = estado;
    }

    // Gets y sets
    public int getID() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido");
        this.id = id;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion <= 0) throw new IllegalArgumentException("Año inválido");
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null) throw new IllegalArgumentException("Título no puede ser nulo");
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null) throw new IllegalArgumentException("Autor no puede ser nulo");
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null) throw new IllegalArgumentException("Género no puede ser nulo");
        this.genero = genero;
    }

    public Estado getEstado() {
        return estado;
    }

    /**
     * Sólo las subclases que permiten préstamo pueden cambiar el estado.
     * LibroReferencia ignora este método si se intenta cambiar a DISPONIBLE o PRESTADO.
     */
    public abstract void setEstado(Estado estado);

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", anioPublicacion=" + anioPublicacion +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", estado=" + estado +
                '}';
    }
}

