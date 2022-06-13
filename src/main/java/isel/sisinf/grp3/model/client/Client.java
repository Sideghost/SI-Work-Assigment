package isel.sisinf.grp3.model.client;

import isel.sisinf.grp3.model.Vehicle;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Mapping of table "Cliente" present in DB.
 * todo
 */
@Entity
@NamedQuery(name = "Client.findByKey",
        query = "SELECT c FROM Client c WHERE c.nif =:key")
@NamedStoredProcedureQuery(
        name = "addVehicleToClient",
        procedureName = "add_vehicle_to_client_or_not",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)
        }
)
@Table(name = "cliente")
public class Client implements IClient {

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

    @OneToMany(mappedBy = "client")
    private Set<Vehicle> vehicles = new LinkedHashSet<>();

    @ManyToOne
    @JoinTable(name = "cliente",
            joinColumns = @JoinColumn(name = "referencia"),
            inverseJoinColumns = @JoinColumn(name = "referencia"))
    private PrivateClient reference;

    public Client() {
    }

    public Client(String nif, String name, String address, String phone, String reference) {
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = true;
        //this.reference = reference;
        this.institutionalClient = null;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PrivateClient getReference() {
        return reference;
    }

    public void setReference(PrivateClient client) {
        this.reference = client;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
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

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getClientId() {
        return getNif();
    }

    @Override
    public void setClientId(String clientId) {
        setNif(clientId);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
                "phone = " + phone + ", " +
                "status =" + status + ")";
    }
}
