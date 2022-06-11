package isel.sisinf.grp3.model.registors;

import isel.sisinf.grp3.model.Vehicle;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
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
    private LocalDate timeStamp;

    @ManyToMany // anotacao e esta ou OnetoOne
    @JoinTable(name = "alarmes",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")) // inverseJoinColumns WTF is this? tabela no meio para saber juntar info
    private Set<Vehicle> vehicles = new LinkedHashSet<>();

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getIdGps() { // TODO: 11/06/2022 como?
        return null;
    }

    @Override
    public void setIdGps(Long idGps) {

    }

}
