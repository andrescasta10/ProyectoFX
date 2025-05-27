package org.example.proyectofx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocenteTest {
    private Biblioteca biblioteca;
    private Docente docente;
    private LibroFisico libroDisponible;
    private LibroFisico libroNoDisponible;

    @BeforeEach
    public void setUp() throws Exception {
        biblioteca = new Biblioteca("Biblioteca UQ");

        docente = new Docente("Ana", 1, "ana@correo.com", "1234", false);
        biblioteca.registrarUsuario(docente);

        libroDisponible = new LibroFisico(1, "Java", "Autor", "Programación", 2020, Estado.DISPONIBLE, 200, "Pearson", "A1");
        libroNoDisponible = new LibroFisico(2, "C++", "Autor", "Programación", 2020, Estado.PRESTADO, 300, "Pearson", "B2");

        biblioteca.registrarLibro(libroDisponible);
        biblioteca.registrarLibro(libroNoDisponible);
    }

    // ----- Test getMaximoLibrosPermitidos -----
    @Test
    public void testGetMaximoLibrosPermitidos() {
        assertEquals(5, docente.getMaximoLibrosPermitidos());
    }

    // ----- Test getDiasMaximosPrestamo -----
    @Test
    public void testGetDiasMaximosPrestamo() {
        assertEquals(30, docente.getDiasMaximosPrestamo());
    }

    // ----- Test buscarLibro (con libro existente) -----


    // ----- Test buscarLibro (con libro inexistente) -----
    @Test
    public void testBuscarLibroSimple() {
        Biblioteca biblioteca = new Biblioteca("UQ");
        Docente docente = new Docente("Ana", 1, "ana@uq.edu", "1234", false);
        LibroFisico libro = new LibroFisico(1, "Java", "Autor", "CS", 2020, Estado.DISPONIBLE, 300, "Pearson", "A1");

        try {
            biblioteca.registrarLibro(libro);
        } catch (Exception e) {
            assert false : "Error al registrar libro";
        }

        // Simplemente llamar al metodo, sin capturar salida ni validar mensaje
        docente.buscarLibro(biblioteca, libro);
    }

    // ----- Test solicitarPrestamo (éxito) -----
    @Test
    public void testSolicitarPrestamoCorrectamente() throws Exception {
        boolean resultado = docente.solicitarPrestamo(biblioteca, libroDisponible);
        assertTrue(resultado);
        assertEquals(Estado.PRESTADO, libroDisponible.getEstado());
    }

    // ----- Test solicitarPrestamo (libro prestado) -----
    @Test
    public void testSolicitarPrestamoLibroNoDisponible() {
        assertThrows(Exception.class, () -> {
            docente.solicitarPrestamo(biblioteca, libroNoDisponible);
        });
    }

    // ----- Test solicitarPrestamo (usuario con deuda) -----
    @Test
    public void testSolicitarPrestamoUsuarioConDeuda() {
        Docente deudor = new Docente("Luis", 2, "luis@correo.com", "pass", true);
        try {
            biblioteca.registrarUsuario(deudor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(Exception.class, () -> {
            deudor.solicitarPrestamo(biblioteca, libroDisponible);
        });
    }
}