package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.registors.UnprocessedRegisters;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
//import java.time.LocalDate;

/**
 * todo
 */
@Entity
@NamedQuery(name = "Gps.findByKey",
        query = "SELECT gps FROM Gps gps WHERE gps.id =:key")
@Table(name = "gps")
public class Gps implements IGps {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Alarms alarms;

    @Column(name = "marca_temporal", nullable = false)
    private Timestamp timeStamp;

    @Column(name = "latitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private UnprocessedRegisters unprocessedRegisters;

    public UnprocessedRegisters getUnprocessedRegisters() {
        return unprocessedRegisters;
    }

    public void setUnprocessedRegisters(UnprocessedRegisters unprocessedRegisters) {
        this.unprocessedRegisters = unprocessedRegisters;
    }

    @Override
    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public BigDecimal getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public String getLicencePlate() {
        return vehicle.getLicensePlate();
    }

    @Override
    public void setLicensePlate(String licensePlate) {
        this.vehicle.setLicensePlate(licensePlate);
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
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
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
