package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.client.Client;
import jakarta.persistence.*;

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
    @JoinColumn(name = "nif")
    private Client clientNIF;

    @Column(name = "n_alarmes")
    private Integer nrAlarms;

    @Override
    public Integer getNrAlarms() {
        return nrAlarms;
    }

    @Override
    public void setNrAlarms(Integer nrAlarms) {
        this.nrAlarms = nrAlarms;
    }

    public Client getClientNIF() {
        return clientNIF;
    }

    public void setClientNIF(Client clientNIF) {
        this.clientNIF = clientNIF;
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

    @Override
    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public void setLicencePlate(String id) {
        this.licencePlate = id;
    }
}
