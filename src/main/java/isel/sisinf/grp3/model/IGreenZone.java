package isel.sisinf.grp3.model;

import java.math.BigDecimal;

public interface IGreenZone {
    public Long getId();
    public void setId(Long Id);

    public Integer getRadius();
    public void setRadius(Integer radius);

    public BigDecimal getLongitude();
    public void setLongitude(BigDecimal longitude);

    public BigDecimal getLatitude();
    public void setLatitude(BigDecimal latitude);

}
