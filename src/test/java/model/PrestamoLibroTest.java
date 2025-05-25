package model;

import org.example.proyectofx.model.Estado;
import org.example.proyectofx.model.Libro;
import org.example.proyectofx.model.PrestamoLibro;
import org.junit.jupiter.api.Test;

class PrestamoLibroTest {
    @Test
    public void devolucionLibroTest(){
        Libro libro = new Libro(1, "Cien años de soledad", "Gabriel garcia", "Novela", 1967, Estado.PRESTAMO);
       // PrestamoLibro libro1 = new PrestamoLibro();
        Estado expected = Estado.DISPONIBLE;
        PrestamoLibro.devolverLibro();
       // assertEquals(expected, PrestamoLibro.devolverLibro());
    }

}
