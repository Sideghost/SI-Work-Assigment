package isel.sisinf.grp3.model.registors;

import isel.sisinf.grp3.model.Gps;

import java.sql.Timestamp;

/**
 * Interface Processed Registers that has all the signatures of the setters and getters needed for
 * the "registos_processados" table present in the DB.
 * Extends from IRegisters
 *
 * @see IRegisters
 */
public interface IProcessedRegisters {
    Long getId();

    void setId(Long Id);

    Gps getGps();

    void setGps(Gps gps);

    Timestamp getTimeStamp();

    void setTimeStamp(Timestamp timeStamp);

}
