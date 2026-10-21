package cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Pruebas de la clase {@link Calificacion}. */
class CalificacionTest {

    @Test
    void valorPonderadoEsNotaPorPesoEntreCien() {
        Calificacion c = new Calificacion("Parcial", 4.0, 30);
        assertEquals(1.2, c.obtenerValorPonderado(), 0.001);
    }

    @Test
    void registrarAceptaUnaNotaValida() {
        Calificacion c = new Calificacion("Taller", 3.5, 20);
        assertTrue(c.registrar());
    }

    @Test
    void registrarRechazaNotaMayorACinco() {
        Calificacion c = new Calificacion("Taller", 5.1, 20);
        assertFalse(c.registrar());
    }

    @Test
    void registrarRechazaNotaNegativa() {
        Calificacion c = new Calificacion("Taller", -1, 20);
        assertFalse(c.registrar());
    }

    @Test
    void registrarRechazaPesoCero() {
        Calificacion c = new Calificacion("Taller", 3.0, 0);
        assertFalse(c.registrar());
    }

    @Test
    void registrarRechazaPesoMayorACien() {
        Calificacion c = new Calificacion("Taller", 3.0, 101);
        assertFalse(c.registrar());
    }

    @Test
    void modificarCambiaElValor() {
        Calificacion c = new Calificacion("Parcial", 2.0, 30);
        assertTrue(c.modificar(3.0));
        assertEquals(3.0, c.getValor(), 0.001);
    }

    @Test
    void modificarRechazaUnaNotaInvalida() {
        Calificacion c = new Calificacion("Parcial", 2.0, 30);
        assertFalse(c.modificar(7.0));
        assertEquals(2.0, c.getValor(), 0.001);
    }

    @Test
    void cadaCalificacionTieneUnIdDistinto() {
        Calificacion a = new Calificacion("A", 3.0, 10);
        Calificacion b = new Calificacion("B", 3.0, 10);
        assertEquals(a.getIdCalificacion() + 1, b.getIdCalificacion());
    }
}
