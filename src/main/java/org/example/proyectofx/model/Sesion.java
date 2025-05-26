package org.example.proyectofx.model;

import lombok.Getter;
import lombok.Setter;

public class Sesion {
    public static Sesion INSTANCIA;

    @Getter
    @Setter
    private Usuario usuario;

    private Sesion() {
    }

    /**
     * Método Get para la instancia
     */
    public static Sesion getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Sesion();
        }
        return INSTANCIA;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}
