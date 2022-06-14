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

import isel.sisinf.grp3.model.registors.UnprocessedRegisters;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
//import java.time.LocalDate;

/**
 * Mapping of table "gps" present in DB.
 */
@Entity
@NamedQuery(name = "Gps.findByKey",
        query = "SELECT gps FROM Gps gps WHERE gps.id =:key") // finds a Gps by its key
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

    @Column(name = "estado", nullable = false, length = 15)
    private String status;

    @OneToOne
    @JoinColumn(name = "matricula", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private UnprocessedRegisters unprocessedRegisters;

    public Gps() {
    }

    public Gps(Long id, BigDecimal longitude, BigDecimal latitude, String licensePlate, String status, Timestamp timeStamp) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.vehicle.setLicensePlate(licensePlate);
        this.status = status;
        this.timeStamp = timeStamp;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Gps gps = (Gps) o;
        return id != null && Objects.equals(id, gps.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "longitude = " + longitude + ", " +
                "latitude = " + latitude + ", " +
                "status = " + status + ", " +
                "licensePlate" + vehicle.getLicensePlate() +
                "timeStamp = " + timeStamp + ")";
    }
}
