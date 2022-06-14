package isel.sisinf.grp3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * todo
 */
@Entity
@NamedQuery(name = "GreenZone.findByKey",
        query = "SELECT gz FROM GreenZone gz WHERE gz.id =:key") //finds a green Zone by its key
@Table(name = "zona_verde")
public class GreenZone implements IGreenZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula")
    private Vehicle vehicle;

    @Column(name = "raio", nullable = false)
    private Integer radius;

    @Column(name = "latitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal longitude;

    public GreenZone() {
    }

    public GreenZone(Integer radius, BigDecimal latitude, BigDecimal longitude, String licencePlate) {
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicle.setLicensePlate(licencePlate);
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
    public Integer getRadius() {
        return radius;
    }

    @Override
    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle licencePlate) {
        this.vehicle = licencePlate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GreenZone greenZone = (GreenZone) o;
        return id != null && Objects.equals(id, greenZone.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "radius = " + radius + ", " +
                "longitude = " + longitude + ", " +
                "latitude = " + latitude + ", " +
                "licensePlate = " + vehicle.getLicensePlate() + ")";
    }
}
