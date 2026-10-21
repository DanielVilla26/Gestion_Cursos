package cursos;

/**
 * Clase abstracta que reúne los datos comunes de las personas del sistema.
 * No se puede crear una {@code Persona} directamente: siempre es un
 * {@link Estudiante} o un {@link Profesor}.
 *
 * @author Daniel Villa
 */
public abstract class Persona {

    private int idPersona;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;

    /**
     * Crea una persona con sus datos básicos.
     *
     * @param idPersona identificador de la persona
     * @param nombre    nombre completo
     * @param documento número de documento
     * @param email     correo electrónico
     * @param telefono  número de teléfono
     */
    public Persona(int idPersona, String nombre, String documento, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Cambia los datos de contacto de la persona. El documento no se modifica.
     *
     * @param nombre   nuevo nombre
     * @param email    nuevo correo
     * @param telefono nuevo teléfono
     */
    public void actualizarDatos(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Devuelve el nombre completo de la persona.
     *
     * @return el nombre completo
     */
    public String obtenerNombreCompleto() {
        return nombre;
    }

    /**

     * Devuelve el identificador de la persona.

     *

     * @return el identificador de la persona

     */
    public int getIdPersona() {
        return idPersona;
    }

    /**

     * Devuelve el nombre de la persona.

     *

     * @return el nombre de la persona

     */
    public String getNombre() {
        return nombre;
    }

    /**

     * Devuelve el documento de la persona.

     *

     * @return el documento de la persona

     */
    public String getDocumento() {
        return documento;
    }

    /**

     * Devuelve el correo de la persona.

     *

     * @return el correo de la persona

     */
    public String getEmail() {
        return email;
    }

    /**

     * Devuelve el teléfono de la persona.

     *

     * @return el teléfono de la persona

     */
    public String getTelefono() {
        return telefono;
    }
}
