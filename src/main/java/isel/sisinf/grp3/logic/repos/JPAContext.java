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
import isel.sisinf.grp3.model.registos.Unprocessed_Registers;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

/**
 * todo
 */
public class JPAContext implements Context{

    private EntityManagerFactory emf;
    private EntityManager em;

    private EntityTransaction tx;
    private int txCount;

    private ClientRepository clientRepository;
    private RegistersRepository registersRepository;
    private VehicleRepository vehicleRepository;
    private GreenZoneRepository greenZoneRepository;

    /**
     * todo
     * @param jpql
     * @param params
     * @return
     */
    protected List helperQueryImpl(String jpql, Object... params)
    {
        Query q = em.createQuery(jpql);

        for(int i = 0; i < params.length; ++i)
            q.setParameter(i+1, params[i]);

        return q.getResultList();
    }

    /**
     * todo por aqui as queries que queremos para cada class
     */
    protected class ClientRepository implements isel.sisinf.grp3.logic.repos.ClientRepository {

        /**
         * todo por aqui as queries que queremos para cada class
         * @param key
         * @return
         */
        @Override
        public Client findByKey(Long key) {
            return em.createNamedQuery("Client.findByKey", Client.class).setParameter("key", key).getSingleResult();
        }

        /**
         * todo
         * @param jpql
         * @param params
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public Collection<Client> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }

    /**
     *
     * todo por aqui as queries que queremos para cada class
     */
    protected class RegistersRepository implements isel.sisinf.grp3.logic.repos.RegistersRepository {

        /**
         *
         * todo por aqui as queries que queremos para cada class
         * @param key
         * @return
         */
        @Override
        public Unprocessed_Registers findByKey(Long key) {
            return em.createNamedQuery("Unprocessed_Registers.findByKey", Unprocessed_Registers.class).setParameter("key", key).getSingleResult();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Collection<Unprocessed_Registers> find(String jpql, Object... params) {
            return helperQueryImpl(jpql, params);
        }
    }


    /**
     * todo por aqui as queries que queremos para cada class
     */
    protected class VehicleRepository implements isel.sisinf.grp3.logic.repos.VehicleRepository {

        /**
         *
         * todo por aqui as queries que queremos para cada class
         * @param key
         * @return
         */
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
     * todo por aqui as queries que queremos para cada class
     */
    protected class GreenZoneRepository implements isel.sisinf.grp3.logic.repos.GreenZoneRepository{

        /**
         *
         * todo por aqui as queries que queremos para cada class
         * @param key
         * @return
         */
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
     * todo
     */
    @Override
    public void beginTransaction() {
         if (tx == null){
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
        if (txCount == 0 & tx != null){
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

    public JPAContext() {
        this("t42dg3");
    }

    public JPAContext(String persistentCtx) {
        super(); // TODO: 07/06/2022 what this means ?
        emf = Persistence.createEntityManagerFactory(persistentCtx);
        em = emf.createEntityManager();
        clientRepository = new ClientRepository();
        vehicleRepository = new VehicleRepository();
        registersRepository = new RegistersRepository();
        greenZoneRepository = new GreenZoneRepository();
    }

    /**
     * todo
     * @return
     */
    @Override
    public ClientRepository getClients() {
        return clientRepository;
    }

    /**
     * todo
     * @return
     */
    @Override
    public RegistersRepository getRegisters() {
        return registersRepository;
    }

    /**
     * todo
     * @return
     */
    @Override
    public VehicleRepository getVehicles() {
        return vehicleRepository;
    }

    /**
     * todo
     * @return
     */
    @Override
    public GreenZoneRepository getGreenZones() {
        return greenZoneRepository;
    }

    /**
     * todo
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        if (tx != null)
            tx.rollback();
        em.close();
        emf.close();
    }
}
