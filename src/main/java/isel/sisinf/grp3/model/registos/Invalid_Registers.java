package isel.sisinf.grp3.model.registos;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "Registos_invalidos")
public class Invalid_Registers extends Unprocessed_Registers {
    @Id
    private int id;

    private int id_gps;

    @Column(name = "marca_temporal")
    private Time timeStamp;

//    @Override
//    public boolean equals (Object other) {
//        if (this == other) {
//            return true;
//        }
//        if(!(other instanceof Unprocessed_Registers)){return false;}
//        Invalid_Registers castOther = (Invalid_Registers)other;
//        return (this.id == castOther.id);
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(this.id);
//    }
}