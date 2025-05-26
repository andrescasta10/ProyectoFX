// Docente.java
package org.example.proyectofx.model;

public class Docente extends Usuario implements IUsuario {

    public Docente(String nombre, int id, String correo, String contrasenia) {
        super(nombre, id, correo, contrasenia);
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

    @Override
    public boolean solicitarPrestamo(Bibliotecario bibliotecario, Libro libro) throws Exception {
        return bibliotecario.registrarPrestamo(this, libro);
    }
}

