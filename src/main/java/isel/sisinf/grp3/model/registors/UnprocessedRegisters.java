package isel.sisinf.grp3.model.registors;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

//import java.time.LocalDate;

/**
 * todo
 */
@Entity
@NamedQuery(name = "UnprocessedRegisters.findByKey",
        query = "SELECT ur FROM UnprocessedRegisters ur WHERE ur.id =:key")
//@NamedStoredProcedureQuery(
//        name = "", //todo
//        procedureName = "process_registers",
//        parameters = {}
//)
@Table(name = "registos_nao_processados")
public class UnprocessedRegisters implements IUnprocessedRegisters {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gps", nullable = false, length = 50)
    private Long idGps;

    @Column(name = "marca_temporal")
    private Timestamp timeStamp;

    public UnprocessedRegisters(){

    }

    public UnprocessedRegisters(Long id, Timestamp timeStamp, Long idGps) {
        this.id = id;
        this.idGps = idGps;
        this.timeStamp = timeStamp;
    }

    public UnprocessedRegisters(Long id, Timestamp timestamp){
        this.id = id;
        this.idGps = null;
        this.timeStamp = timestamp;
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
        return idGps;
    }

    @Override
    public void setIdGps(Long idGps) {
        this.idGps = idGps;
    }

    @Override
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
