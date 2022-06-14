package isel.sisinf.grp3.model;

import java.math.BigDecimal;

/**
 * Interface Green Zone that has all the signatures of the setters and getters needed for
 * the "zona_verde" table present in the DB.
 */
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
