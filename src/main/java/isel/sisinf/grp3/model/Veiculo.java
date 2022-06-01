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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado")
    private Gps estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gps")
    private Gps idGps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_nif")
    private Client clienteNif;

    @Column(name = "alarmes")
    private Integer alarmes;

    public Integer getAlarmes() {
        return alarmes;
    }

    public void setAlarmes(Integer alarmes) {
        this.alarmes = alarmes;
    }

    public Client getClienteNif() {
        return clienteNif;
    }

    public void setClienteNif(Client clienteNif) {
        this.clienteNif = clienteNif;
    }

    public Gps getIdGps() {
        return idGps;
    }

    public void setIdGps(Gps idGps) {
        this.idGps = idGps;
    }

    public Gps getEstado() {
        return estado;
    }

    public void setEstado(Gps estado) {
        this.estado = estado;
    }

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
