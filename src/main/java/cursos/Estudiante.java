package cursos;

import java.util.ArrayList;

/**
 * Persona matriculada que se inscribe en cursos y consulta sus notas.
 *
 * @author Daniel Villa
 */
public class Estudiante extends Persona {

    private String codigo;
    private String carrera;
    private int semestre;
    private ArrayList<Inscripcion> inscripciones;

    /**
     * Crea un estudiante.
     *
     * @param idPersona identificador
     * @param nombre    nombre completo
     * @param documento documento de identidad
     * @param email     correo electrónico
     * @param telefono  teléfono
     * @param codigo    código estudiantil
     * @param carrera   carrera que cursa
     * @param semestre  semestre actual
     */
    public Estudiante(int idPersona, String nombre, String documento, String email, String telefono,
                      String codigo, String carrera, int semestre) {
        super(idPersona, nombre, documento, email, telefono);
        this.codigo = codigo;
        this.carrera = carrera;
        this.semestre = semestre;
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    /**
     * Inscribe al estudiante en un curso. Falla si ya está inscrito o si no hay cupo.
     *
     * @param curso el curso al que se quiere inscribir
     * @return la inscripción creada y confirmada, o {@code null} si no fue posible
     */
    public Inscripcion inscribirse(Curso curso) {
        if (buscarInscripcion(curso) != null) {
            System.out.println(getNombre() + " ya está inscrito en " + curso.getNombre() + ".");
            return null;
        }
        if (!curso.tieneCupo()) {
            System.out.println("No hay cupo en " + curso.getNombre() + ".");
            return null;
        }
        Inscripcion nueva = new Inscripcion(this, curso);
        nueva.confirmar();
        curso.agregarInscripcion(nueva);
        inscripciones.add(nueva);
        return nueva;
    }

    /**
     * Retira al estudiante de un curso. El cupo queda libre para otra persona.
     *
     * @param curso el curso del que se retira
     * @return {@code true} si se retiró, {@code false} si no estaba inscrito
     */
    public boolean retirarCurso(Curso curso) {
        Inscripcion i = buscarInscripcion(curso);
        if (i == null) {
            System.out.println(getNombre() + " no está inscrito en " + curso.getNombre() + ".");
            return false;
        }
        i.retirar();
        return true;
    }

    /**
     * Junta todas las notas del estudiante en los cursos donde sigue inscrito.
     *
     * @return la lista de calificaciones
     */
    public ArrayList<Calificacion> consultarCalificaciones() {
        ArrayList<Calificacion> todas = new ArrayList<Calificacion>();
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva()) {
                todas.addAll(i.getCalificaciones());
            }
        }
        return todas;
    }

    /**
     * Calcula el promedio de las notas finales de los cursos terminados,
     * es decir, los que ya tienen el 100% de las notas registradas.
     *
     * @return el promedio, o 0 si todavía no hay cursos terminados
     */
    public double calcularPromedio() {
        double suma = 0;
        int cursosTerminados = 0;
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getPesoTotal() == 100) {
                suma = suma + i.getNotaFinal();
                cursosTerminados = cursosTerminados + 1;
            }
        }
        if (cursosTerminados == 0) {
            return 0;
        }
        return suma / cursosTerminados;
    }

    /**
     * Busca la inscripción vigente del estudiante en un curso.
     *
     * @param curso el curso que se busca
     * @return la inscripción, o {@code null} si no está inscrito
     */
    public Inscripcion buscarInscripcion(Curso curso) {
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getCurso() == curso) {
                return i;
            }
        }
        return null;
    }

    /**

     * Devuelve el código estudiantil.

     *

     * @return el código estudiantil

     */
    public String getCodigo() {
        return codigo;
    }

    /**

     * Devuelve la carrera que cursa.

     *

     * @return la carrera que cursa

     */
    public String getCarrera() {
        return carrera;
    }

    /**

     * Devuelve el semestre actual.

     *

     * @return el semestre actual

     */
    public int getSemestre() {
        return semestre;
    }

    /**

     * Devuelve todas las inscripciones del estudiante, incluso las retiradas.

     *

     * @return todas las inscripciones del estudiante, incluso las retiradas

     */
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
}
