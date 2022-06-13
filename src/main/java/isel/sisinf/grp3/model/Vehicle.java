package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.client.Client;
import jakarta.persistence.*;

/**
 * todo
 */
@Entity
@Table(name = "veiculo")
public class Vehicle implements IVehicle {

    @Id
    @Column(name = "matricula", nullable = false, length = 30)
    private String licencePlate;

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
        this.client.setClientId(clientNif);
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
}
