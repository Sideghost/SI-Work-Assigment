package isel.sisinf.grp3.model.registos;

import isel.sisinf.grp3.model.Gps;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "registos_nao_processados")
public class Unprocessed_Registers {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_gps", nullable = false, length = 50)
    private Integer id_gps;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gps", nullable = false)
    private Gps Gps;


    @Column(name = "marca_temporal")
    private LocalDate timeStamp;

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Gps getGps() {
        return Gps;
    }

    public void setGps(Gps equipamentoGps) {
        this.Gps = equipamentoGps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_gps() {
        return id;
    }

    public void setId_gps(Integer id) {
        this.id = id;
    }
}
