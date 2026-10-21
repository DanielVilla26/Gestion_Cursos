package cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Pruebas de la clase {@link Profesor}. */
class ProfesorTest {

    private Curso poo;
    private Curso redes;
    private Profesor laura;
    private Profesor carlos;
    private Estudiante camila;
    private Inscripcion camilaPoo;

    @BeforeEach
    void preparar() {
        Departamento sistemas = new Departamento(1, "Sistemas");
        laura = new Profesor(100, "Laura", "43111222", "laura@correo.com", "300", "P-01", "Programación", "Magíster");
        carlos = new Profesor(101, "Carlos", "70222333", "carlos@correo.com", "301", "P-02", "Redes", "Ingeniero");
        poo = sistemas.crearCurso("SIS101", "POO", 4, 5, "2026-2");
        redes = sistemas.crearCurso("SIS103", "Redes", 3, 5, "2026-2");
        poo.asignarProfesor(laura);
        redes.asignarProfesor(carlos);
        camila = new Estudiante(1, "Camila", "1000111", "camila@correo.com", "310", "E-01", "Sistemas", 3);
        camilaPoo = camila.inscribirse(poo);
    }

    @Test
    void unProfesorEsUnaPersona() {
        assertTrue(laura instanceof Persona);
    }

    @Test
    void registraNotasEnElCursoQueDicta() {
        assertTrue(laura.registrarCalificacion(camilaPoo, "Parcial", 4.0, 30));
        assertEquals(1.2, camilaPoo.getNotaFinal(), 0.001);
    }

    @Test
    void unProfesorAjenoNoPuedeRegistrarNotas() {
        assertFalse(carlos.registrarCalificacion(camilaPoo, "Parcial", 4.0, 30));
        assertEquals(0, camilaPoo.getPesoTotal(), 0.001);
    }

    @Test
    void noRegistraNotasInvalidas() {
        assertFalse(laura.registrarCalificacion(camilaPoo, "Parcial", 6.0, 30));
    }

    @Test
    void listaLosEstudiantesDeSuCurso() {
        assertEquals(1, laura.listarEstudiantes(poo).size());
        assertSame(camila, laura.listarEstudiantes(poo).get(0));
    }

    @Test
    void noListaLosEstudiantesDeUnCursoAjeno() {
        assertTrue(carlos.listarEstudiantes(poo).isEmpty());
    }

    @Test
    void listaSusCursos() {
        assertEquals(1, laura.listarCursos().size());
        assertSame(poo, laura.listarCursos().get(0));
    }
}
