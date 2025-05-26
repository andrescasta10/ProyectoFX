// Visitante.java
package org.example.proyectofx.model;

public class Visitante extends Usuario {

    public Visitante(String nombre, int id, String correo, String contrasenia) {
        super(nombre, id, correo, contrasenia);
    }

    @Override
    public int getMaximoLibrosPermitidos() {
        return 0; // No puede tomar libros prestados
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 0;
    }

    @Override
    public void buscarLibro(Bibliotecario bibliotecario, Libro libro) {
        boolean encontrado = false;
        for (Libro l : bibliotecario.getListaLibros()) {
            if (l.getID() == libro.getID()) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            System.out.println("El libro consultado se encuentra en la biblioteca");
        } else {
            System.out.println("El libro consultado no se encuentra en la biblioteca");
        }
    }
}

