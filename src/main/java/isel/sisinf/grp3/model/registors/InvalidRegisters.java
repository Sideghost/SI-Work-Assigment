package isel.sisinf.grp3.model.registors;

import isel.sisinf.grp3.model.Gps;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * todo
 */
@Entity
@NamedQuery(name = "InvalidRegisters.findByKey",
        query = "SELECT ir FROM InvalidRegisters ir WHERE ir.id =:key")
@NamedStoredProcedureQuery(
        name = "eliminateInvalidRegisters" ,
        procedureName = "eliminate_invalid_registers",
        parameters = {}
)
@Table(name = "Registos_invalidos")
public class InvalidRegisters implements IInvalidRegisters{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_gps", nullable = false)
    private Long id_gps;

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

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long Id) {this.id = Id;}

    @Override
    public Long getIdGps() {
        return id_gps;
    }

    @Override
    public void setIdGps(Long idGps) {
        this.id_gps = idGps;
    }

    public Gps getGps() {
        return this.Gps;
    }

    public void setGps(Gps gps) {
        Gps = gps;
    }

}