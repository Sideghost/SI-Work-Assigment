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
*/// TODO: 07/06/2022 por este aviso em todas as interfaces

package isel.sisinf.grp3.logic.repos;

import isel.sisinf.grp3.logic.repos.client.IClientRepository;
import isel.sisinf.grp3.logic.repos.client.IInstitutionalClientRepository;
import isel.sisinf.grp3.logic.repos.client.IPrivateClientRepository;
import isel.sisinf.grp3.logic.repos.registers.IInvalidRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IProcessedRegistersRepository;
import isel.sisinf.grp3.logic.repos.registers.IUnprocessedRegistersRepository;
import isel.sisinf.grp3.model.Alarms;
import isel.sisinf.grp3.model.Gps;
import isel.sisinf.grp3.model.GreenZone;
import isel.sisinf.grp3.model.Vehicle;
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
 * todo
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

    private EntityTransaction tx;
    private int txCount;

    /**
     * todo
     */
    public JPAContext() {
        this("t42dg3");
    }

    /**
     * todo
     *
     * @param persistentCtx
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
    }

    /**
     * todo
     *
     * @param jpql
     * @param params
     * @return
     */
    protected List helperQueryImpl(String jpql, Object... params) {
        Query q = em.createQuery(jpql);

        for (int i = 0; i < params.length; ++i)
            q.setParameter(i + 1, params[i]);

        return q.getResultList();
    }

    /**
     * todo
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
     * todo
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
     * todo
     */
    @Override
    public void flush() {
        em.flush();
    }

    /**
     * todo
     *
     * @return
     */
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

    /**
     * todo
     *
     * @return
     */
    @Override
    public IVehicleRepository getVehicles() {
        return vehicleRepository;
    }

    /**
     * todo
     *
     * @return
     */
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

    /**
     * todo
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }

    public void addVehicleToClient(String licensePlate, String driverName, String driversPhone, String clientNif, Long greenZoneId, Integer zoneRadius, BigDecimal zoneGpsLat, BigDecimal zoneGpsLon) {
        StoredProcedureQuery q = em.createNamedStoredProcedureQuery("addVehicleToClient");
    }

    /**
     * D
     */
    public void insertPrivateClient(PrivateClient privateClient) {
        beginTransaction();
        Query q = em.createNativeQuery("call insert_particular(?1,?2,?3, ?4, ?5, ?6)")
                .setParameter(1, privateClient.getNif())
                .setParameter(2, privateClient.getClient().getName())
                .setParameter(3, privateClient.getClient().getAddress())
                .setParameter(4, privateClient.getClient().getPhone())
                .setParameter(5, privateClient.getCC())
                .setParameter(6, privateClient.getClient().getReference());
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
     * E
     */
    public Integer numberOfAlarms(Integer year, String licensePlate) {
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
     * F
     */
    public void processRegisters() {
        beginTransaction();
        Query q = em.createNativeQuery("call process_registers()");
        q.executeUpdate();
        commit();
    }

    /**
     * H
     * ver se ponho o objecto em vez dos parametros.
     */
    public void addVehicleToClient(String licencePlate, String driverName, String driverPhone, String clientNif, Integer zoneRadius, Integer zoneGpsLat, Integer zoneGpsLon) {
        beginTransaction();
        Query q = em.createNativeQuery("call add_vehicle_to_client_or_not(?1,?2,?3, ?4, ?5, ?6, ?7)")
                .setParameter(1, licencePlate)
                .setParameter(2, driverName)
                .setParameter(3, driverPhone)
                .setParameter(4, clientNif)
                .setParameter(5, zoneRadius)
                .setParameter(6, zoneGpsLat)
                .setParameter(7, zoneGpsLon);
        q.executeUpdate();
        commit();
    }

    /**
     * I
     * como e uma vista pode ser feito atravez de selects
     * ver o tipo de retorno porque tenho de printar para a consola
     */
    public Collection<List<String>> allAlarms() {
        beginTransaction();
        //List q = em.createNamedQuery("Vehicle.alarms").getResultList();
        return null;
    }

    /**
     * J
     * duvida
     */
    public void addAlarm() {

    }

    /**
     * K
     */
    public void eliminateInvalidRegisters() {
        beginTransaction();
        Query q = em.createNativeQuery("call eliminate_invalid_registers()");
        q.executeUpdate();
        commit();
    }

    /**
     * L
     */
    public void deleteClient() {
        beginTransaction();
        Query q = em.createQuery("delete from Client");
        q.executeUpdate();
        commit();
    }

    /**
     * H by hand
     */
    public void addVehicleToClientOrNot(String licensePlate, String driverName, String driverPhone, String clientNif, Integer zoneRadius, BigDecimal zoneGpsLat, BigDecimal zoneGpsLon) {
        beginTransaction();
        Client client = clientRepository.findByKey(clientNif);
        if (client.getInstitutionalClient() != null) {
            Vehicle vehicle = new Vehicle(licensePlate, driverName, driverPhone, clientNif, null);
            em.merge(vehicle);
            addVehicleToGreenZone(zoneRadius, zoneGpsLat, zoneGpsLon, licensePlate);
        } else {
            if (client.getVehicles().size() < 3) {
                Vehicle v = new Vehicle(licensePlate, driverName, driverPhone, clientNif, null);
                em.merge(v);
                addVehicleToGreenZone(zoneRadius, zoneGpsLat, zoneGpsLon, licensePlate);
            }
            else throw new IllegalStateException("Client already has top number of Vehicles");
        }
        //...
        commit();
    }

    public void addVehicleToGreenZone(Integer zoneRadius, BigDecimal zoneGpsLat, BigDecimal zoneGpsLon, String licencePlate) {
        GreenZone newGreenZone = new GreenZone(zoneRadius, zoneGpsLat, zoneGpsLon, licencePlate);
        em.merge(newGreenZone);
    }

    /**
     * REPOSITORIES
     */
    protected class ClientRepository implements isel.sisinf.grp3.logic.repos.client.IClientRepository {

        @Override
        public Client findByKey(String key) {
            return em.createNamedQuery("Client.findByKey", Client.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<Client> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
        // apenas fazer o repositorio com o find e o findByKey

    }

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

    protected class InstitutionalClientRepository implements isel.sisinf.grp3.logic.repos.client.IInstitutionalClientRepository {

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

    protected class UnprocessedRegistersRepository implements isel.sisinf.grp3.logic.repos.registers.IUnprocessedRegistersRepository {

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

    protected class InvalidRegistersRepository implements isel.sisinf.grp3.logic.repos.registers.IInvalidRegistersRepository {

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

    protected class ProcessedRegistersRepository implements isel.sisinf.grp3.logic.repos.registers.IProcessedRegistersRepository {

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

    protected class VehicleRepository implements isel.sisinf.grp3.logic.repos.IVehicleRepository {

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

    protected class GreenZoneRepository implements isel.sisinf.grp3.logic.repos.IGreenZoneRepository {

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

    protected class AlarmsRepository implements isel.sisinf.grp3.logic.repos.IAlarmsRepository {

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

    protected class GpsRepository implements isel.sisinf.grp3.logic.repos.IGpsRepository {

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

}
