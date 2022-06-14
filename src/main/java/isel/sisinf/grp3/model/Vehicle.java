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

import isel.sisinf.grp3.model.client.Client;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Mapping of table "veiculo" present in DB.
 */
@Entity
@NamedQuery(name = "Vehicle.findByKey",
        query = "SELECT v FROM Vehicle v WHERE v.licensePlate =:key")// finds a Vehicle by its key
@Table(name = "veiculo")
public class Vehicle implements IVehicle {

    @Id
    @Column(name = "matricula", nullable = false, length = 6)
    private String licensePlate;

    @Column(name = "nome_condutor", nullable = false, length = 100)
    private String driversName;

    @Column(name = "telefone_condutor", nullable = false, length = 20)
    private String driversPhone;

    @ManyToOne(fetch = FetchType.LAZY) //foreign key
    @JoinColumn(name = "nif", nullable = false)
    private Client client;

    @Column(name = "n_alarmes")
    private Integer nrAlarms;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, String driversName, String driversPhone, String clientNif, Integer nrAlarms) {
        this.licensePlate = licensePlate;
        this.driversName = driversName;
        this.driversPhone = driversPhone;
        this.client.setClientId(clientNif); //ta errado
        this.nrAlarms = nrAlarms;
    }

    public Vehicle(String licensePlate, String driversName, String clientNif, Integer nrAlarms) {
        this.licensePlate = licensePlate;
        this.driversName = driversName;
        this.driversPhone = null;
        this.client.setClientId(clientNif);
        this.nrAlarms = nrAlarms;
    }

    @Override
    public Integer getNrAlarms() {
        return nrAlarms;
    }

    @Override
    public void setNrAlarms(Integer nrAlarms) {
        this.nrAlarms = nrAlarms;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String getDriversPhone() {
        return driversPhone;
    }

    @Override
    public void setDriversPhone(String driversPhone) {
        this.driversPhone = driversPhone;
    }

    @Override
    public String getDriversName() {
        return driversName;
    }

    @Override
    public void setDriversName(String driversName) {
        this.driversName = driversName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String id) {
        this.licensePlate = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vehicle vehicle = (Vehicle) o;
        return licensePlate != null && Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "licensePlate = " + licensePlate + ", " +
                "driversName = " + driversName + ", " +
                "driversPhone = " + driversPhone + ", " +
                "NIF = " + client.getClientId() + ", " +
                "nrAlarms" + nrAlarms + ")";
    }
}
