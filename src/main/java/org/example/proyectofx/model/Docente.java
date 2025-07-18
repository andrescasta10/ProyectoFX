// Docente.java
package org.example.proyectofx.model;

public class Docente extends Usuario implements IUsuario {

    public Docente(String nombre, int id, String correo, String contrasenia, boolean deuda) {
        super(nombre, id, correo, contrasenia, deuda);
    }

    @Override
    public int getMaximoLibrosPermitidos() {
        return 5;
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 30;
    }

    @Override
    public void buscarLibro(Biblioteca biblioteca, Libro libro) {
        boolean encontrado = false;
        for (Libro l : biblioteca.getListaLibros()) {
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

    @Override
    public boolean solicitarPrestamo(Biblioteca biblioteca, Libro libro) throws Exception {
        return biblioteca.registrarPrestamo(this, libro);
    }
}

