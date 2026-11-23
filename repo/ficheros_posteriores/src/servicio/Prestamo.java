package servicio;

import modelo.Libro;
import modelo.Socio;

public class Prestamo {
    public static final int MAX_LIBROS = 3;

    public boolean prestar(Socio socio, Libro libro) {
        if (socio == null || libro == null) {
            throw new IllegalArgumentException("Socio y libro no pueden ser nulos");
        }
        if (!libro.isDisponible() || socio.getLibrosPrestados().size() >= MAX_LIBROS) {
            return false;
        }
        libro.setDisponible(false);
        socio.getLibrosPrestados().add(libro);
        return true;
    }

    public boolean devolver(Socio socio, Libro libro) {
        if (socio == null || libro == null) {
            throw new IllegalArgumentException("Socio y libro no pueden ser nulos");
        }
        if (!socio.getLibrosPrestados().remove(libro)) {
            return false;
        }
        libro.setDisponible(true);
        return true;
    }
}
