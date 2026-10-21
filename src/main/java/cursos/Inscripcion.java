package cursos;

import java.util.ArrayList;

/**
 * Une a un {@link Estudiante} con un {@link Curso} y guarda sus calificaciones.
 * Es la clase que resuelve la relación de muchos a muchos entre estudiantes y cursos.
 *
 * @author Daniel Villa
 */
public class Inscripcion {

    private static int contador = 0;

    private int idInscripcion;
    private String fechaInscripcion;
    private String estado;
    private double notaFinal;
    private Estudiante estudiante;
    private Curso curso;
    private ArrayList<Calificacion> calificaciones;

    /**
     * Crea una inscripción en estado PENDIENTE. Después hay que confirmarla.
     *
     * @param estudiante el estudiante que se inscribe
     * @param curso      el curso en el que se inscribe
     */
    public Inscripcion(Estudiante estudiante, Curso curso) {
        contador = contador + 1;
        this.idInscripcion = contador;
        this.fechaInscripcion = java.time.LocalDate.now().toString();
        this.estado = "PENDIENTE";
        this.notaFinal = 0;
        this.estudiante = estudiante;
        this.curso = curso;
        this.calificaciones = new ArrayList<Calificacion>();
    }

    /** Pasa la inscripción al estado CONFIRMADA. */
    public void confirmar() {
        estado = "CONFIRMADA";
    }

    /** Pasa la inscripción al estado RETIRADA y libera el cupo del curso. */
    public void retirar() {
        estado = "RETIRADA";
    }

    /**
     * Calcula la nota final sumando el aporte de cada calificación.
     *
     * @return la nota final
     */
    public double calcularNotaFinal() {
        double suma = 0;
        for (Calificacion c : calificaciones) {
            suma = suma + c.obtenerValorPonderado();
        }
        notaFinal = suma;
        return notaFinal;
    }

    /**
     * Agrega una calificación. Solo se acepta si la inscripción está confirmada,
     * si la nota es válida y si los pesos no pasan del 100%.
     *
     * @param c la calificación a agregar
     * @return {@code true} si se agregó, {@code false} si se rechazó
     */
    public boolean agregarCalificacion(Calificacion c) {
        if (!estado.equals("CONFIRMADA")) {
            System.out.println("La inscripción no está confirmada.");
            return false;
        }
        if (getPesoTotal() + c.getPeso() > 100) {
            System.out.println("Los pesos no pueden pasar del 100%.");
            return false;
        }
        if (!c.registrar()) {
            return false;
        }
        calificaciones.add(c);
        calcularNotaFinal();
        return true;
    }

    /**
     * Suma los porcentajes de las evaluaciones ya registradas.
     *
     * @return el peso total, entre 0 y 100
     */
    public double getPesoTotal() {
        double total = 0;
        for (Calificacion c : calificaciones) {
            total = total + c.getPeso();
        }
        return total;
    }

    /**
     * Indica si la inscripción sigue vigente.
     *
     * @return {@code true} si no está retirada
     */
    public boolean estaActiva() {
        return !estado.equals("RETIRADA");
    }

    /**
     * Resume cómo va el estudiante: RETIRADO, EN CURSO (faltan evaluaciones),
     * APROBADO (nota final de 3.0 o más) o REPROBADO.
     *
     * @return el resultado como texto
     */
    public String resultado() {
        if (!estaActiva()) {
            return "RETIRADO";
        }
        if (getPesoTotal() < 100) {
            return "EN CURSO";
        }
        if (calcularNotaFinal() >= 3.0) {
            return "APROBADO";
        }
        return "REPROBADO";
    }

    /**

     * Devuelve el identificador de la inscripción.

     *

     * @return el identificador de la inscripción

     */
    public int getIdInscripcion() {
        return idInscripcion;
    }

    /**

     * Devuelve la fecha de la inscripción en formato aaaa-mm-dd.

     *

     * @return la fecha de la inscripción en formato aaaa-mm-dd

     */
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**

     * Devuelve el estado: PENDIENTE, CONFIRMADA o RETIRADA.

     *

     * @return el estado: PENDIENTE, CONFIRMADA o RETIRADA

     */
    public String getEstado() {
        return estado;
    }

    /**

     * Devuelve la nota final, recalculada con las calificaciones actuales.

     *

     * @return la nota final, recalculada con las calificaciones actuales

     */
    public double getNotaFinal() {
        return calcularNotaFinal();
    }

    /**

     * Devuelve el estudiante inscrito.

     *

     * @return el estudiante inscrito

     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**

     * Devuelve el curso de la inscripción.

     *

     * @return el curso de la inscripción

     */
    public Curso getCurso() {
        return curso;
    }

    /**

     * Devuelve la lista de calificaciones registradas.

     *

     * @return la lista de calificaciones registradas

     */
    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }
}
