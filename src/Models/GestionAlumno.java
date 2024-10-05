package Models;

import Exceptions.*;

import java.util.*;

public class GestionAlumno {
    private HashMap<String, HashSet<Alumno>> inventario;

    public GestionAlumno() {
        this.inventario = new HashMap<String,HashSet<Alumno>>();
    }
    public void agregarCurso(String curso)throws CursoYaExistenteException{
        if(inventario.containsKey(curso)){
            throw new CursoYaExistenteException(curso);
        }
        inventario.put(curso,new HashSet<Alumno>());
    }
    ///Ejercicio 1
    public void addAlumno(String curso, Alumno alumno) throws CursoNoEncontradoException, AlumnoYaInscriptoException {
        if (!inventario.containsKey(curso)){
            throw new CursoNoEncontradoException(curso);
        }
        HashSet<Alumno> alumnos=inventario.get(curso);
        if(alumnos.contains(alumno)==true){
            throw new AlumnoYaInscriptoException(alumno,curso);
        }
        alumnos.add(alumno);
    }
    ///Ejercicio 2
    public void eliminarAlumno(String curso,String DNI) throws CursoNoEncontradoException, AlumnoNoEncontradoException {
        if (!inventario.containsKey(curso)){
              throw new CursoNoEncontradoException(curso);
          }
        HashSet<Alumno> alumnos =inventario.get(curso);
        if(alumnos.removeIf(alumno -> alumno.getDni().equals(DNI))){
            System.out.println("El Alumno con el DNI:"+DNI+" fue eliminado.");
        }else{
            throw new AlumnoNoEncontradoException(DNI);
        }
    }
    ///Ejercicio 3
    public HashSet<Alumno> listarAlumnos(String curso) throws CursoNoEncontradoException, CursoVacioException {
        if (!inventario.containsKey(curso)){
            throw new CursoNoEncontradoException(curso);
        }
        HashSet<Alumno> alumnos=inventario.get(curso);
        if(alumnos.isEmpty()){
            throw new CursoVacioException(curso);
        }
        return alumnos;
    }
    ///Ejercicio 4
    public int contarAlumnos(String curso) throws CursoNoEncontradoException, CursoVacioException {
        if(!inventario.containsKey(curso)){
            throw new CursoNoEncontradoException(curso);
        }
        HashSet<Alumno> alumnos = inventario.get(curso);
        if(alumnos.isEmpty()){
            throw new CursoVacioException(curso);
        }
        return alumnos.size();
    }
    ///Ejercicio 5
    public void transferirEstudiante(String cursoOrigen,String cursoDestino,String DNI) throws CursoNoEncontradoException, AlumnoNoEncontradoException, AlumnoYaInscriptoException {
        if(!inventario.containsKey(cursoOrigen)){
            throw new CursoNoEncontradoException(cursoOrigen);
        }
        if(!inventario.containsKey(cursoDestino)){
            throw new CursoNoEncontradoException(cursoDestino);
        }
        HashSet<Alumno> alumnos = inventario.get(cursoOrigen);
        HashSet<Alumno> alumnos2 = inventario.get(cursoDestino);
        Alumno alumno = alumnos.stream()
                ///Que hace stream? sirve para procesar datos de una lista, como si fuera una tuberia
                .filter(a -> a.getDni().equals(DNI))
                ///Filter es un metodo que solo se aplica a un stream, filtra elementos y te quedas con el que
                ///cumpla las condiciones, generando un nuevo stream
                ///lo que esta adentro es una expresion lambda (una forma de escribir una funcion corta)
                ///filter devuelve true o false
                ///la expresion lambda hace que al recorrer el stream, solo mente se mantenga el alumno que
                ///coincida su dni, es la condicion
                .findFirst()
                ///findFirst hace que se obtenga solo el primer elemento que coincida con la expresion lambda
                ///devuelve un optional, que evita que se devuelva null o un comportamiento indefinido
                .orElseThrow(() -> new AlumnoNoEncontradoException(DNI));
        if(alumnos2.contains(alumno)){
            throw new AlumnoYaInscriptoException(alumno,cursoDestino);
        }
        alumnos.remove(alumno);
        alumnos2.add(alumno);
    }
    ///Ejercicio 6
    public ArrayList<String> listarCurso()throws NoHayCursosException{
        if(inventario.isEmpty()){
             throw new NoHayCursosException();
        }
        return new ArrayList<>(inventario.keySet());
    }
    ///Ejercico 7
    public boolean verificarInscripcion(String curso, String DNI) throws CursoNoEncontradoException {
        if(!inventario.containsKey(curso)) {
            throw new CursoNoEncontradoException(curso);
        }
        HashSet<Alumno> alumnos=inventario.get(curso);
        return alumnos.stream().anyMatch(alumno -> alumno.getDni().equals(DNI));
        ///Se usa anyMatch, que devuelve true o false en el caso que se encuentre almenos uno de la
        ///coincidencia
    }
    public void cambiarNombreCurso(String cursoActual, String nuevoNombre) throws CursoNoEncontradoException {
        if(!inventario.containsKey(cursoActual)){
            throw new CursoNoEncontradoException(cursoActual);
        }
        if(inventario.containsKey(nuevoNombre)){
            throw new CursoYaExistenteException(nuevoNombre);
        }
        HashSet<Alumno> alumnos =inventario.remove(cursoActual);
        inventario.put(nuevoNombre,alumnos);
        System.out.println("Se Cambio el nombre del Curso");
    }
}
