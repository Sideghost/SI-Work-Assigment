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
*/
package isel.sisinf.grp3.model.client;

import isel.sisinf.grp3.model.Vehicle;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Mapping of table "Cliente" present in DB.
 */
@Entity
@NamedQuery(name = "Client.findByKey",
        query = "SELECT c FROM Client c WHERE c.nif =:key") // finds a client by its key
@NamedQuery(name = "Client.getVehicles",
        query = "SELECT c From Client c join c.vehicles v WHERE v.client.nif =:key") // finds all client vehicles
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

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinTable(name = "cliente",
//            joinColumns = @JoinColumn(name = "referencia"),
//            inverseJoinColumns = @JoinColumn(name = "referencia"))
    @JoinColumn(name = "referencia", referencedColumnName = "nif")
    private PrivateClient reference;

    public Client() {
    }

    public Client(String nif, String name, String address, String phone, PrivateClient reference) {
        this.nif = nif;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = true;
        this.reference = reference;
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
