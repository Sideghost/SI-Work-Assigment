package isel.sisinf.grp3.model.client;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Mapping of table "particulares" present in DB.
 */
@Entity
@NamedQuery(name = "PrivateClient.findByKey",
        query = "SELECT pc FROM PrivateClient pc WHERE pc.nif =:key")
@Table(name = "particulares")
public class PrivateClient implements IPrivateClient {

    @Id
    @Column(name = "nif", nullable = false, length = 15)
    private String nif;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nif", nullable = false)
    private Client client;

    @Column(name = "cc", nullable = false, length = 15)
    private String cc;

    public PrivateClient() {
    }

    public PrivateClient(String nif, String cc) {
        this.client = getClient();
        this.nif = nif;
        this.cc = cc;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNif() {
        return nif;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public void setClientId(String clientId) {
        client.setClientId(clientId);
    }

    @Override
    public String getName() {
        return client.getName();
    }

    @Override
    public void setName(String name) {
        client.setName(name);
    }

    @Override
    public String getAddress() {
        return client.getAddress();
    }

    @Override
    public void setAddress(String address) {
        client.setAddress(address);
    }

    @Override
    public String getPhone() {
        return client.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        client.setPhone(phone);
    }

    @Override
    public PrivateClient getReference() {
        return client.getReference();
    }

    @Override
    public void setReference(PrivateClient reference) {
        client.setReference(reference);
    }

    @Override
    public Set<Client> getClients() {
        return null;
    }

    @Override
    public void setClients(Set<Client> clients) {

    }

    @Override
    public String getCC() {
        return this.cc;
    }

    @Override
    public void setCC(String cc) {
        this.cc = cc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PrivateClient privateClient = (PrivateClient) o;
        return nif != null && Objects.equals(nif, privateClient.nif);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                getClient().toString() +
                "cc = " + cc + ")";
    }
}