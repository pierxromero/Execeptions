package Exceptions;

public class NoHayCursosException extends RuntimeException {
    public NoHayCursosException() {
        super("No hay Cursos en el Sistema");
    }
}
