package isel.sisinf.grp3.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Interface All Allarms that has all the signatures of the setters and getters needed for
 * the "all_alarms" view present in the DB.
 */
public interface IAllAlarm {
    Long getIdAlarm();

    String getLicensePlate();

    String getDriversName();

    BigDecimal getLatitude();

    BigDecimal getLongitude();

    Timestamp getTimeStamp();
}
