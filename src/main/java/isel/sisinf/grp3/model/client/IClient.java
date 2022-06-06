package isel.sisinf.grp3.model.client;

import java.util.Set;

public interface IClient {
    public String getClientId();
    public void setClientId(String clientId);

    public String getName();
    public void setName(String name);

    public Set<Client> getClients();
    public void setClients(Set<Client> clients);
}
