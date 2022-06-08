package isel.sisinf.grp3.logic.repos;

import isel.sisinf.grp3.model.client.Client;
import isel.sisinf.grp3.model.client.PrivateClient;

import java.text.CollationElementIterator;
import java.util.Collection;

/**
 * todo
 */
public interface IClientRepository extends IRepository<Client, Collection<Client>, String/*tipo da chave*/> {
    Collection<Client> getAllClients();

    Integer getClientNrVehicles(PrivateClient privateClient);

    void removePrivateClient(String NIF);

    void updatePrivateClient(PrivateClient client, String newName, String newAddress, String newPhone, String newStatus);

    void insertPrivateClient(String NIF, String name, String address, String phone, String CC, String reference);
}

//implementar um repo para particular?
