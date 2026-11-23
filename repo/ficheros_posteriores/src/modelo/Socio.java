package modelo;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private final int id;
    private final String nombre;
    private final String email;
    private final List<Libro> librosPrestados = new ArrayList<>();

    public Socio(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public List<Libro> getLibrosPrestados() { return librosPrestados; }

    @Override
    public String toString() {
        return nombre + " <" + email + ">";
    }
}
