// Bibliotecario.java
package org.example.proyectofx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Empleado {
    private List<Libro> listaLibros;
    private List<Usuario> listaUsuarios;
    private List<PrestamoLibro> listaPrestamos; // lista de todos los préstamos
    public Bibliotecario(int id, String nombre) {
        super(id, nombre);
        this.listaLibros = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaPrestamos = new ArrayList<>();
    }

    public List<Libro> getListaLibros() {

        return listaLibros;
    }

    public List<Usuario> getListaUsuarios() {

        return listaUsuarios;
    }


    public List<PrestamoLibro> getListaPrestamos() {
        return listaPrestamos;
    }

    public boolean registrarPrestamo(Usuario usuario, Libro libro) throws Exception {
        // 1) Verificar que libro exista
        Libro libEncontrado = null;
        for (Libro l : listaLibros) {
            if (l.getID() == libro.getID()) {
                libEncontrado = l;
                break;
            }
        }
        if (libEncontrado == null) {
            throw new Exception("Libro con ID " + libro.getID() + " no encontrado.");
        }
        // No prestar libro de referencia
        if (libEncontrado.getEstado() == Estado.REFERENCIA) {
            throw new Exception("Este libro es de referencia y no puede ser prestado.");
        }
        // Verificar disponibilidad
        if (libEncontrado.getEstado() != Estado.DISPONIBLE) {
            throw new Exception("El libro no está disponible para préstamo.");
        }

        // 2) Verificar que usuario exista
        Usuario usrEncontrado = null;
        for (Usuario u : listaUsuarios) {
            if (u.getId() == usuario.getId()) {
                usrEncontrado = u;
                break;
            }
        }
        if (usrEncontrado == null) {
            throw new Exception("Usuario con ID " + usuario.getId() + " no encontrado.");
        }
        // Verificar deudas
        if (usrEncontrado.tieneDeuda()) {
            throw new Exception("El usuario tiene deudas pendientes.");
        }
        // Contar préstamos activos de este usuario
        int prestamosActivos = 0;
        for (PrestamoLibro p : listaPrestamos) {
            if (p.getUsuario().getId() == usuario.getId() && !p.isDevuelto()) {
                prestamosActivos++;
            }
        }
        if (prestamosActivos >= usrEncontrado.getMaximoLibrosPermitidos()) {
            throw new Exception("El usuario ha alcanzado el límite de préstamos activos.");
        }

        // 3) Crear el préstamo
        LocalDate hoy = LocalDate.now();
        LocalDate vencimiento = hoy.plusDays(usrEncontrado.getDiasMaximosPrestamo());
        String idTransaccion = usuario.getId() + "-" + libro.getID() + "-" + System.currentTimeMillis();
        PrestamoLibro nuevoPrestamo = new PrestamoLibro(idTransaccion, hoy.toString(), vencimiento.toString(), libEncontrado, usrEncontrado);
        listaPrestamos.add(nuevoPrestamo);

        // 4) Actualizar estado del libro
        libEncontrado.setEstado(Estado.PRESTADO);

        return true;
    }

    public boolean buscarLibro(Libro libro) {
        for (Libro libroB : listaLibros) {
            if (libro.getID() == libroB.getID()) {
                return true;
            }
        }
        return false;
    }


    public void registrarLibro(Libro libro) throws Exception {
        for (Libro l : listaLibros) {
            if (l.getID() == libro.getID()) {
                throw new Exception("Ya existe un libro con ID: " + libro.getID());
            }
        }
        listaLibros.add(libro);
    }

    public void registrarUsuario(Usuario usuario) throws Exception {
        for (Usuario u : listaUsuarios) {
            if (u.getId() == usuario.getId()) {
                throw new Exception("Ya existe un usuario con ID: " + usuario.getId());
            }
        }
        listaUsuarios.add(usuario);
    }


    public void registrarDevolucion(int usuarioId, int libroId) throws Exception {
        PrestamoLibro prestamoEncontrado = null;
        for (PrestamoLibro p : listaPrestamos) {
            if (p.getUsuario().getId() == usuarioId
                    && p.getLibro().getID() == libroId
                    && !p.isDevuelto()) {
                prestamoEncontrado = p;
                break;
            }
        }
        if (prestamoEncontrado == null) {
            throw new Exception("No existe préstamo activo para ese usuario y libro.");
        }

        // 1) Actualizar fecha de devolución y marcar devuelto
        LocalDate hoy = LocalDate.now();
        prestamoEncontrado.setFechaDevolucion(hoy.toString());
        prestamoEncontrado.setDevuelto(true);

        // 2) Actualizar estado del libro
        Libro libroDev = prestamoEncontrado.getLibro();
        libroDev.setEstado(Estado.DISPONIBLE);

        // 3) Verificar atraso
        LocalDate fechaVenc = LocalDate.parse(prestamoEncontrado.getFechaVencimiento());
        if (hoy.isAfter(fechaVenc)) {
            prestamoEncontrado.getUsuario().setDeuda(true);
        }
    }

    /** Método que muestra el estado de un libro */
    public void actualizarEstado(Libro libro) {
        System.out.println("Este libro se encuentra " + libro.getEstado());
    }

    public void reporteLibrosPrestados() {
        System.out.println("=== Libros Prestados Actualmente ===");
        for (PrestamoLibro p : listaPrestamos) {
            if (!p.isDevuelto()) {
                System.out.println(p.getLibro());
            }
        }
    }

    /** Reporte: libros disponibles */
    public void reporteLibrosDisponibles() {
        System.out.println("=== Libros Disponibles ===");
        for (Libro l : listaLibros) {
            if (l.getEstado() == Estado.DISPONIBLE) {
                System.out.println(l);
            }
        }
    }

    /** Reporte: usuarios con deudas */
    public void reporteUsuariosConDeuda() {
        System.out.println("=== Usuarios con Deudas ===");
        for (Usuario u : listaUsuarios) {
            if (u.tieneDeuda()) {
                System.out.println(u);
            }
        }
    }
}

