package Models;

import java.util.Objects;

public class Alumno {
    private String nombre;
    private String dni;
    public Alumno() {
    }

    public Alumno(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getDni() {
        return dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Alumno:{" +
                "Nombre:'" + nombre + '\'' +
                ", DNI:'" + dni + '\'' +
                '}';
    }
}
