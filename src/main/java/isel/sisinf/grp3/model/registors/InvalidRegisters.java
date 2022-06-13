package isel.sisinf.grp3.model.registors;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

//import java.time.LocalDate;

/**
 * todo
 */
@Entity
@NamedQuery(name = "InvalidRegisters.findByKey",
        query = "SELECT ir FROM InvalidRegisters ir WHERE ir.id =:key")
@NamedStoredProcedureQuery(
        name = "eliminateInvalidRegisters",
        procedureName = "eliminate_invalid_registers",
        parameters = {}
)
@Table(name = "Registos_invalidos")
public class InvalidRegisters implements IInvalidRegisters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_gps")
    private Long idGps;

    @Column(name = "marca_temporal", nullable = false)
    private Timestamp timeStamp;

    public InvalidRegisters(Long idGps) {
        this.idGps = idGps;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public InvalidRegisters(){
        this.idGps = null;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long Id) {
        this.id = Id;
    }

    @Override
    public Long getIdGps() {
        return idGps;
    }

    @Override
    public void setIdGps(Long idGps) {
        this.idGps = idGps;
    }

    @Override
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InvalidRegisters invalidRegister = (InvalidRegisters) o;
        return id != null && Objects.equals(id, invalidRegister.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idGps = " + idGps + ", " +
                "timeStamp = " + timeStamp + ")";
    }
}