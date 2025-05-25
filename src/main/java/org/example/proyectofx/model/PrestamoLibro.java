// PrestamoLibro.java
package org.example.proyectofx.model;

public class PrestamoLibro {
    private String idTransaccion;
    private String fechaPrestamo;
    private String fechaVencimiento; // fecha de vencimiento
    private String fechaDevolucion;  // se asigna cuando se devuelve
    private Libro libro;
    private Usuario usuario;
    private boolean devuelto;

    // Constructor de la clase PrestamoLibro
    public PrestamoLibro(String idTransaccion, String fechaPrestamo, String fechaVencimiento, Libro libro, Usuario usuario) {
        if (idTransaccion == null || fechaPrestamo == null || fechaVencimiento == null || libro == null || usuario == null) {
            throw new IllegalArgumentException("Parámetros del préstamo no pueden ser nulos");
        }
        this.idTransaccion = idTransaccion;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaDevolucion = null;
        this.libro = libro;
        this.usuario = usuario;
        this.devuelto = false;
    }

    // Gets y sets
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    @Override
    public String toString() {
        return "PrestamoLibro{" +
                "idTransaccion='" + idTransaccion + '\'' +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", fechaDevolucion='" + fechaDevolucion + '\'' +
                ", libro=" + libro +
                ", usuario=" + usuario +
                ", devuelto=" + devuelto +
                '}';
    }
}

