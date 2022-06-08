package isel.sisinf.grp3.model.registos;

import isel.sisinf.grp3.model.Gps;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * todo
 */
@Entity
@Table(name = "registos_nao_processados")
public class Unprocessed_Registers implements IRegisters {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gps", nullable = false, length = 50)
    private Long id_gps;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gps", nullable = false)
    private Gps Gps;


    @Column(name = "marca_temporal")
    private LocalDate timeStamp;

    @Override
    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Gps getGps() {
        return Gps;
    }

    public void setGps(Gps gps) {
        this.Gps = gps;
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
    public Long getIdGps() {
        return id;
    }

    @Override
    public void setIdGps(Long id) {
        this.id = id;
    }
}
