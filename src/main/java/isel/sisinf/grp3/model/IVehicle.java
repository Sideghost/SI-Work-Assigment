package isel.sisinf.grp3.model;

public interface IVehicle {
    public String getLicencePlate();
    public void setLicencePlate(String id);

    public String getDriversName();
    public void setDriversName(String driversName);

    public String getDriversPhone();
    public void setDriversPhone(String driversPhone);

    public Integer getNrAlarms();
    public void setNrAlarms(Integer nrAlarms);
}
