package isel.sisinf.grp3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@NamedQuery(name = "AllAlarm.findByKey",
        query = "SELECT a FROM AllAlarm a WHERE a.idAlarm =:key") //finds an alarm in all_alarms view by its key.
@NamedQuery(name = "AllAlarm.findAll", query = "SELECT a FROM AllAlarm a") // finds all the alarms in the view.
@Table(name = "all_alarms")
public class AllAlarm implements IAllAlarm {
    @Id
    @Column(name = "id_alarm")
    private Long idAlarm;

    @Column(name = "matricula", length = 6)
    private String licensePlate;

    @Column(name = "nome_condutor", length = 30)
    private String driversName;

    @Column(name = "latitude", precision = 6, scale = 4)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 6, scale = 4)
    private BigDecimal longitude;

    @Column(name = "dia_hora")
    private Timestamp timeStamp;

    public Long getIdAlarm() {
        return idAlarm;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getDriversName() {
        return driversName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

}