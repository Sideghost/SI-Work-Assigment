package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.registos.ProcessedRegisters;
import jakarta.persistence.*;

/**
 * todo
 */
@Entity
@Table(name = "Alarmes")
public class Alarms {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "registos_processados",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private ProcessedRegisters registerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "veiculo",
            joinColumns = @JoinColumn(name = "matricula"),
            inverseJoinColumns = @JoinColumn(name = "matricula"))
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ProcessedRegisters getRegisterId() {
        return registerId;
    }

    public void setRegisterId(ProcessedRegisters registerId) {
        this.registerId = registerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
