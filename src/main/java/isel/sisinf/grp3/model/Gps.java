package isel.sisinf.grp3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Objects;

@Entity
public class Gps {
    @Id
    private Integer id;

    @Column(name = "longitude")
    private Double longi;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "matricula")
    private Integer license_plate;

    @Column(name = "estado")
    private String status;

    @Column(name = "marca_temporal")
    private Time timeStamp;

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Gps)){return false;}
        Gps castOther = (Gps)other;
        return (this.id.equals(castOther.id));
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
