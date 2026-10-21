package cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Pruebas de la clase {@link Estudiante}. */
class EstudianteTest {

    private Departamento sistemas;
    private Profesor laura;
    private Curso poo;
    private Curso bd;
    private Estudiante camila;
    private Estudiante andres;

    @BeforeEach
    void preparar() {
        sistemas = new Departamento(1, "Sistemas");
        laura = new Profesor(100, "Laura", "43111222", "laura@correo.com", "300", "P-01", "Programación", "Magíster");
        poo = sistemas.crearCurso("SIS101", "POO", 4, 1, "2026-2");
        bd = sistemas.crearCurso("SIS102", "Bases de Datos", 3, 30, "2026-2");
        poo.asignarProfesor(laura);
        bd.asignarProfesor(laura);
        camila = new Estudiante(1, "Camila", "1000111", "camila@correo.com", "310", "E-01", "Sistemas", 3);
        andres = new Estudiante(2, "Andrés", "1000222", "andres@correo.com", "311", "E-02", "Sistemas", 3);
    }

    @Test
    void unEstudianteEsUnaPersona() {
        assertTrue(camila instanceof Persona);
    }

    @Test
    void actualizarDatosCambiaNombreYCorreoPeroNoElDocumento() {
        camila.actualizarDatos("Camila R.", "nuevo@correo.com", "312");
        assertEquals("Camila R.", camila.obtenerNombreCompleto());
        assertEquals("nuevo@correo.com", camila.getEmail());
        assertEquals("1000111", camila.getDocumento());
    }

    @Test
    void inscribirseCreaLaInscripcion() {
        Inscripcion i = camila.inscribirse(poo);
        assertNotNull(i);
        assertEquals(1, poo.contarInscritos());
        assertSame(i, camila.buscarInscripcion(poo));
    }

    @Test
    void noPermiteInscribirseDosVecesEnElMismoCurso() {
        camila.inscribirse(bd);
        assertNull(camila.inscribirse(bd));
        assertEquals(1, bd.contarInscritos());
    }

    @Test
    void noPermiteInscribirseSiElCursoEstaLleno() {
        camila.inscribirse(poo);
        assertNull(andres.inscribirse(poo));
        assertEquals(1, poo.contarInscritos());
    }

    @Test
    void retirarseLiberaElCupo() {
        camila.inscribirse(poo);
        assertTrue(camila.retirarCurso(poo));
        assertTrue(poo.tieneCupo());
        assertNotNull(andres.inscribirse(poo));
    }

    @Test
    void retirarUnCursoSinEstarInscritoDevuelveFalso() {
        assertFalse(camila.retirarCurso(poo));
    }

    @Test
    void sePuedeVolverAInscribirDespuesDeRetirarse() {
        camila.inscribirse(bd);
        camila.retirarCurso(bd);
        assertNotNull(camila.inscribirse(bd));
    }

    @Test
    void promedioEsCeroSinCursosTerminados() {
        Inscripcion i = camila.inscribirse(bd);
        laura.registrarCalificacion(i, "Parcial", 5.0, 40);
        assertEquals(0, camila.calcularPromedio(), 0.001);
    }

    @Test
    void promedioCuentaSoloLosCursosTerminados() {
        Inscripcion terminado = camila.inscribirse(poo);
        laura.registrarCalificacion(terminado, "Todo", 4.0, 100);
        Inscripcion enCurso = camila.inscribirse(bd);
        laura.registrarCalificacion(enCurso, "Parcial", 1.0, 30);
        assertEquals(4.0, camila.calcularPromedio(), 0.001);
    }

    @Test
    void promedioEsElPromedioDeLosCursosTerminados() {
        Inscripcion a = camila.inscribirse(poo);
        Inscripcion b = camila.inscribirse(bd);
        laura.registrarCalificacion(a, "Todo", 4.0, 100);
        laura.registrarCalificacion(b, "Todo", 3.0, 100);
        assertEquals(3.5, camila.calcularPromedio(), 0.001);
    }

    @Test
    void consultarCalificacionesJuntaLasNotasDeTodosLosCursos() {
        Inscripcion a = camila.inscribirse(poo);
        Inscripcion b = camila.inscribirse(bd);
        laura.registrarCalificacion(a, "Parcial", 4.0, 50);
        laura.registrarCalificacion(b, "Parcial", 3.0, 50);
        assertEquals(2, camila.consultarCalificaciones().size());
    }

    @Test
    void consultarCalificacionesIgnoraLosCursosRetirados() {
        Inscripcion a = camila.inscribirse(bd);
        laura.registrarCalificacion(a, "Parcial", 4.0, 50);
        camila.retirarCurso(bd);
        assertEquals(0, camila.consultarCalificaciones().size());
    }
}
