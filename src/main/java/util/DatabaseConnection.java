package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:/Users/255_Studio/Documents/Dev-Projects/asistenciaBooster/src/main/resources/asistenciaBooster.db";
    private static Connection instance;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }
    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL);
            createTablesIfNotExist();
        }
        return instance;
    }

    private static void createTablesIfNotExist() throws SQLException {
        String[] tables = {
                "CREATE TABLE IF NOT EXISTS Alumnos (" +
                        "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT NOT NULL, " +
                        "email TEXT UNIQUE)",

                "CREATE TABLE IF NOT EXISTS Clases (" +
                        "id_sesion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre_clase TEXT NOT NULL, " +
                        "fecha TEXT NOT NULL)",

                "CREATE TABLE IF NOT EXISTS Asistencias (" +
                        "id_asistencia INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "id_alumno INTEGER NOT NULL, " +
                        "id_sesion INTEGER NOT NULL, " +
                        "asistio INTEGER NOT NULL, " +
                        "FOREIGN KEY (id_alumno) REFERENCES Alumnos(id_usuario), " +
                        "FOREIGN KEY (id_sesion) REFERENCES Clases(id_sesion))"
        };

        try (Statement stmt = instance.createStatement()) {
            for (String sql : tables) {
                stmt.execute(sql);
            }
        }
    }

    public static void closeConnection() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.close();
            instance = null;
        }
    }
}

