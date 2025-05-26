// Biblioteca.java
package org.example.proyectofx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private String nombre;



    // Constructor de la clase Biblioteca
    public Biblioteca(String nombre) {
        this.nombre = nombre;

    }

    public Usuario verificarCredenciales(String correo, String contrasenia) throws Exception{
        if (correo == null || correo.isEmpty()) throw new Exception("Verifique el correo ingresado");
        if (contrasenia == null || contrasenia.isEmpty()) throw new Exception("Verifique la contraseña ingresada");

        Usuario usuario = buscarUsuarioporCorreo(correo);

        if(usuario == null) throw new Exception("El correo no existe");
        if (!usuario.getContrasenia().equals(contrasenia)) throw new Exception("La contraseña es incorrecta");
        return usuario;
    }

    public Usuario buscarUsuarioporCorreo(Bibliotecario bibliotecario, String correo){
        for (Usuario usuario : bibliotecario.getListaUsuarios){
            if (usuario.getCorreo().equals(correo)){
                return usuario;
            }
        }
        return null;
    }

    // ----- Gestión de Empleados -----



    // ----- Gestión de Libros -----





    // ----- Gestión de Usuarios -----



    // ----- Gestión de Préstamos -----

    /**
     * Crea un préstamo si:
     * 1) El libro existe y está DISPONIBLE.
     * 2) El usuario existe, no tiene deudas y no supera su límite.
     */

    /**
     * Registra la devolución:
     * 1) Busca el préstamo activo (mismo libro y usuario, y devuelto == false).
     * 2) Marca el préstamo como devuelto y actualiza fecha de devolución.
     * 3) Cambia el estado del libro a DISPONIBLE.
     * 4) Si hay atraso, marca deuda en el usuario.
     */


    // ----- Reportes mínimos -----







    // ----- Getters y Setters -----

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return "Biblioteca{" +
                "nombre='" + nombre + '\'' +
                ", empleados=" + listaEmpleados +
                ", libros=" + listaLibros +
                ", usuarios=" + listaUsuarios +
                ", prestamos=" + listaPrestamos +
                '}';
    }
}
