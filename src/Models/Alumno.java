package Models;

import java.util.Objects;

public class Alumno {
    private String nombre;
    private String apellido;
    private String dni;
    private Integer edad;
    private String curso;
    public Alumno() {
    }

    public Alumno(String nombre, String apellido, String dni, Integer edad, String curso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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
                ", Apellido:'" + apellido + '\'' +
                ", DNI:'" + dni + '\'' +
                ", Edad:" + edad +
                ", Curso:'" + curso + '\'' +
                '}';
    }
}
