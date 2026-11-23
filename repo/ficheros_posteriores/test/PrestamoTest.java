import static org.junit.jupiter.api.Assertions.*;

import modelo.Libro;
import modelo.Socio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicio.Prestamo;

public class PrestamoTest {
    private Prestamo prestamo;
    private Socio socio;
    private Libro libro;

    @BeforeEach
    void setUp() {
        prestamo = new Prestamo();
        socio = new Socio(1, "Ana", "ana@correo.es");
        libro = new Libro("978-84-0000-001", "El Quijote", "Cervantes");
    }

    @Test
    void prestarLibroDisponible() {
        assertTrue(prestamo.prestar(socio, libro));
        assertFalse(libro.isDisponible());
        assertEquals(1, socio.getLibrosPrestados().size());
    }

    @Test
    void noPrestarLibroYaPrestado() {
        prestamo.prestar(socio, libro);
        Socio otro = new Socio(2, "Luis", "luis@correo.es");
        assertFalse(prestamo.prestar(otro, libro));
    }

    @Test
    void noSuperarMaximoDeLibros() {
        for (int i = 0; i < Prestamo.MAX_LIBROS; i++) {
            assertTrue(prestamo.prestar(socio, new Libro("isbn" + i, "T" + i, "A" + i)));
        }
        assertFalse(prestamo.prestar(socio, libro));
    }

    @Test
    void devolverLibro() {
        prestamo.prestar(socio, libro);
        assertTrue(prestamo.devolver(socio, libro));
        assertTrue(libro.isDisponible());
        assertTrue(socio.getLibrosPrestados().isEmpty());
    }

    @Test
    void prestarConNulosLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> prestamo.prestar(null, libro));
    }
}
