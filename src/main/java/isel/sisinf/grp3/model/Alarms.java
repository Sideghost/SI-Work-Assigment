package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.registors.ProcessedRegisters;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * todo
 */
@Entity
@Table(name = "alarmes")
@NamedQuery(name = "Alarms.findByKey",
        query = "SELECT a FROM Alarms a WHERE a.id =:key")
public class Alarms implements IAlarm {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca_temporal")
    private Timestamp timeStamp;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "alarms")
    private Gps gps;

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

    public Alarms() {
    }

    public Alarms(Timestamp timeStamp) {
        this.timeStamp = timeStamp;

    }

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
    public Long getAlarmId() {
        return this.getId();
    }

    @Override
    public void setAlarmId(Long id) {
        setId(id);
    }

    @Override
    public Long getGpsId() {
        return gps.getId();
    }

    @Override
    public void setGpsId(Long id) {
        gps.setId(id);
    }

    @Override
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public Timestamp getTimestamp() {
        return this.timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Alarms alarm = (Alarms) o;
        return id != null && Objects.equals(id, alarm.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idGps = " + gps.getId() + ", " +
                "timeStamp = " + timeStamp + ")";
    }
}