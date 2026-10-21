package cursos;

/**
 * Programa de demostración del Sistema de Gestión de Cursos. Recorre un semestre:
 * crea el departamento y los cursos, inscribe estudiantes, registra notas y prueba
 * las reglas de negocio que deben fallar.
 *
 * @author Daniel Villa
 */
public class Main {

    /** Esta clase solo se usa para ejecutar el programa; no se crean objetos de ella. */
    private Main() {
    }

    /**
     * Punto de entrada del programa.
     *
     * @param args no se usa
     */
    public static void main(String[] args) {

        System.out.println("=== 1. Departamento, profesora y cursos ===");
        Departamento sistemas = new Departamento(1, "Ingeniería de Sistemas");
        Profesor laura = new Profesor(100, "Laura Gómez", "43111222", "laura@instituto.edu.co", "3001112233",
                "P-001", "Programación", "Magíster en Ingeniería");
        sistemas.agregarProfesor(laura);

        Curso poo = sistemas.crearCurso("SIS101", "Programación Orientada a Objetos", 4, 2, "2026-2");
        Curso bd = sistemas.crearCurso("SIS102", "Bases de Datos", 3, 30, "2026-2");
        poo.asignarProfesor(laura);
        bd.asignarProfesor(laura);

        for (Curso c : sistemas.listarCursos()) {
            System.out.println(c.getCodigo() + " - " + c.getNombre() + " | cupo: " + c.getCupoMaximo()
                    + " | profesora: " + c.getProfesor().obtenerNombreCompleto());
        }

        System.out.println();
        System.out.println("=== 2. Inscripciones ===");
        Estudiante camila = new Estudiante(1, "Camila Restrepo", "1000111", "camila@correo.com", "3101110000",
                "E-01", "Ingeniería de Sistemas", 3);
        Estudiante andres = new Estudiante(2, "Andrés Mejía", "1000222", "andres@correo.com", "3102220000",
                "E-02", "Ingeniería de Sistemas", 3);
        Estudiante valentina = new Estudiante(3, "Valentina Ríos", "1000333", "valentina@correo.com", "3103330000",
                "E-03", "Ingeniería de Sistemas", 2);

        camila.inscribirse(poo);
        andres.inscribirse(poo);
        System.out.println("Inscritos en POO: " + poo.contarInscritos() + " de " + poo.getCupoMaximo());

        System.out.println("Valentina intenta entrar con el curso lleno:");
        valentina.inscribirse(poo);

        System.out.println("Camila intenta inscribirse otra vez:");
        camila.inscribirse(poo);

        andres.retirarCurso(poo);
        System.out.println("Andrés se retiró. Inscritos en POO: " + poo.contarInscritos());
        valentina.inscribirse(poo);
        System.out.println("Valentina entró. Inscritos en POO: " + poo.contarInscritos());
        camila.inscribirse(bd);

        System.out.println("Lista de POO:");
        for (Estudiante e : laura.listarEstudiantes(poo)) {
            System.out.println("  - " + e.getNombre());
        }

        System.out.println();
        System.out.println("=== 3. Calificaciones ===");
        Inscripcion camilaPoo = camila.buscarInscripcion(poo);
        Inscripcion valentinaPoo = valentina.buscarInscripcion(poo);
        Inscripcion camilaBd = camila.buscarInscripcion(bd);

        laura.registrarCalificacion(camilaPoo, "Parcial 1", 4.0, 30);
        laura.registrarCalificacion(camilaPoo, "Taller", 3.5, 20);
        laura.registrarCalificacion(camilaPoo, "Examen final", 4.5, 50);

        laura.registrarCalificacion(valentinaPoo, "Parcial 1", 2.0, 30);
        laura.registrarCalificacion(valentinaPoo, "Taller", 3.0, 20);
        laura.registrarCalificacion(valentinaPoo, "Examen final", 2.5, 50);

        laura.registrarCalificacion(camilaBd, "Parcial 1", 5.0, 40);

        System.out.println("Nota de 6.0 (no válida):");
        laura.registrarCalificacion(camilaBd, "Taller", 6.0, 20);

        System.out.println("Peso que pasa del 100%:");
        laura.registrarCalificacion(camilaBd, "Proyecto", 4.0, 70);

        Profesor carlos = new Profesor(101, "Carlos Pérez", "70222333", "carlos@instituto.edu.co", "3004445566",
                "P-002", "Redes", "Ingeniero");
        System.out.println("Un profesor que no dicta el curso intenta poner una nota:");
        carlos.registrarCalificacion(camilaBd, "Taller", 4.0, 20);

        System.out.println();
        System.out.println("=== 4. Resultados ===");
        mostrar(camilaPoo);
        mostrar(valentinaPoo);
        mostrar(camilaBd);

        System.out.printf("Promedio de Camila: %.2f%n", camila.calcularPromedio());

        System.out.println("Notas de Camila:");
        for (Calificacion c : camila.consultarCalificaciones()) {
            System.out.println("  " + c.getTipoEvaluacion() + ": " + c.getValor() + " (" + c.getPeso() + "%)");
        }

        System.out.println();
        System.out.println("Corrección: el parcial de Valentina era 3.0, no 2.0.");
        valentinaPoo.getCalificaciones().get(0).modificar(3.0);
        mostrar(valentinaPoo);
    }

    /**
     * Muestra el resultado de una inscripción en una línea.
     *
     * @param i la inscripción a mostrar
     */
    public static void mostrar(Inscripcion i) {
        System.out.printf("%s | %s | nota final: %.2f | %s%n",
                i.getEstudiante().getNombre(), i.getCurso().getNombre(), i.getNotaFinal(), i.resultado());
    }
}
