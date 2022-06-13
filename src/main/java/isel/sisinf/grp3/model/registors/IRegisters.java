package isel.sisinf.grp3.model.registors;

//import java.time.LocalDate;

import java.sql.Timestamp;

/**
 * Interface of a generic register.
 */
public interface IRegisters {
    Long getId();

    void setId(Long Id);

    Long getIdGps();

    void setIdGps(Long idGps);

    Timestamp getTimeStamp();

    void setTimeStamp(Timestamp timeStamp);
}
