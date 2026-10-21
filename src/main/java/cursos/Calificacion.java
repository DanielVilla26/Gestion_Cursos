package cursos;

/**
 * Nota obtenida en una evaluación concreta (parcial, taller, examen...).
 * El peso se maneja como porcentaje: 30 significa que vale el 30% de la nota final.
 *
 * @author Daniel Villa
 */
public class Calificacion {

    private static int contador = 0;

    private int idCalificacion;
    private String tipoEvaluacion;
    private double valor;
    private double peso;

    /**
     * Crea una calificación.
     *
     * @param tipoEvaluacion nombre de la evaluación, por ejemplo "Parcial 1"
     * @param valor          nota obtenida (de 0 a 5)
     * @param peso           porcentaje que vale la evaluación (de 1 a 100)
     */
    public Calificacion(String tipoEvaluacion, double valor, double peso) {
        contador = contador + 1;
        this.idCalificacion = contador;
        this.tipoEvaluacion = tipoEvaluacion;
        this.valor = valor;
        this.peso = peso;
    }

    /**
     * Revisa que la nota esté entre 0 y 5 y que el peso esté entre 1 y 100.
     *
     * @return {@code true} si los datos son válidos, {@code false} si no
     */
    public boolean registrar() {
        if (valor < 0 || valor > 5) {
            System.out.println("La nota debe estar entre 0 y 5.");
            return false;
        }
        if (peso <= 0 || peso > 100) {
            System.out.println("El peso debe estar entre 1 y 100.");
            return false;
        }
        return true;
    }

    /**
     * Corrige el valor de la nota, por ejemplo si se digitó mal.
     *
     * @param nuevoValor la nota correcta (de 0 a 5)
     * @return {@code true} si se cambió, {@code false} si la nota no es válida
     */
    public boolean modificar(double nuevoValor) {
        if (nuevoValor < 0 || nuevoValor > 5) {
            System.out.println("La nota debe estar entre 0 y 5.");
            return false;
        }
        valor = nuevoValor;
        return true;
    }

    /**
     * Calcula cuánto aporta esta nota a la nota final: valor por peso entre 100.
     *
     * @return el aporte de esta calificación
     */
    public double obtenerValorPonderado() {
        return valor * peso / 100;
    }

    /**

     * Devuelve el identificador de la calificación.

     *

     * @return el identificador de la calificación

     */
    public int getIdCalificacion() {
        return idCalificacion;
    }

    /**

     * Devuelve el nombre de la evaluación.

     *

     * @return el nombre de la evaluación

     */
    public String getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    /**

     * Devuelve la nota obtenida.

     *

     * @return la nota obtenida

     */
    public double getValor() {
        return valor;
    }

    /**

     * Devuelve el porcentaje que vale la evaluación.

     *

     * @return el porcentaje que vale la evaluación

     */
    public double getPeso() {
        return peso;
    }
}
