package Exceptions;

public class AlumnoNoEncontradoException extends Exception{
    public AlumnoNoEncontradoException(String DNI){
        super("No se encontro el alumno con el DNI:"+DNI);
    }
}
