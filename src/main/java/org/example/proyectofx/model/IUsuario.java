package org.example.proyectofx.model;

public interface IUsuario {
    /**
     * Solicita un préstamo. Lanza excepción si no se puede concretar.
     */
    boolean solicitarPrestamo(Biblioteca biblioteca, Libro libro) throws Exception;
}