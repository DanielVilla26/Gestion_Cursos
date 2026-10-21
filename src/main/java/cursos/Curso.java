package cursos;

import java.util.ArrayList;

/**
 * Asignatura ofrecida en un periodo, con cupo limitado y un profesor a cargo.
 * Se crea con {@link Departamento#crearCurso(String, String, int, int, String)}.
 *
 * @author Daniel Villa
 */
public class Curso {

    private String codigo;
    private String nombre;
    private int creditos;
    private int cupoMaximo;
    private String periodo;
    private Profesor profesor;
    private ArrayList<Inscripcion> inscripciones;

    /**
     * Crea un curso sin profesor y sin inscritos.
     *
     * @param codigo     código del curso
     * @param nombre     nombre del curso
     * @param creditos   número de créditos
     * @param cupoMaximo máximo de estudiantes inscritos a la vez
     * @param periodo    periodo académico, por ejemplo "2026-2"
     */
    public Curso(String codigo, String nombre, int creditos, int cupoMaximo, String periodo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
        this.periodo = periodo;
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    /**
     * Indica si todavía se puede inscribir alguien más.
     *
     * @return {@code true} si los inscritos son menos que el cupo máximo
     */
    public boolean tieneCupo() {
        return contarInscritos() < cupoMaximo;
    }

    /**
     * Guarda una inscripción en el curso.
     *
     * @param i la inscripción a guardar
     */
    public void agregarInscripcion(Inscripcion i) {
        inscripciones.add(i);
    }

    /**
     * Lista los estudiantes que siguen inscritos. Los que se retiraron no aparecen.
     *
     * @return la lista de estudiantes inscritos
     */
    public ArrayList<Estudiante> listarInscritos() {
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva()) {
                lista.add(i.getEstudiante());
            }
        }
        return lista;
    }

    /**
     * Asigna el profesor que dicta el curso y lo agrega a la lista de cursos del profesor.
     *
     * @param p el profesor del curso
     */
    public void asignarProfesor(Profesor p) {
        this.profesor = p;
        p.agregarCurso(this);
    }

    /**
     * Cuenta los estudiantes con inscripción vigente.
     *
     * @return el número de inscritos
     */
    public int contarInscritos() {
        int total = 0;
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva()) {
                total = total + 1;
            }
        }
        return total;
    }

    /**

     * Devuelve el código del curso.

     *

     * @return el código del curso

     */
    public String getCodigo() {
        return codigo;
    }

    /**

     * Devuelve el nombre del curso.

     *

     * @return el nombre del curso

     */
    public String getNombre() {
        return nombre;
    }

    /**

     * Devuelve el número de créditos.

     *

     * @return el número de créditos

     */
    public int getCreditos() {
        return creditos;
    }

    /**

     * Devuelve el cupo máximo.

     *

     * @return el cupo máximo

     */
    public int getCupoMaximo() {
        return cupoMaximo;
    }

    /**

     * Devuelve el periodo académico.

     *

     * @return el periodo académico

     */
    public String getPeriodo() {
        return periodo;
    }

    /**

     * Devuelve el profesor del curso, o {@code null} si aún no tiene.

     *

     * @return el profesor del curso, o {@code null} si aún no tiene

     */
    public Profesor getProfesor() {
        return profesor;
    }
}
