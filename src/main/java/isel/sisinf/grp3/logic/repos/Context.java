package isel.sisinf.grp3.logic.repos;


public interface Context extends AutoCloseable {

    void beginTransaction();

    void commit();

    void flush();

    ClientRepository getClients();

    RegistersRepository getRegisters();

    VehicleRepository getVehicles();

    ZonaVerdeRepository getZonasVerdes();
}
