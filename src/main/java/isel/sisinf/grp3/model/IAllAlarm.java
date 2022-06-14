package isel.sisinf.grp3.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface IAllAlarm {
    Long getIdAlarm();

    String getLicensePlate();

    String getDriversName();

    BigDecimal getLatitude();

    BigDecimal getLongitude();

    Timestamp getTimeStamp();
}
