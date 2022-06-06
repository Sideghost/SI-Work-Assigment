package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.client.Client;
import jakarta.persistence.*;

@Entity
public class Veiculo {

    @Id
    @Column(name = "matricula", nullable = false, length = 30)
    private String licencePlate;

    @Column(name = "nome_condutor", nullable = false, length = 100)
    private String driversName;

    @Column(name = "telefone_condutor", nullable = false, length = 20)
    private String driversPhone;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    private Gps status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgps")
    private Gps idGps;*/

    @ManyToOne(fetch = FetchType.LAZY) //foreign key
    @JoinColumn(name = "nif")
    private Client clientNIF;

    @Column(name = "n_alarmes")
    private Integer nrAlarms;

    public Integer getNrAlarms() {
        return nrAlarms;
    }

    public void setNrAlarms(Integer alarmes) {
        this.nrAlarms = alarmes;
    }

    public Client getClientNIF() {
        return clientNIF;
    }

    public void setClientNIF(Client clienteNif) {
        this.clientNIF = clienteNif;
    }

    /*public Gps getIdGps() {
        return idGps;
    }

    public void setIdGps(Gps idGps) {
        this.idGps = idGps;
    }

    public Gps getStatus() {
        return status;
    }

    public void setStatus(Gps estado) {
        this.status = estado;
    }*/

    public String getDriversPhone() {
        return driversPhone;
    }

    public void setDriversPhone(String telefoneCondutor) {
        this.driversPhone = telefoneCondutor;
    }

    public String getDriversName() {
        return driversName;
    }

    public void setDriversName(String nomeCondutor) {
        this.driversName = nomeCondutor;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String id) {
        this.licencePlate = id;
    }
}
