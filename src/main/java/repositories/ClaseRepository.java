package repositories;

import modelo.Clase;
import util.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class ClaseRepository {
    public void insertClase(Clase clase) throws SQLException {
        String sql = "INSERT INTO clases (nombre_clase, fecha) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, clase.getNombreClase());
            stmt.setString(2, clase.getFecha().toString());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    clase.setIdSesion(rs.getInt(1));
                }
            }
        }
    }

    public List<Clase> getAllClases() throws SQLException {
        List<Clase> clases = new ArrayList<>();
        String sql = "SELECT * FROM clases";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Clase clase = new Clase(
                        rs.getString("nombre_clase"),
                        LocalDateTime.parse(rs.getString("fecha"), DateTimeFormatter.ISO_DATE_TIME)
                );
                clase.setIdSesion(rs.getInt("id_sesion"));
                clases.add(clase);
            }
        }
        return clases;
    }
}

