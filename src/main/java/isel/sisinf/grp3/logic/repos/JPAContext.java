/*
MIT License

Copyright (c) 2022, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package isel.sisinf.grp3.logic.repos;

import com.sun.istack.Nullable;
import isel.sisinf.grp3.logic.repos.client.IClientRepository;
import isel.sisinf.grp3.logic.repos.client.IInstitutionalClientRepository;
import isel.sisinf.grp3.logic.repos.client.IPrivateClientRepository;
import isel.sisinf.grp3.logic.repos.registers.IInvalidRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IProcessedRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IUnprocessedRegistersRepository;
import isel.sisinf.grp3.model.*;
import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.InstitutionalClient;
import isel.sisinf.grp3.model.client.PrivateClient;
import isel.sisinf.grp3.model.registors.InvalidRegisters;
import isel.sisinf.grp3.model.registors.ProcessedRegisters;
import isel.sisinf.grp3.model.registors.UnprocessedRegisters;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * JPAContext class that inherits IContext interface
 */
public class JPAContext implements IContext {

    public final EntityManager em;
    private final EntityManagerFactory emf;
    private final IClientRepository clientRepository;
    private final IInstitutionalClientRepository institutionalClientRepository;
    private final IPrivateClientRepository privateClientRepository;

    private final IInvalidRegistersRepository invalidRegistersRepository;
    private final IUnprocessedRegistersRepository unprocessedRegistersRepository;
    private final IProcessedRegistersRepository processedRegistersRepository;

    private final IVehicleRepository vehicleRepository;
    private final IGreenZoneRepository greenZoneRepository;
    private final IAlarmsRepository alarmsRepository;
    private final IGpsRepository gpsRepository;

    private final IAllAlarmRepository allAlarmRepository;

    private EntityTransaction tx;
    private int txCount;

    /**
     * JPA constructor.
     */
    public JPAContext() {
        this("t42dg3");
    }

    /**
     * JPA constructor.
     * @param persistentCtx name of prescience unit.
     */
    public JPAContext(String persistentCtx) {
        super();
        this.emf = Persistence.createEntityManagerFactory(persistentCtx);
        this.em = emf.createEntityManager();
        this.clientRepository = new ClientRepository();
        this.institutionalClientRepository = new InstitutionalClientRepository();
        this.privateClientRepository = new PrivateClientRepository();
        this.vehicleRepository = new VehicleRepository();
        this.invalidRegistersRepository = new InvalidRegistersRepository();
        this.unprocessedRegistersRepository = new UnprocessedRegistersRepository();
        this.processedRegistersRepository = new ProcessedRegistersRepository();
        this.greenZoneRepository = new GreenZoneRepository();
        this.alarmsRepository = new AlarmsRepository();
        this.gpsRepository = new GpsRepository();
        this.allAlarmRepository = new AllAlarmsRepository();
    }

    /**
     * Util function.
     * @param jpql query.
     * @param params arguments to set in query.
     * @return Result of Query.
     */
    protected List helperQueryImpl(String jpql, Object... params) {
        Query q = em.createQuery(jpql);

        for (int i = 0; i < params.length; ++i)
            q.setParameter(i + 1, params[i]);

        return q.getResultList();
    }

    /**
     * Transaction beginner
     */
    @Override
    public void beginTransaction() {
        if (tx == null) {
            tx = em.getTransaction();
            tx.begin();
            txCount = 0;
        }
        ++txCount;
    }

    /**
     * Commit operation
     */
    @Override
    public void commit() {
        --txCount;
        if (txCount == 0 && tx != null) {
            tx.commit();
            tx = null;
        }
    }

    /**
     * Flush operation.
     */
    @Override
    public void flush() {
        em.flush();
    }

    /**
     * Close operation.
     */
    @Override
    public void close() throws Exception {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }

    @Override
    public IClientRepository getClients() {
        return clientRepository;
    }

    @Override
    public IPrivateClientRepository getPrivateClients() {
        return privateClientRepository;
    }

    @Override
    public IInstitutionalClientRepository getInstitutionalClients() {
        return institutionalClientRepository;
    }

    @Override
    public IUnprocessedRegistersRepository getUnprocessedRegisters() {
        return unprocessedRegistersRepository;
    }

    @Override
    public IInvalidRegistersRepository getInvalidRegisters() {
        return invalidRegistersRepository;
    }

    @Override
    public IProcessedRegistersRepository getProcessedRegisters() {
        return processedRegistersRepository;
    }

    @Override
    public IVehicleRepository getVehicles() {
        return vehicleRepository;
    }

    @Override
    public IGreenZoneRepository getGreenZones() {
        return greenZoneRepository;
    }

