package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.registors.ProcessedRegisters;
import jakarta.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 * todo
 */
@Entity
@NamedQuery(name = "Alarms.findByKey",
        query = "SELECT a FROM Alarms a WHERE a.id =:key")
@Table(name = "alarmes")
public class Alarms implements IAlarm{

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setAlarmId(Long id) {
        setId(id);
    }

    @Override
    public Long getAlarmId() {
        return this.getId();
    }

    @Override
    public void setGpsId(Long id) {

    }

    @Override
    public Long getGpsId() {
        return null;
    }

    @Override
    public void setTimeStamp(DateTime timeStamp) {

    }

    @Override
    public DateTime getTimestamp() {
        return null;
    }
}
