package isel.sisinf.grp3.model.registors;

import isel.sisinf.grp3.model.Gps;

import java.sql.Timestamp;

public interface IProcessedRegisters {
    Long getId();

    void setId(Long Id);

    Gps getGps();

    void setGps(Gps gps);

    Timestamp getTimeStamp();

    void setTimeStamp(Timestamp timeStamp);

}
