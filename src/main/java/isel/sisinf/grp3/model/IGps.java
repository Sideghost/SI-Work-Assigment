package isel.sisinf.grp3.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
