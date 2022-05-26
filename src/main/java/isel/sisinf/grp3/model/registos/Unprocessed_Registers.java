package isel.sisinf.grp3.model.registos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "registos_nao_processados")
abstract class Unprocessed_Registers {
    @Id
    private int id;

    private int id_gps;

    @Column(name = "marca_temporal")
    private Time timeStamp;

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Unprocessed_Registers)){return false;}
        Unprocessed_Registers castOther = (Unprocessed_Registers)other;
        return (this.id == castOther.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
