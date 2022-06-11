package isel.sisinf.grp3.logic.repos;


import isel.sisinf.grp3.logic.repos.client.IClientRepository;
import isel.sisinf.grp3.logic.repos.client.IInstitutionalClientRepository;
import isel.sisinf.grp3.logic.repos.client.IPrivateClientRepository;
import isel.sisinf.grp3.logic.repos.registers.IInvalidRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IProcessedRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IUnprocessedRegistersRepository;

/**
 * todo
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
}
