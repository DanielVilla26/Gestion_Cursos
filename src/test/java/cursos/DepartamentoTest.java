package cursos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Pruebas de la clase {@link Departamento}. */
class DepartamentoTest {

    private Departamento sistemas;

    @BeforeEach
    void preparar() {
        sistemas = new Departamento(1, "Ingeniería de Sistemas");
    }

    @Test
    void guardaSuIdYSuNombre() {
        assertEquals(1, sistemas.getIdDepartamento());
        assertEquals("Ingeniería de Sistemas", sistemas.getNombre());
    }

    @Test
    void empiezaSinCursosNiProfesores() {
        assertTrue(sistemas.listarCursos().isEmpty());
        assertTrue(sistemas.getProfesores().isEmpty());
    }

    @Test
    void crearCursoLoAgregaAlDepartamento() {
        Curso poo = sistemas.crearCurso("SIS101", "POO", 4, 30, "2026-2");
        assertEquals(1, sistemas.listarCursos().size());
        assertSame(poo, sistemas.listarCursos().get(0));
        assertEquals("POO", poo.getNombre());
    }

    @Test
    void puedeTenerVariosCursos() {
        sistemas.crearCurso("SIS101", "POO", 4, 30, "2026-2");
        sistemas.crearCurso("SIS102", "Bases de Datos", 3, 30, "2026-2");
        assertEquals(2, sistemas.listarCursos().size());
    }

    @Test
    void agregarProfesorLoGuarda() {
        Profesor laura = new Profesor(100, "Laura", "43111222", "laura@correo.com", "300", "P-01", "Programación", "Magíster");
        sistemas.agregarProfesor(laura);
        assertTrue(sistemas.getProfesores().contains(laura));
    }
}
