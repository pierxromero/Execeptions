package Exceptions;

public class CursoNoEncontradoException extends Exception{
    public CursoNoEncontradoException(String curso) {
        super("El Curso:"+curso+" - No Fue Encontrado");
    }
}
