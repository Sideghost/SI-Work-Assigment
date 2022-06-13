package isel.sisinf.grp3.model.client;

import jakarta.persistence.*;

/**
 * todo
 */
@Entity
@NamedQuery(name = "InstitutionalClient.findByKey",
        query = "SELECT ic FROM InstitutionalClient ic WHERE ic.nif =:key")
@Table(name = "institucionais")
public class InstitutionalClient implements IInstitucionalClient {

    @Id
    @Column(name = "nif", nullable = false, length = 15)
    private String nif;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nif", nullable = false)
    private Client client;

    @Column(name = "nome_contacto", nullable = false, length = 100)
    private String contactName;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public void setNif(String id) {
        this.nif = id;
    }

    @Override
    public String getClientId() {
        return null;
    }

    @Override
    public void setClientId(String clientId) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public PrivateClient getReference() {
        return client.getReference();
    }

    @Override
    public void setReference(PrivateClient reference) {
        this.client.setReference(reference);
    }

}