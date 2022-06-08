package isel.sisinf.grp3.model.registos;

import isel.sisinf.grp3.model.Vehicle;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * todo
 */
@Entity
@Table(name = "registos_processados")
public class Processed_Registers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "marca_temporal", nullable = false)
    private LocalDate timeStamp;

    @ManyToMany
    @JoinTable(name = "alarmes",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")) // inverseJoinColumns WTF is this?
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
