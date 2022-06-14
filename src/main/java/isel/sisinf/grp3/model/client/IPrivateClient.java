package isel.sisinf.grp3.model.client;

import java.util.Set;

/**
 * Interface PrivateClient that has all the signatures of the setters and getters needed for
 * the "particulares" table present in the DB.
 * Extends from IClient.
 * @see IClient
 */
public interface IPrivateClient extends IClient {
    String getCC();

    void setCC(String cc);

    Set<Client> getClients();

    void setClients(Set<Client> clients);
}
