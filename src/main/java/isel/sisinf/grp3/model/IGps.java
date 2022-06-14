package isel.sisinf.grp3.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Interface Gps that has all the signatures of the setters and getters needed for
 * the "gps" table present in the DB.
 */
public interface IGps {
    Long getId();

    void setId(Long id);

    BigDecimal getLongitude();

    void setLongitude(BigDecimal longitude);

    BigDecimal getLatitude();

    void setLatitude(BigDecimal latitude);

    String getLicencePlate();

    void setLicensePlate(String licencePlate);

    String getStatus();

    void setStatus(String status);

    Timestamp getTimeStamp();

    void setTimeStamp(Timestamp timeStamp);
}
