package org.example.proyectofx.model;

public class PrestamoLibro {
    private String fechaPrestamo, fechaDevolucion;
    private static Libro libro;
    private Usuario usuario;
    private Estado estado;

    public PrestamoLibro (String fechaPrestamo, String fechaDevolucion, Libro libro, Usuario usuario){
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libro = libro;
        this.usuario = usuario;
        this.setEstado(Estado.PRESTAMO);
    }

    public static void devolverLibro(){
        if(libro.getEstado() == Estado.PRESTAMO){
            libro.setEstado(Estado.DISPONIBLE);
        }
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "PrestamoLibro{" +
                "fechaPrestamo='" + fechaPrestamo + '\'' +
                ", fechaDevolucion='" + fechaDevolucion + '\'' +
                ", libro=" + libro +
                ", usuario=" + usuario +
                '}';
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }
}
