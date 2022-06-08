package isel.sisinf.grp3.model;

import java.math.BigDecimal;

public interface IGreenZone {
    Long getId();

    void setId(Long Id);

    Integer getRadius();

    void setRadius(Integer radius);

    BigDecimal getLongitude();

    void setLongitude(BigDecimal longitude);

    BigDecimal getLatitude();

    void setLatitude(BigDecimal latitude);

}