    @Override
    public IAlarmsRepository getAlarms() {
        return alarmsRepository;
    }

    @Override
    public IGpsRepository getGpss() {
        return gpsRepository;
    }

    public IAllAlarmRepository getAllAlarmRepository() {
        return allAlarmRepository;
    }


    /**
     * Implementation of exercise D.
     */
    public void insertPrivateClient(PrivateClient privateClient, String reference) {
        beginTransaction();
        Query q = em.createNativeQuery("call insert_particular(?1,?2,?3, ?4, ?5, ?6)")
                .setParameter(1, privateClient.getNif())
                .setParameter(2, privateClient.getClient().getName())
                .setParameter(3, privateClient.getClient().getAddress())
                .setParameter(4, privateClient.getClient().getPhone())
                .setParameter(5, privateClient.getCC())
                .setParameter(6, reference);
        q.executeUpdate();
        commit();
    }

    public void updatePrivateClient(String nif, String newName, String newAddress, String newPhone, Boolean newStatus) {
        beginTransaction();
        Query q = em.createNativeQuery("call update_particular(?1,?2,?3,?4,?5)")
                .setParameter(1, nif)
                .setParameter(2, newName)
                .setParameter(3, newAddress)
                .setParameter(4, newPhone)
                .setParameter(5, newStatus);

        q.executeUpdate();
        commit();
    }

    public void removePrivateClient(String NIF) {
        beginTransaction();
        Query q = em.createNativeQuery("call remove_particular(?1)")
                .setParameter(1, NIF);

        q.executeUpdate();
        commit();
    }

    /**
     * Implementation of exercise E.
     */
    public Integer numberOfAlarms(Integer year, @Nullable String licensePlate) {
        beginTransaction();
        StoredProcedureQuery s = em.createStoredProcedureQuery("number_of_alarms")
                .setParameter(1, year)
                .setParameter(2, licensePlate);
        s.execute();
        int nrAlarms = (int) s.getOutputParameterValue(3);
        commit();
        return nrAlarms;
    }

    public Integer numberOfAlarms(Integer year) {
        return numberOfAlarms(year, null);
    }

    /**
     * Implementation of exercise F.
     */
    public void processRegisters() {
        beginTransaction();
        Query q = em.createNativeQuery("call process_registers()");
        q.executeUpdate();
        commit();
    }

    /**
     * Implementation of exercise G.
     */
    public void insertInView() {
        System.out.println("NOT YET IMPLEMENTED");
    }


    /**
     * Implementation of exercise H.
     */
    public void addVehicleToClient(Vehicle vehicle, GreenZone greenZone) {
        beginTransaction();
        Query q = em.createNativeQuery("call add_vehicle_to_client_or_not(?1,?2,?3, ?4, ?5, ?6, ?7)")
                .setParameter(1, vehicle.getLicensePlate())
                .setParameter(2, vehicle.getDriversName())
                .setParameter(3, vehicle.getDriversPhone())
                .setParameter(4, vehicle.getClient().getClientId())
                .setParameter(5, greenZone.getRadius())
                .setParameter(6, greenZone.getLatitude())
                .setParameter(7, greenZone.getLongitude());
        q.executeUpdate();
        commit();
    }

    /**
     * Implementation of exercise I.
     */
    public Collection<AllAlarm> allAlarms() {
        beginTransaction();
        System.out.println("NOT IMPLEMENTED");
        return allAlarmRepository.findAll();
    }

    /**
     * Implementation of exercise J.
     */
    public void addAlarm() {
        System.out.println("not Implemented");
    }

    /**
     * Implementation of exercise K.
     */
    public void eliminateInvalidRegisters() {
        beginTransaction();
        Query q = em.createNativeQuery("call eliminate_invalid_registers()");
        q.executeUpdate();
        commit();
    }

    /**
     * Implementation of exercise L.
     */
    public void deleteClient() {
        beginTransaction();
        Query q = em.createQuery("delete from Client");
        q.executeUpdate();
        commit();
    }

    /**
     * Implementation of exercise H, by hand.
     */
    public void addVehicleToClientOrNot(String licensePlate, String clientNif, Integer zoneRadius, BigDecimal zoneGpsLat, BigDecimal zoneGpsLon, Vehicle vehicle) {
        beginTransaction();
        Client client = clientRepository.findByKey(clientNif);
        if (client.getInstitutionalClient() != null) {
            em.merge(vehicle);
            addVehicleToGreenZone(zoneRadius, zoneGpsLat, zoneGpsLon, licensePlate);
        } else {
            if (client.getVehicles().size() < 3) {
                em.merge(vehicle);
                addVehicleToGreenZone(zoneRadius, zoneGpsLat, zoneGpsLon, licensePlate);
            } else throw new IllegalStateException("Client already has top number of Vehicles");
        }
        commit();
    }

