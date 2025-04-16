import modelo.Alumno;
import modelo.Clase;
import modelo.Asistencia;
import repositories.AlumnoRepository;
import repositories.ClaseRepository;
import repositories.AsistenciaRepository;
import util.DatabaseConnection;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class MainApp {
    public static void main(String[] args) {

        try {
            // 1. Crear un alumno
            Alumno alumno = new Alumno("Carlos Munoz", "CarlosM@mail.com");
            AlumnoRepository alumnoRepo = new AlumnoRepository();
            alumnoRepo.insertAlumno(alumno);
            System.out.println("Alumno creado con ID: " + alumno.getIdUsuario());

            // 2. Crear una clase
            Clase clase = new Clase("Base de datos", LocalDateTime.now());
            ClaseRepository claseRepo = new ClaseRepository();
            claseRepo.insertClase(clase);
            System.out.println("Clase creada con ID: " + clase.getIdSesion());

            // 3. Registrar asistencia
            Asistencia asistencia = new Asistencia(
                    alumno.getIdUsuario(),
                    clase.getIdSesion(),
                    true
            );
            AsistenciaRepository asistenciaRepo = new AsistenciaRepository();
            asistenciaRepo.insertAsistencia(asistencia);
            System.out.println("Asistencia registrada.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
        // 4. Eliminar un alumno
//        AlumnoRepository alumnoRepo = new AlumnoRepository();
//        int idAlumnoBorrar = 1; // Cambia esto al ID del alumno que deseas eliminar
//        try {
//            boolean eliminado = alumnoRepo.deleteAlumno(idAlumnoBorrar);
//            if (eliminado) {
//                System.out.println("Alumno con ID " + idAlumnoBorrar + " eliminado.");
//            } else {
//                System.out.println("No se encontró el alumno con ID " + idAlumnoBorrar + ".");
//            }
//        } catch (SQLException e) {
//            System.err.println("Error al eliminar el alumno: " + e.getMessage());
//        }
    }
}
