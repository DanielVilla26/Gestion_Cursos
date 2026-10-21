package cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Pruebas de la clase {@link Inscripcion}. */
class InscripcionTest {

    private Curso curso;
    private Estudiante camila;
    private Inscripcion inscripcion;

    @BeforeEach
    void preparar() {
        Departamento sistemas = new Departamento(1, "Sistemas");
        curso = sistemas.crearCurso("SIS101", "POO", 4, 2, "2026-2");
        camila = new Estudiante(1, "Camila", "1000111", "camila@correo.com", "3101110000", "E-01", "Sistemas", 3);
        inscripcion = camila.inscribirse(curso);
    }

    @Test
    void unaInscripcionNuevaQuedaConfirmadaYActiva() {
        assertEquals("CONFIRMADA", inscripcion.getEstado());
        assertTrue(inscripcion.estaActiva());
    }

    @Test
    void unaInscripcionCreadaDirectamenteEmpiezaPendiente() {
        Inscripcion nueva = new Inscripcion(camila, curso);
        assertEquals("PENDIENTE", nueva.getEstado());
        nueva.confirmar();
        assertEquals("CONFIRMADA", nueva.getEstado());
    }

    @Test
    void notaFinalEsLaSumaPonderada() {
        inscripcion.agregarCalificacion(new Calificacion("Parcial", 4.0, 30));
        inscripcion.agregarCalificacion(new Calificacion("Taller", 3.5, 20));
        inscripcion.agregarCalificacion(new Calificacion("Examen final", 4.5, 50));
        assertEquals(4.15, inscripcion.calcularNotaFinal(), 0.001);
    }

    @Test
    void noPermitePesosQuePasenDelCien() {
        assertTrue(inscripcion.agregarCalificacion(new Calificacion("Parcial", 3.0, 60)));
        assertFalse(inscripcion.agregarCalificacion(new Calificacion("Examen", 3.0, 50)));
        assertEquals(60, inscripcion.getPesoTotal(), 0.001);
    }

    @Test
    void rechazaUnaNotaFueraDeRango() {
        assertFalse(inscripcion.agregarCalificacion(new Calificacion("Parcial", 6.0, 30)));
        assertEquals(0, inscripcion.getCalificaciones().size());
    }

    @Test
    void unaInscripcionPendienteNoRecibeNotas() {
        Inscripcion pendiente = new Inscripcion(camila, curso);
        assertFalse(pendiente.agregarCalificacion(new Calificacion("Parcial", 4.0, 30)));
    }

    @Test
    void resultadoEnCursoMientrasFaltenEvaluaciones() {
        inscripcion.agregarCalificacion(new Calificacion("Parcial", 5.0, 40));
        assertEquals("EN CURSO", inscripcion.resultado());
    }

    @Test
    void resultadoAprobadoConTresOMas() {
        inscripcion.agregarCalificacion(new Calificacion("Todo", 3.0, 100));
        assertEquals("APROBADO", inscripcion.resultado());
    }

    @Test
    void resultadoReprobadoConMenosDeTres() {
        inscripcion.agregarCalificacion(new Calificacion("Todo", 2.9, 100));
        assertEquals("REPROBADO", inscripcion.resultado());
    }

    @Test
    void retirarDejaLaInscripcionInactiva() {
        inscripcion.retirar();
        assertFalse(inscripcion.estaActiva());
        assertEquals("RETIRADO", inscripcion.resultado());
    }

    @Test
    void unaInscripcionRetiradaNoRecibeNotas() {
        inscripcion.retirar();
        assertFalse(inscripcion.agregarCalificacion(new Calificacion("Parcial", 4.0, 30)));
    }

    @Test
    void corregirUnaNotaCambiaLaNotaFinal() {
        inscripcion.agregarCalificacion(new Calificacion("Todo", 2.0, 100));
        inscripcion.getCalificaciones().get(0).modificar(4.0);
        assertEquals(4.0, inscripcion.getNotaFinal(), 0.001);
    }
}