    public void addVehicleToGreenZone(Integer zoneRadius, BigDecimal zoneGpsLat, BigDecimal zoneGpsLon, String licencePlate) {
        GreenZone newGreenZone = new GreenZone(zoneRadius, zoneGpsLat, zoneGpsLon, licencePlate);
        em.merge(newGreenZone);
    }

    /** REPOSITORIES */

    /**
     * Class that represents an implementation of {@link IClientRepository} and implements its methods.
     */
    protected class ClientRepository implements IClientRepository {

        @Override
        public Client findByKey(String key) {
            return em.createNamedQuery("Client.findByKey", Client.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<Client> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }

        @Override
        public Integer getClientVehicles(String key) {
            return em.createNamedQuery("Client.getVehicles", Client.class).setParameter("key", key).getMaxResults();
        }
    }
    /**
     * Class that represents an implementation of {@link IPrivateClientRepository} and implements its methods.
     */
    protected class PrivateClientRepository implements IPrivateClientRepository {

        @Override
        public PrivateClient findByKey(String key) {
            return em.createNamedQuery("PrivateClient.findByKey", PrivateClient.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<PrivateClient> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }

    }
    /**
     * Class that represents an implementation of {@link IInstitutionalClientRepository} and implements its methods.
     */
    protected class InstitutionalClientRepository implements IInstitutionalClientRepository {

        @Override
        public InstitutionalClient findByKey(String key) {
            return em.createNamedQuery("InstitutionalClient.findByKey", InstitutionalClient.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<InstitutionalClient> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }
    /**
     * Class that represents an implementation of {@link IUnprocessedRegistersRepository} and implements its methods.
     */
    protected class UnprocessedRegistersRepository implements IUnprocessedRegistersRepository {

        @Override
        public UnprocessedRegisters findByKey(Long key) {
            return em.createNamedQuery("UnprocessedRegisters.findByKey", UnprocessedRegisters.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<UnprocessedRegisters> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }
    /**
     * Class that represents an implementation of {@link IInvalidRegistersRepository} and implements its methods.
     */
    protected class InvalidRegistersRepository implements IInvalidRegistersRepository {

        @Override
        public InvalidRegisters findByKey(Long key) {
            return em.createNamedQuery("InvalidRegisters.findByKey", InvalidRegisters.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<InvalidRegisters> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }
    /**
     * Class that represents an implementation of {@link IProcessedRegistersRepository} and implements its methods.
     */
    protected class ProcessedRegistersRepository implements IProcessedRegistersRepository {

        @Override
        public ProcessedRegisters findByKey(Long key) {
            return em.createNamedQuery("ProcessedRegisters.findByKey", ProcessedRegisters.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<ProcessedRegisters> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }
    /**
     * Class that represents an implementation of {@link IVehicleRepository} and implements its methods.
     */
    protected class VehicleRepository implements IVehicleRepository {

        @Override
        public Vehicle findByKey(Long key) {
            return em.createNamedQuery("Vehicle.findByKey", Vehicle.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<Vehicle> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }
    /**
     * Class that represents an implementation of {@link IGreenZoneRepository} and implements its methods.
     */
    protected class GreenZoneRepository implements IGreenZoneRepository {

        @Override
        public GreenZone findByKey(Long key) {
            return em.createNamedQuery("GreenZone.findByKey", GreenZone.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<GreenZone> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }
    /**
     * Class that represents an implementation of {@link IAlarmsRepository} and implements its methods.
     */
    protected class AlarmsRepository implements IAlarmsRepository {

        @Override
        public Alarms findByKey(Long key) {
            return em.createNamedQuery("Alarms.findByKey", Alarms.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<Alarms> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     * Class that represents an implementation of {@link IGpsRepository} and implements its methods.
     */
    protected class GpsRepository implements IGpsRepository {

        @Override
        public Gps findByKey(Long key) {
            return em.createNamedQuery("Gps.findByKey", Gps.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<Gps> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     * Class that represents an implementation of {@link IAlarmsRepository} and implements its methods.
     */
    protected class AllAlarmsRepository implements IAllAlarmRepository {
        @Override
        public AllAlarm findByKey(Long key) {
            return em.createNamedQuery("AllAlarm.findByKey", AllAlarm.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<AllAlarm> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }

        @Override
        public Collection<AllAlarm> findAll() {
            return em.createNamedQuery("AllAlarm.findAll", AllAlarm.class).getResultList();
        }
    }
}
