package isel.sisinf.grp3.model.client;

import java.util.Set;

/**
 *
 */
public interface IClient {
    public String getClientId();
    public void setClientId(String clientId);

    public String getName();
    public void setName(String name);

    public String getAddress();
    public void setAddress(String address);

    public String getPhone();
    public void setPhone(String phone);

    public String getCC();
    public void setCC(String CC);

    public String getReference(); // TODO: 07/06/2022 devera ser set de clientes ou set de referencias?
    public void setReference(String reference);
    public Set<Client> getClients();
    public void setClients(Set<Client> clients);
}
