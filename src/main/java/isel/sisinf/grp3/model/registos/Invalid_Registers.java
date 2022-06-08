package isel.sisinf.grp3.model.registos;

import isel.sisinf.grp3.model.Gps;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * todo
 */
@Entity
@Table(name = "Registos_invalidos")
public class Invalid_Registers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_gps", nullable = false)
    private Integer id_gps;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gps", nullable = false)
    private Gps Gps;

    @Column(name = "marca_temporal", nullable = false)
    private LocalDate timeStamp;

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gps getGps() {
        return this.Gps;
    }

    public void setGps(Gps gps) {
        Gps = gps;
    }

    public Integer getId_gps() {
        return id_gps;
    }

    public void setId_gps(Integer id_gps) {
        this.id_gps = id_gps;
    }
}