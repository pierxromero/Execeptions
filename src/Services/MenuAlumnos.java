package Services;

import Models.Alumno;
import Models.GestionAlumno;
import Exceptions.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MenuAlumnos {
    private GestionAlumno gestion;
    private Scanner scanner;

    public MenuAlumnos() {
        this.gestion = new GestionAlumno();
        this.scanner = new Scanner(System.in);
    }
    public void menuInteractivo(){
        cargaManual();
        Integer opcion = 0;
        Scanner scanner = new Scanner(System.in);
        while(opcion!=10){
            System.out.println("\n--- Menú de Gestión de Alumnos ---");
            System.out.println("1. Agregar Curso");
            System.out.println("2. Inscribir Alumno");
            System.out.println("3. Eliminar Alumno");
            System.out.println("4. Listar Alumnos en un Curso");
            System.out.println("5. Contar Alumnos en un Curso");
            System.out.println("6. Transferir Estudiante");
            System.out.println("7. Cambiar Nombre de Curso");
            System.out.println("8. Listar Cursos");
            System.out.println("9. Verificar inscripcion");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion){
                case 1:
                    primerCase();
                    break;
                case 2:
                    segundoCase();
                    break;
                case 3:
                    tercerCase();
                    break;
                case 4:
                    cuartoCase();
                    break;
                case 5:
                    quintoCase();
                    break;
                case 6:
                    sextoCase();
                    break;
                case 7:
                    septimoCase();
                    break;
                case 8:
                    octavoCase();
                    break;
                case 9:
                    novenoCase();
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
    public void primerCase() {
        System.out.print("Ingrese el nombre del curso: ");
        String curso = scanner.nextLine();
        try {
            gestion.agregarCurso(curso);
            System.out.println("Curso agregado exitosamente.");
        } catch (CursoYaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void segundoCase() {
        System.out.print("Ingrese el nombre del curso: ");
        String cursoInscribir = scanner.nextLine();
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el DNI del alumno: ");
        String dni = scanner.nextLine();
        Alumno alumno = new Alumno(nombre, dni);
        try {
            gestion.addAlumno(cursoInscribir, alumno);
            System.out.println("Alumno inscrito exitosamente.");
        } catch (CursoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (AlumnoYaInscriptoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void tercerCase() {
        System.out.print("Ingrese el nombre del curso: ");
        String cursoEliminar = scanner.nextLine();
        System.out.print("Ingrese el DNI del alumno a eliminar: ");
        String dniEliminar = scanner.nextLine();

        try {
            gestion.eliminarAlumno(cursoEliminar, dniEliminar);
            System.out.println("Alumno eliminado exitosamente.");
        } catch (CursoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (AlumnoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cuartoCase() {
        System.out.print("Ingrese el nombre del curso: ");
        String cursoListar = scanner.nextLine();

        try {
            HashSet<Alumno> alumnos = gestion.listarAlumnos(cursoListar);
            System.out.println("Alumnos en " + cursoListar + ": " + alumnos);
        } catch (CursoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }catch (CursoVacioException e){
            System.out.println(e.getMessage());
        }
    }

    public void quintoCase() {
        System.out.print("Ingrese el nombre del curso: ");
        String cursoContar = scanner.nextLine();

        try {
            int cantidad = gestion.contarAlumnos(cursoContar);
            System.out.println("Cantidad de alumnos en " + cursoContar + ": " + cantidad);
        } catch (CursoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }catch (CursoVacioException e){
            System.out.println(e.getMessage());
        }
    }

    public void sextoCase() {
        System.out.print("Ingrese el curso de origen: ");
        String cursoOrigen = scanner.nextLine();
        System.out.print("Ingrese el curso de destino: ");
        String cursoDestino = scanner.nextLine();
        System.out.print("Ingrese el DNI del alumno: ");
        String dniTransferir = scanner.nextLine();

        try {
            gestion.transferirEstudiante(cursoOrigen, cursoDestino, dniTransferir);
            System.out.println("Estudiante transferido exitosamente.");
        } catch (CursoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (AlumnoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (AlumnoYaInscriptoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void septimoCase() {
        System.out.print("Ingrese el nombre del curso actual: ");
        String cursoActual = scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre del curso: ");
        String nuevoNombre = scanner.nextLine();

        try {
            gestion.cambiarNombreCurso(cursoActual, nuevoNombre);
            System.out.println("Nombre del curso cambiado exitosamente.");
        } catch (CursoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (CursoYaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void octavoCase() {
        try {
            ArrayList<String> cursos = gestion.listarCurso();
            System.out.println("Cursos disponibles: " + cursos);
        } catch (NoHayCursosException e) {
            System.out.println(e.getMessage());
        }
    }
    public void novenoCase(){
        System.out.print("Ingrese el nombre del curso actual: ");
        String curso = scanner.nextLine();
        System.out.print("Ingrese el DNI a verificar: ");
        String dni = scanner.nextLine();
        try{
            if(!gestion.verificarInscripcion(curso,dni)){
                System.out.println("El Alumno no se encuentra en el curso");
            }else{
                System.out.println("El Alumno se encuentra en el curso");
            }
        }catch(CursoNoEncontradoException e){
            System.out.println(e.getMessage());
        }
    }
    public void cargaManual(){
        ///Una exception general por que no fallara la carga
        try {
            gestion.agregarCurso("Matematicas");
            gestion.agregarCurso("Fisica");
            gestion.agregarCurso("Quimica");
            gestion.agregarCurso("Biologia");
            gestion.agregarCurso("Historia");
            Alumno alumno1 = new Alumno("Juan", "12345678");
            Alumno alumno2 = new Alumno("Maria", "23456789");
            Alumno alumno3 = new Alumno("Carlos", "34567890");
            Alumno alumno4 = new Alumno("Ana", "45678901");
            Alumno alumno5 = new Alumno("Luis", "56789012");
            Alumno alumno6 = new Alumno("Sofia", "67890123");
            Alumno alumno7 = new Alumno("Pedro", "78901234");
            Alumno alumno8 = new Alumno("Laura", "89012345");
            Alumno alumno9 = new Alumno("Javier", "90123456");
            Alumno alumno10 = new Alumno("Clara", "01234567");
            gestion.addAlumno("Matematicas", alumno1);
            gestion.addAlumno("Matematicas", alumno2);
            gestion.addAlumno("Matematicas", alumno3);
            gestion.addAlumno("Matematicas", alumno4);

            gestion.addAlumno("Fisica", alumno5);
            gestion.addAlumno("Fisica", alumno6);
            gestion.addAlumno("Fisica", alumno2); // María también en Física

            gestion.addAlumno("Quimica", alumno3);
            gestion.addAlumno("Quimica", alumno7);
            gestion.addAlumno("Quimica", alumno8);

            gestion.addAlumno("Biologia", alumno6);
            gestion.addAlumno("Biologia", alumno9);
            gestion.addAlumno("Biologia", alumno10);

            gestion.addAlumno("Historia", alumno1);
            gestion.addAlumno("Historia", alumno4);
            gestion.addAlumno("Historia", alumno5);
        }catch (Exception e){
            System.out.println("no pasara nada xde");
        }
    }
}
