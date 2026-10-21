package cursos;

import java.util.ArrayList;

/**
 * Persona que dicta cursos y registra las calificaciones de sus estudiantes.
 *
 * @author Daniel Villa
 */
public class Profesor extends Persona {

    private String codigo;
    private String especialidad;
    private String titulo;
    private ArrayList<Curso> cursos;

    /**
     * Crea un profesor.
     *
     * @param idPersona    identificador
     * @param nombre       nombre completo
     * @param documento    documento de identidad
     * @param email        correo electrónico
     * @param telefono     teléfono
     * @param codigo       código del profesor
     * @param especialidad área en la que se especializa
     * @param titulo       título académico
     */
    public Profesor(int idPersona, String nombre, String documento, String email, String telefono,
                    String codigo, String especialidad, String titulo) {
        super(idPersona, nombre, documento, email, telefono);
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.titulo = titulo;
        this.cursos = new ArrayList<Curso>();
    }

    /**
     * Agrega un curso a la lista del profesor. Lo usa {@link Curso#asignarProfesor(Profesor)}.
     *
     * @param curso el curso que dicta
     */
    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    /**
     * Lista los cursos que dicta el profesor.
     *
     * @return la lista de cursos
     */
    public ArrayList<Curso> listarCursos() {
        return cursos;
    }

    /**
     * Lista los estudiantes inscritos en un curso. Solo puede hacerlo quien lo dicta.
     *
     * @param curso el curso consultado
     * @return los estudiantes inscritos, o una lista vacía si el profesor no dicta el curso
     */
    public ArrayList<Estudiante> listarEstudiantes(Curso curso) {
        if (curso.getProfesor() != this) {
            System.out.println(getNombre() + " no dicta " + curso.getNombre() + ".");
            return new ArrayList<Estudiante>();
        }
        return curso.listarInscritos();
    }

    /**
     * Pone una nota a un estudiante. Solo puede hacerlo el profesor que dicta el curso.
     *
     * @param inscripcion la inscripción del estudiante
     * @param tipo        nombre de la evaluación
     * @param valor       nota (de 0 a 5)
     * @param peso        porcentaje de la evaluación (de 1 a 100)
     * @return {@code true} si se registró, {@code false} si se rechazó
     */
    public boolean registrarCalificacion(Inscripcion inscripcion, String tipo, double valor, double peso) {
        if (inscripcion.getCurso().getProfesor() != this) {
            System.out.println(getNombre() + " no dicta " + inscripcion.getCurso().getNombre() + ".");
            return false;
        }
        Calificacion nueva = new Calificacion(tipo, valor, peso);
        return inscripcion.agregarCalificacion(nueva);
    }

    /**

     * Devuelve el código del profesor.

     *

     * @return el código del profesor

     */
    public String getCodigo() {
        return codigo;
    }

    /**

     * Devuelve la especialidad del profesor.

     *

     * @return la especialidad del profesor

     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**

     * Devuelve el título académico del profesor.

     *

     * @return el título académico del profesor

     */
    public String getTitulo() {
        return titulo;
    }
}
