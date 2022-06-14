package isel.sisinf.grp3.model;

/**
 * Interface Vehicle that has all the signatures of the setters and getters needed for
 * the "veiculo" table present in the DB.
 */
public interface IVehicle {
    String getLicensePlate();

    void setLicensePlate(String id);

    String getDriversName();

    void setDriversName(String driversName);

    String getDriversPhone();

    void setDriversPhone(String driversPhone);

    Integer getNrAlarms();

    void setNrAlarms(Integer nrAlarms);
}
