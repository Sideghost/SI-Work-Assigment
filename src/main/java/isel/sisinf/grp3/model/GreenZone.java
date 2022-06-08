package isel.sisinf.grp3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * todo
 */
@Entity
@Table(name = "zona_verde")
public class GreenZone implements IGreenZone {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula")
    private Vehicle licencePlate;

    @Column(name = "raio", nullable = false)
    private Integer radius;

    @Column(name = "latitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal longitude;

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

    public Vehicle getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(Vehicle licencePlate) {
        this.licencePlate = licencePlate;
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
