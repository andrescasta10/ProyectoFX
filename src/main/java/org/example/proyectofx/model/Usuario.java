// Usuario.java
package org.example.proyectofx.model;

public abstract class Usuario {
    private String nombre;
    private int id;
    private String correo;
    private String contrasenia;
    private boolean deuda;

    // Constructor de la clase Usuario
    public Usuario(String nombre, int id, String correo, String contrasenia) {
        if (nombre == null || correo == null || contrasenia == null)
            throw new IllegalArgumentException("Nombre, correo y contraseña no pueden ser nulos");
        if (id <= 0) throw new IllegalArgumentException("ID inválido");
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.deuda = false;
    }

    // Gets y sets
    public void setNombre(String nombre) {
        if (nombre == null) throw new IllegalArgumentException("Nombre no puede ser nulo");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCorreo(String correo) {
        if (correo == null) throw new IllegalArgumentException("Correo no puede ser nulo");
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setContrasenia(String contrasenia) {
        if (contrasenia == null) throw new IllegalArgumentException("Contraseña no puede ser nula");
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setDeuda(boolean deuda) {
        this.deuda = deuda;
    }

    public boolean tieneDeuda() {
        return deuda;
    }

    public abstract int getMaximoLibrosPermitidos();

    public abstract int getDiasMaximosPrestamo();

    public abstract void buscarLibro(Bibliotecario bibliotecario, Libro libro);

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", id=" + id +
                ", deuda=" + deuda +
                '}';
    }
}


