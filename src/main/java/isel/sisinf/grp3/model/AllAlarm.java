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
import java.sql.Timestamp;

/**
 * Mapping of view "all_alarms" present in DB.
 */
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