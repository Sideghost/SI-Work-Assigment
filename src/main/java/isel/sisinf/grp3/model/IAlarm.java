package isel.sisinf.grp3.model;

import java.sql.Timestamp;

/**
 * Interface Alarm that has all the signatures of the setters and getters needed for
 * the "alarmes" table present in the DB.
 */
public interface IAlarm {

    Long getAlarmId();

    void setAlarmId(Long id);

    Long getGpsId();

    void setGpsId(Long id);

    void setTimeStamp(Timestamp timeStamp);

    Timestamp getTimestamp();

}
