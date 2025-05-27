package org.example.proyectofx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {
    private Biblioteca biblioteca;

    @BeforeEach
    void setUp() {
        biblioteca = new Biblioteca("BibliotecaUQ");
    }

    private LibroFisico crearLibroDisponible() {
        return new LibroFisico(
                1,
                "Java Básico",
                "James Gosling",
                "Tecnología",
                2020,
                Estado.DISPONIBLE,
                300,
                "Pearson",
                "Estante A1"
        );
    }

    @Test
    void testRegistrarBibliotecarioCorrecto() throws Exception {
        Bibliotecario b = new Bibliotecario(1, "Laura");
        biblioteca.registrarEmpleado(b);
        assertEquals(b, biblioteca.buscarEmpleado(1));
    }

    @Test
    void testRegistrarBibliotecarioRepetido() throws Exception {
        Bibliotecario b1 = new Bibliotecario(1, "Laura");
        Bibliotecario b2 = new Bibliotecario(1, "Carlos");
        biblioteca.registrarEmpleado(b1);
        Exception ex = assertThrows(Exception.class, () -> biblioteca.registrarEmpleado(b2));
        assertTrue(ex.getMessage().contains("Ya existe un empleado"));
    }

    @Test
    void testModificarBibliotecarioCorrecto() throws Exception {
        Bibliotecario b1 = new Bibliotecario(1, "Laura");
        Bibliotecario b2 = new Bibliotecario(2, "Carlos");
        biblioteca.registrarEmpleado(b1);
        biblioteca.modificarEmpleado(1, b2);
        assertEquals(b2, biblioteca.buscarEmpleado(2));
    }

    @Test
    void testEliminarBibliotecarioCorrecto() throws Exception {
        Bibliotecario b = new Bibliotecario(1, "Laura");
        biblioteca.registrarEmpleado(b);
        biblioteca.eliminarEmpleado(1);
        assertNull(biblioteca.buscarEmpleado(1));
    }

    @Test
    void testRegistrarLibroFisicoCorrecto() throws Exception {
        LibroFisico libro = crearLibroDisponible();
        biblioteca.registrarLibro(libro);
        assertTrue(biblioteca.buscarLibro(libro));
    }

    @Test
    void testRegistrarLibroFisicoRepetido() throws Exception {
        LibroFisico libro1 = crearLibroDisponible();
        LibroFisico libro2 = crearLibroDisponible(); // mismo ID
        biblioteca.registrarLibro(libro1);
        Exception ex = assertThrows(Exception.class, () -> biblioteca.registrarLibro(libro2));
        assertTrue(ex.getMessage().contains("Ya existe un libro"));
    }

    @Test
    public void testEliminarLibro_Exitoso() throws Exception {
        Biblioteca biblioteca = new Biblioteca("UQ");
        LibroFisico libro = new LibroFisico(1, "Java", "Autor", "CS", 2020,
                Estado.DISPONIBLE, 300, "Pearson", "A1");
        biblioteca.registrarLibro(libro);

        biblioteca.eliminarLibro(1);

        assertFalse(biblioteca.getListaLibros().contains(libro));
    }

    @Test
    public void testModificarLibro_Exitoso() throws Exception {
        Biblioteca biblioteca = new Biblioteca("UQ");
        LibroFisico libroOriginal = new LibroFisico(1, "Java", "Autor", "CS", 2020,
                Estado.DISPONIBLE, 300, "Pearson", "A1");
        biblioteca.registrarLibro(libroOriginal);

        LibroFisico libroModificado = new LibroFisico(2, "Java Avanzado", "Autor 2", "CS", 2021,
                Estado.DISPONIBLE, 350, "Pearson", "B2");

        biblioteca.modificarLibro(1, libroModificado);

        // Verifica que el libro con idOriginal ya no esté, y el modificado sí.
        assertFalse(biblioteca.getListaLibros().contains(libroOriginal));
        assertTrue(biblioteca.getListaLibros().contains(libroModificado));
    }




    @Test
    void testBuscarLibroFisicoInexistente() {
        LibroFisico libro = new LibroFisico(
                99, "No Existe", "Anon", "Ficción", 2000,
                Estado.DISPONIBLE, 123, "Editorial X", "Estante Z"
        );
        assertFalse(biblioteca.buscarLibro(libro));
    }

    @Test
    void testRegistrarDocenteCorrecto() throws Exception {
        Usuario docente = new Docente("Ana", 141, "ana@correo.com", "1234",true);
        biblioteca.registrarUsuario(docente);
        assertEquals(docente, biblioteca.buscarUsuarioporCorreo("ana@correo.com"));
    }

    @Test
    void testRegistrarPrestamoCorrecto() throws Exception {
        LibroFisico libro = crearLibroDisponible();
        Usuario docente = new Docente("sam", 213, "ana@correo.com", "1234",false);

        biblioteca.registrarLibro(libro);
        biblioteca.registrarUsuario(docente);

        boolean prestado = biblioteca.registrarPrestamo(docente, libro);
        assertTrue(prestado);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @Test
    void testRegistrarPrestamoLibroNoDisponible() throws Exception {
        LibroFisico libro = crearLibroDisponible();
        libro.setEstado(Estado.PRESTADO);
        Usuario docente = new Docente("manolo", 1234, "ana@correo.com", "1234", true);

        biblioteca.registrarLibro(libro);
        biblioteca.registrarUsuario(docente);

        Exception ex = assertThrows(Exception.class, () -> biblioteca.registrarPrestamo(docente, libro));
        assertTrue(ex.getMessage().contains("no está disponible"));
    }

    @Test
    void testRegistrarDevolucionCorrecta() throws Exception {
        LibroFisico libro = crearLibroDisponible();
        Usuario docente = new Docente("andrew", 1234, "ana@correo.com", "1234", false);

        biblioteca.registrarLibro(libro);
        biblioteca.registrarUsuario(docente);
        biblioteca.registrarPrestamo(docente, libro);

        biblioteca.registrarDevolucion(docente.getId(), libro.getID());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testRegistrarDevolucionInexistente() {
        Exception ex = assertThrows(Exception.class, () -> biblioteca.registrarDevolucion(99, 99));
        assertTrue(ex.getMessage().contains("No existe préstamo activo"));
    }

    @Test
    void testVerificarCredencialesCorrectas() throws Exception {
        Usuario docente = new Docente("sam", 2143, "ana@correo.com", "1234", true);
        biblioteca.registrarUsuario(docente);
        Docente autenticado = (Docente) biblioteca.verificarCredenciales("ana@correo.com", "1234");
        assertEquals(docente, autenticado);
    }

    @Test
    void testVerificarCredencialesIncorrectas() throws Exception {
        Usuario docente = new Docente("manolo", 21324, "ana@correo.com", "1234", false);
        biblioteca.registrarUsuario(docente);
        Exception ex = assertThrows(Exception.class, () ->
                biblioteca.verificarCredenciales("ana@correo.com", "incorrecta"));
        assertTrue(ex.getMessage().contains("incorrecta"));
    }

}

