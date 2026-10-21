package cursos;

import java.util.ArrayList;

/**
 * Unidad académica que agrupa profesores y ofrece cursos.
 *
 * @author Daniel Villa
 */
public class Departamento {

    private int idDepartamento;
    private String nombre;
    private ArrayList<Profesor> profesores;
    private ArrayList<Curso> cursos;

    /**
     * Crea un departamento sin profesores ni cursos.
     *
     * @param idDepartamento identificador
     * @param nombre         nombre del departamento
     */
    public Departamento(int idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.profesores = new ArrayList<Profesor>();
        this.cursos = new ArrayList<Curso>();
    }

    /**
     * Agrega un profesor al departamento.
     *
     * @param p el profesor
     */
    public void agregarProfesor(Profesor p) {
        profesores.add(p);
    }

    /**
     * Crea un curso dentro del departamento.
     *
     * @param codigo      código del curso
     * @param nombreCurso nombre del curso
     * @param creditos    número de créditos
     * @param cupoMaximo  cupo máximo de estudiantes
     * @param periodo     periodo académico
     * @return el curso creado
     */
    public Curso crearCurso(String codigo, String nombreCurso, int creditos, int cupoMaximo, String periodo) {
        Curso nuevo = new Curso(codigo, nombreCurso, creditos, cupoMaximo, periodo);
        cursos.add(nuevo);
        return nuevo;
    }

    /**
     * Lista los cursos del departamento.
     *
     * @return la lista de cursos
     */
    public ArrayList<Curso> listarCursos() {
        return cursos;
    }

    /**

     * Devuelve el identificador del departamento.

     *

     * @return el identificador del departamento

     */
    public int getIdDepartamento() {
        return idDepartamento;
    }

    /**

     * Devuelve el nombre del departamento.

     *

     * @return el nombre del departamento

     */
    public String getNombre() {
        return nombre;
    }

    /**

     * Devuelve la lista de profesores del departamento.

     *

     * @return la lista de profesores del departamento

     */
    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }
}
