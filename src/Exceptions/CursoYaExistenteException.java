package Exceptions;

public class CursoYaExistenteException extends RuntimeException {
    public CursoYaExistenteException(String curso) {
        super("El Curso:"+curso+" Ya Existe en el sistema");
    }
}
