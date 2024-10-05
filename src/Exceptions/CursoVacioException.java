package Exceptions;

public class CursoVacioException extends Exception{
    public CursoVacioException(String curso) {
        super("El Curso:"+curso+" Se Encuentra Vacio");
    }
}
