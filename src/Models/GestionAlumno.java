package Models;

import Exceptions.AlumnoNoEncontradoException;
import Exceptions.AlumnoYaInscriptoException;
import Exceptions.CursoNoEncontradoException;
import Exceptions.CursoVacioException;

import java.lang.runtime.TemplateRuntime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GestionAlumno {
    private HashMap<String, HashSet<Alumno>> inventario;

    public GestionAlumno() {
        this.inventario = new HashMap<String,HashSet<Alumno>>();
    }
    public void agregarCurso(String curso){
        inventario.put(curso,new HashSet<Alumno>());
    }
    public void addAlumno(String curso, Alumno alumno) throws CursoNoEncontradoException, AlumnoYaInscriptoException {
        HashSet<Alumno> alumnos = new HashSet<Alumno>();
        if (inventario.containsKey(curso)==false){
            throw new CursoNoEncontradoException(curso);
        }
        alumnos = inventario.get(curso);
        if(alumnos.contains(alumno)==true){
            throw new AlumnoYaInscriptoException(alumno,curso);
        }
        alumnos.add(alumno);
    }
    public void eliminarAlumno(String curso,String DNI) throws CursoNoEncontradoException, AlumnoNoEncontradoException {
        HashSet<Alumno> alumnos = new HashSet<Alumno>();
        Iterator<Alumno> iterator = alumnos.iterator();
        if (inventario.containsKey(curso)==false){
              throw new CursoNoEncontradoException(curso);
          }
        alumnos=inventario.get(curso);
        while (iterator.hasNext()) {
            Alumno alumno = iterator.next();
            if (alumno.getDni().equals(DNI)) {
                iterator.remove();
                System.out.println("Se Elimino el Alumno");
                return;
            }
        }
        throw new AlumnoNoEncontradoException(DNI);
    }
    public void listarAlumnos(String curso) throws CursoNoEncontradoException, CursoVacioException {
        HashSet<Alumno> alumnos=new HashSet<Alumno>();
        if (inventario.containsKey(curso)==false){
            throw new CursoNoEncontradoException(curso);
        }
        alumnos=inventario.get(curso);
        if(alumnos.isEmpty()==true){
            throw new CursoVacioException(curso);
        }
        System.out.println("Listado del Curso:"+curso);
        for (Alumno alumno:alumnos) {
            System.out.println(alumno);
        }
    }
}
