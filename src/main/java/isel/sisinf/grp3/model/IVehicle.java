package isel.sisinf.grp3.model;

/**
 * todo
 */
public interface IVehicle {
    String getLicencePlate();

    void setLicencePlate(String id);

    String getDriversName();

    void setDriversName(String driversName);

    String getDriversPhone();

    void setDriversPhone(String driversPhone);

    Integer getNrAlarms();

    void setNrAlarms(Integer nrAlarms);
}
