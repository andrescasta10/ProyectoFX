// Bibliotecario.java
package org.example.proyectofx.model;

public class Bibliotecario extends Empleado {

    public Bibliotecario(int id, String nombre) {
        super(id, nombre);
    }

    /** Agrega libro mediante Biblioteca */
    public void agregarLibroSistema(Biblioteca biblioteca, Libro libro) throws Exception {
        biblioteca.registrarLibro(libro);
    }

    /** Agrega usuario mediante Biblioteca */
    public void agregarUsuarioSistema(Biblioteca biblioteca, Usuario usuario) throws Exception {
        biblioteca.registrarUsuario(usuario);
    }

    /** Presta libro mediante Biblioteca */
    public boolean prestarLibroSistema(Biblioteca biblioteca, Usuario usuario, Libro libro) throws Exception {
        return biblioteca.registrarPrestamo(usuario, libro);
    }

    /** Devuelve libro mediante Biblioteca */
    public void devolverLibroSistema(Biblioteca biblioteca, int usuarioId, int libroId) throws Exception {
        biblioteca.registrarDevolucion(usuarioId, libroId);
    }

    /** Método que muestra el estado de un libro */
    public void actualizarEstado(Libro libro) {
        System.out.println("Este libro se encuentra " + libro.getEstado());
    }

    /** Reporte: libros prestados */
    public void reporteLibrosPrestado(Biblioteca biblioteca) {
        System.out.println("Los libros prestados son:");
        biblioteca.reporteLibrosPrestados();
    }

    /** Reporte: libros disponibles */
    public void reporteLibrosDisponibles(Biblioteca biblioteca) {
        System.out.println("Los libros disponibles son:");
        biblioteca.reporteLibrosDisponibles();
    }

    /** Reporte: usuarios con deudas */
    public void reporteUsuarioConDeuda(Biblioteca biblioteca) {
        System.out.println("Los usuarios con deudas son:");
        biblioteca.reporteUsuariosConDeuda();
    }
}

