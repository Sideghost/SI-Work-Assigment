/*
MIT License

Copyright (c) 2022, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package isel.sisinf.grp3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Mapping of table "zona_verde" present in DB.
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
