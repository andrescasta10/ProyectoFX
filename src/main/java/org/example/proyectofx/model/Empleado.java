// Empleado.java
package org.example.proyectofx.model;

public abstract class Empleado {
    private final int id;
    private String nombre;

    public Empleado(int id, String nombre) {
        if (id <= 0) throw new IllegalArgumentException("ID de empleado inválido");
        if (nombre == null) throw new IllegalArgumentException("Nombre no puede ser nulo");
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) throw new IllegalArgumentException("Nombre no puede ser nulo");
        this.nombre = nombre;
    }

    /**
     * Autenticación siempre exitosa; el empleado entra libremente.
     */
    public boolean authenticate() {
        return true;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

