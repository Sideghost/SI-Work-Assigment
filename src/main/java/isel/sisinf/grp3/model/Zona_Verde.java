package isel.sisinf.grp3.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Zona_Verde {
    @Id
    private int id;
    private int raio;
    private double longitude;
    private double latitude;
    private String matricula;

    public int getZonaVerdeId() {
        return id;
    }
    public void setZonaVerdeId(int zonaVerdeId) {
        this.id = zonaVerdeId;
    }

    public int getZonaVerdeRaio() {
        return raio;
    }
    public void setZonaVerdeRaio(int zonaVerdeRaio) {
        this.raio = zonaVerdeRaio;
    }

    public double getZonaVerdeLongitude() {
        return longitude;
    }
    public void setZonaVerdeLongitude(double zonaVerdeLongitude) {
        this.longitude = zonaVerdeLongitude;
    }

    public double getZonaVerdeLatitude() {
        return latitude;
    }
    public void setZonaVerdeId(double zonaVerdeLatitude) {
        this.latitude = zonaVerdeLatitude;
    }

    public String getZonaVerdeMatricula() {
        return matricula;
    }
    public void setZonaVerdeMatricula(String zonaVerdeMatricula) {
        this.matricula = zonaVerdeMatricula;
    }
    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Zona_Verde)){return false;}
        Zona_Verde castOther = (Zona_Verde)other;
        return (this.id == castOther.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
