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

import isel.sisinf.grp3.model.GreenZone;
import isel.sisinf.grp3.model.Vehicle;
import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.InstitutionalClient;
import isel.sisinf.grp3.model.client.PrivateClient;
import isel.sisinf.grp3.model.registors.InvalidRegisters;
import isel.sisinf.grp3.model.registors.ProcessedRegisters;
import isel.sisinf.grp3.model.registors.UnprocessedRegisters;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

/**
 * todo
 */
public class JPAContext implements IContext {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    private final IClientRepository clientRepository;
    private final IInstitutionalClientRepository institutionalClientRepository;
    private final IPrivateClientRepository privateClientRepository;
    private final IInvalidRegistersRepository invalidRegistersRepository;
    private final IUnprocessedRegistersRepository unprocessedRegistersRepository;
    private final IProcessedRegistersRepository processedRegistersRepository;
    private final IVehicleRepository vehicleRepository;
    private final IGreenZoneRepository greenZoneRepository;
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
        emf = Persistence.createEntityManagerFactory(persistentCtx);
        em = emf.createEntityManager();
        clientRepository = new IClientRepository();
        institutionalClientRepository = new IInstitutionalClientRepository();
        privateClientRepository = new IPrivateClientRepository();
        vehicleRepository = new IVehicleRepository();
        invalidRegistersRepository = new IInvalidRegistersRepository();
        unprocessedRegistersRepository = new IUnprocessedRegistersRepository();
        processedRegistersRepository = new IProcessedRegistersRepository();
        greenZoneRepository = new IGreenZoneRepository();
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
    }

    /**
     * todo
     */
    @Override
    public void commit() {
        --txCount;
        if (txCount == 0 & tx != null) {
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

    /**
     * todo por aqui as queries que queremos para cada class
     */
    protected class IClientRepository implements isel.sisinf.grp3.logic.repos.client.IClientRepository {

        /**
         *
         * @param key
         * @return
         */
        @Override
        public Client findByKey(String key) {
            return em.createNamedQuery("Client.findByKey", Client.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         *
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<Client> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
        // apenas fazer o repositorio com o find e o findByKey

    }

    protected class IPrivateClientRepository implements isel.sisinf.grp3.logic.repos.client.IPrivateClientRepository {

        /**
         *
         * @param key
         * @return
         */
        @Override
        public PrivateClient findByKey(String key) {
            return em.createNamedQuery("PrivateClient.findByKey", PrivateClient.class).setParameter("key", key).getSingleResult();
        }

        /**
         *
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<PrivateClient> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    protected class IInstitutionalClientRepository implements isel.sisinf.grp3.logic.repos.client.IInstitutionalClientRepository {

        /**
         *
         * @param key
         * @return
         */
        @Override
        public InstitutionalClient findByKey(String key) {
            return em.createNamedQuery("InstitutionalClient.findByKey", InstitutionalClient.class).setParameter("key", key).getSingleResult();
        }

        /**
         *
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<InstitutionalClient> find(String jpql, Object... params) {
            return helperQueryImpl(jpql,params);
        }
    }

    /**
     * todo
     */
    protected class IUnprocessedRegistersRepository implements isel.sisinf.grp3.logic.repos.registers.IUnprocessedRegistersRepository {

        /**
         *
         * @param key
         * @return
         */
        @Override
        public UnprocessedRegisters findByKey(Long key) {
            return em.createNamedQuery("UnprocessedRegisters.findByKey", UnprocessedRegisters.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         *
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<UnprocessedRegisters> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     * todo
     */
    protected class IInvalidRegistersRepository implements isel.sisinf.grp3.logic.repos.registers.IInvalidRegistersRepository {

        /**
         * todo
         * @param key
         * @return
         */
        @Override
        public InvalidRegisters findByKey(Long key) {
            return em.createNamedQuery("InvalidRegisters.findByKey", InvalidRegisters.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<InvalidRegisters> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     * todo
     */
    protected class IProcessedRegistersRepository implements isel.sisinf.grp3.logic.repos.registers.IProcessedRegistersRepository {

        /**
         * todo
         * @param key
         * @return
         */
        @Override
        public ProcessedRegisters findByKey(Long key) {
            return em.createNamedQuery("ProcessedRegisters.findByKey", ProcessedRegisters.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<ProcessedRegisters> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     * todo por aqui as queries que queremos para cada class
     */
    protected class IVehicleRepository implements isel.sisinf.grp3.logic.repos.IVehicleRepository {

        /**
         * todo por aqui as queries que queremos para cada class
         *
         * @param key
         * @return
         */
        @Override
        public Vehicle findByKey(Long key) {
            return em.createNamedQuery("Vehicle.findByKey", Vehicle.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         *
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<Vehicle> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     * todo por aqui as queries que queremos para cada class
     */
    protected class IGreenZoneRepository implements isel.sisinf.grp3.logic.repos.IGreenZoneRepository {

        /**
         * todo por aqui as queries que queremos para cada class
         *
         * @param key
         * @return
         */
        @Override
        public GreenZone findByKey(Long key) {
            return em.createNamedQuery("GreenZone.findByKey", GreenZone.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         *
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<GreenZone> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }




}
