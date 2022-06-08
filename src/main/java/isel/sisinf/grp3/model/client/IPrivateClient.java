package isel.sisinf.grp3.model.client;

import java.util.Set;

public interface IPrivateClient extends IClient{
    String getCC();

    void setCC(String cc);

    Set<Client> getClients();

    void setClients(Set<Client> clients);
}
