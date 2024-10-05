package Exceptions;

import Models.Alumno;

public class AlumnoYaInscriptoException extends Exception{
    public AlumnoYaInscriptoException(Alumno alumno, String curso) {
        super("El Alumno:"+alumno.getNombre()+" - Ya se encuentra en el" +
                " curso:"+curso);
    }
}
