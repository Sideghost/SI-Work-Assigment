package isel.sisinf.grp3.model.registors;

import java.time.LocalDate;

/**
 * Interface of a generic register.
 */
public interface IRegisters {
    Long getId();

    void setId(Long Id);

    Long getIdGps();

    void setIdGps(Long idGps);

    LocalDate getTimeStamp();

    void setTimeStamp(LocalDate timeStamp);
}
