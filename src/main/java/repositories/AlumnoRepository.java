package repositories;

import modelo.Alumno;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {
    public void insertAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO Alumnos (nombre, email) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getEmail());
            stmt.executeUpdate();

            // Obtener ID generado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    alumno.setIdUsuario(rs.getInt(1));
                }
            }
        }
    }

    public List<Alumno> getAllAlumnos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM Alumnos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("nombre"),
                        rs.getString("email")
                );
                alumno.setIdUsuario(rs.getInt("id_usuario"));
                alumnos.add(alumno);
            }
        }
        return alumnos;
    }

    public boolean alumnoExists(int idUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Alumnos WHERE id_usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public boolean deleteAlumno(int idUsuario) throws SQLException {
        String sql = "DELETE FROM Alumnos WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            int rowsAffected = stmt.executeUpdate();
            return stmt.executeUpdate() > 0;
        }
    }
}

