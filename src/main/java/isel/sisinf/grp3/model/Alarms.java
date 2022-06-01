package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.registos.Processed_Registers;
import jakarta.persistence.*;

@Entity
@Table(name = "Alarmes")
public class Alarms {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "registos_processados",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Processed_Registers registerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "veiculo",
            joinColumns = @JoinColumn(name = "matricula"),
            inverseJoinColumns = @JoinColumn(name = "matricula"))
    private Veiculo veiculo;

    public Veiculo getVehicle() {
        return veiculo;
    }

    public void setVehicle(Veiculo vehicle) {
        this.veiculo = vehicle;
    }

    public Processed_Registers getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Processed_Registers registerId) {
        this.registerId = registerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
