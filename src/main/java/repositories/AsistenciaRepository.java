package repositories;

import java.sql.Connection;
import java.sql.SQLException;
import util.DatabaseConnection;
import modelo.Asistencia;

import java.sql.*;

public class AsistenciaRepository {
    public void insertAsistencia(modelo.Asistencia asistencia) throws SQLException {
        String sql = "INSERT INTO asistencia (id_alumno, id_sesion, asistio) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, asistencia.getIdAlumno());
            stmt.setInt(2, asistencia.getIdSesion());
            stmt.setInt(3, asistencia.isAsistio() ? 1 : 0);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    asistencia.setIdAsistencia(rs.getInt(1));
                }
            }
        }
    }

    public boolean checkAsistencia(int idAlumno, int idSesion) throws SQLException {
        String sql = "SELECT asistio FROM Asistencias WHERE id_alumno = ? AND id_sesion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAlumno);
            stmt.setInt(2, idSesion);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt("asistio") == 1;
            }
        }
    }

    public void deleteAsistenciaByAlumno(int idAlumno) throws SQLException {
        String sql = "DELETE FROM asistencia WHERE id_alumno = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAlumno);
            stmt.executeUpdate();
        }
    }
}

