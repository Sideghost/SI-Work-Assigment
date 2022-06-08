package isel.sisinf.grp3.logic.repos;


/**
 * todo
 */
public interface IContext extends AutoCloseable {

    void beginTransaction();

    void commit();

    void flush();

    IClientRepository getClients();

    IRegistersRepository getRegisters();

    IVehicleRepository getVehicles();

    IGreenZoneRepository getGreenZones();
}
