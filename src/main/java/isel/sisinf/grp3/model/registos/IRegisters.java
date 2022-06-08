package isel.sisinf.grp3.model.registos;

import java.time.LocalDate;

/**
 * todo
 */
public interface IRegisters {
    Long getId();

    void setId(Long Id);

    Long getIdGps();

    void setIdGps(Long idGps);

    LocalDate getTimeStamp();

    void setTimeStamp(LocalDate timeStamp);
}
