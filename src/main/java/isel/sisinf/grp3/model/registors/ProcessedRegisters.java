package isel.sisinf.grp3.model.registors;

import isel.sisinf.grp3.model.Gps;
import isel.sisinf.grp3.model.Vehicle;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * todo
 */
@Entity
@NamedQuery(name = "ProcessedRegisters.findByKey",
        query = "SELECT pr FROM ProcessedRegisters pr WHERE pr.id =:key")
@Table(name = "registos_processados")
public class ProcessedRegisters implements IProcessedRegisters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "marca_temporal", nullable = false)
    private Timestamp timeStamp;

    @ManyToMany // anotacao e esta ou OnetoOne
    @JoinTable(name = "alarmes",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    // inverseJoinColumns WTF is this? tabela no meio para saber juntar info
    private Set<Vehicle> vehicles = new LinkedHashSet<>();


    public ProcessedRegisters() {
    }

    public ProcessedRegisters(Long id, Timestamp timeStamp) {
        this.id = id;
        this.id = getGps().getId();
        this.timeStamp = timeStamp;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Gps getGps() {
        return null;
    }

    @Override
    public void setGps(Gps gps) {

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
        ProcessedRegisters processedRegister = (ProcessedRegisters) o;
        return id != null && Objects.equals(id, processedRegister.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idGps = " + getGps().getId() + ", " +
                "timeStamp = " + timeStamp + ")";
    }
}
