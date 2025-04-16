package modelo;

import java.time.LocalDateTime;

public class Clase {
    private int idSesion;
    private String nombreClase;
    private LocalDateTime fecha;

    // Constructor
    public Clase(String nombreClase, LocalDateTime fecha) {
        this.nombreClase = nombreClase;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdSesion() { return idSesion; }
    public void setIdSesion(int idSesion) { this.idSesion = idSesion; }
    public String getNombreClase() { return nombreClase; }
    public void setNombreClase(String nombreClase) { this.nombreClase = nombreClase; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}