package isel.sisinf.grp3.logic.repos;


import isel.sisinf.grp3.logic.repos.client.IClientRepository;
import isel.sisinf.grp3.logic.repos.client.IInstitutionalClientRepository;
import isel.sisinf.grp3.logic.repos.client.IPrivateClientRepository;
import isel.sisinf.grp3.logic.repos.registers.IInvalidRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IProcessedRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IUnprocessedRegistersRepository;

/**
 * Interface Context is an entity manager and its responsible for managing the transactions.
 * @see AutoCloseable
 */
public interface IContext extends AutoCloseable {

    void beginTransaction();

    void commit();

    void flush();

    IClientRepository getClients();

    IPrivateClientRepository getPrivateClients();

    IInstitutionalClientRepository getInstitutionalClients();

    IUnprocessedRegistersRepository getUnprocessedRegisters();

    IInvalidRegistersRepository getInvalidRegisters();

    IProcessedRegistersRepository getProcessedRegisters();

    IVehicleRepository getVehicles();

    IGreenZoneRepository getGreenZones();

    IAlarmsRepository getAlarms();

    IGpsRepository getGpss();

    IAllAlarmRepository getAllAlarmRepository();
}
