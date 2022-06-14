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

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Mapping of table "institucionais" present in DB.
 */
@Entity
@NamedQuery(name = "InstitutionalClient.findByKey",
        query = "SELECT ic FROM InstitutionalClient ic WHERE ic.nif =:key") // finds an institutional client by its key
@Table(name = "institucionais")
public class InstitutionalClient implements IInstitutionalClient {

    @Id
    @Column(name = "nif", nullable = false, length = 15)
    private String nif;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nif", nullable = false)
    private Client client;

    @Column(name = "nome_contacto", nullable = false, length = 100)
    private String contactName;

    public InstitutionalClient() {
    }

    public InstitutionalClient(String nif, String contactName) {
        this.nif = nif;
        this.contactName = contactName;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InstitutionalClient institutionalClient = (InstitutionalClient) o;
        return nif != null && Objects.equals(nif, institutionalClient.nif);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nif = " + nif + ", " +
                "contactName = " + contactName + ")";
    }
}