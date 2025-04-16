package modelo;

public class Asistencia {
    private int idAsistencia;
    private int idAlumno;
    private int idSesion;
    private boolean asistio;  // Mapeado desde INTEGER (0 o 1)

    // Constructor
    public Asistencia(int idAlumno, int idSesion, boolean asistio) {
        this.idAlumno = idAlumno;
        this.idSesion = idSesion;
        this.asistio = asistio;
    }

    // Getters y Setters
    public int getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(int idAsistencia) { this.idAsistencia = idAsistencia; }
    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }
    public int getIdSesion() { return idSesion; }
    public void setIdSesion(int idSesion) { this.idSesion = idSesion; }
    public boolean isAsistio() { return asistio; }
    public void setAsistio(boolean asistio) { this.asistio = asistio; }
}