package isel.sisinf.grp3.model.client;

import isel.sisinf.grp3.model.Veiculo;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Mapping of table "Cliente" present in DB.
 */
@Entity
@Table(name = "cliente")
public class Client {

    @Id
    @Column(name = "nif", nullable = false, length = 15)
    private String nif;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @Column(name = "morada", nullable = false, length = 120)
    private String address;

    @Column(name = "telefone", nullable = false, length = 20)
    private String phone;

    @Column(name = "ativo", length = 1)
    private Boolean status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client")
    private PrivateClient privateClient;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client")
    private InstitutionalClient institutionalClient;

    @OneToMany(mappedBy = "clientNIF")
    private Set<Veiculo> vehicles = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "cliente",
            joinColumns = @JoinColumn(name = "referencia"),
            inverseJoinColumns = @JoinColumn(name = "referencia"))
    private Set<Client> clients = new LinkedHashSet<>();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Veiculo> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Veiculo> vehicles) {
        this.vehicles = vehicles;
    }

    public InstitutionalClient getInstitutionalClient() {
        return institutionalClient;
    }

    public void setInstitutionalClient(InstitutionalClient institutionalClient) {
        this.institutionalClient = institutionalClient;
    }

    public PrivateClient getPrivateClient() {
        return privateClient;
    }

    public void setPrivateClient(PrivateClient privateClient) {
        this.privateClient = privateClient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return nif != null && Objects.equals(nif, client.nif);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nif = " + nif + ", " +
                "name = " + name + ", " +
                "address = " + address + ", " +
                "phone = " + phone + ")";
    }
}
