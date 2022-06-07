package isel.sisinf.grp3.model.registos;

import java.time.LocalDate;

public interface IRegisters {
    public Long getId();
    public void setId(Long Id);

    public Long getIdGps();
    public void setIdGps(Long idGps);

    public LocalDate getTimeStamp();
    public void setTimeStamp(LocalDate timeStamp);
}
