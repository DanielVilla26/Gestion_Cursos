package cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Pruebas de la clase {@link Curso}. */
class CursoTest {

    private Curso curso;
    private Estudiante a;
    private Estudiante b;
    private Estudiante c;

    @BeforeEach
    void preparar() {
        curso = new Departamento(1, "Sistemas").crearCurso("SIS101", "POO", 4, 2, "2026-2");
        a = new Estudiante(1, "Ana", "1", "a@correo.com", "1", "E-01", "Sistemas", 1);
        b = new Estudiante(2, "Beto", "2", "b@correo.com", "2", "E-02", "Sistemas", 1);
        c = new Estudiante(3, "Carla", "3", "c@correo.com", "3", "E-03", "Sistemas", 1);
    }

    @Test
    void guardaSusDatos() {
        assertEquals("SIS101", curso.getCodigo());
        assertEquals("POO", curso.getNombre());
        assertEquals(4, curso.getCreditos());
        assertEquals(2, curso.getCupoMaximo());
        assertEquals("2026-2", curso.getPeriodo());
    }

    @Test
    void empiezaSinInscritosYConCupo() {
        assertEquals(0, curso.contarInscritos());
        assertTrue(curso.tieneCupo());
    }

    @Test
    void sinProfesorAlPrincipio() {
        assertNull(curso.getProfesor());
    }

    @Test
    void sinCupoCuandoSeLlena() {
        a.inscribirse(curso);
        b.inscribirse(curso);
        assertEquals(2, curso.contarInscritos());
        assertFalse(curso.tieneCupo());
    }

    @Test
    void retirarUnEstudianteLiberaUnCupo() {
        a.inscribirse(curso);
        b.inscribirse(curso);
        a.retirarCurso(curso);
        assertEquals(1, curso.contarInscritos());
        assertTrue(curso.tieneCupo());
    }

    @Test
    void listarInscritosNoMuestraALosRetirados() {
        a.inscribirse(curso);
        b.inscribirse(curso);
        a.retirarCurso(curso);
        assertEquals(1, curso.listarInscritos().size());
        assertSame(b, curso.listarInscritos().get(0));
        assertNotNull(c.inscribirse(curso));
        assertEquals(2, curso.listarInscritos().size());
    }

    @Test
    void asignarProfesorLoGuardaYLoEnlazaConElProfesor() {
        Profesor laura = new Profesor(100, "Laura", "43111222", "laura@correo.com", "300", "P-01", "Programación", "Magíster");
        curso.asignarProfesor(laura);
        assertSame(laura, curso.getProfesor());
        assertTrue(laura.listarCursos().contains(curso));
    }
}
