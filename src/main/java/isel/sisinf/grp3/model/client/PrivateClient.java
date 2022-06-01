package isel.sisinf.grp3.model.client;

import jakarta.persistence.*;

@Entity
@Table(name = "particulares")
public class PrivateClient {

    @Id
    @Column(name = "nif", nullable = false, length = 15)
    private String nif;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nif", nullable = false)
    private Client client;

    @Column(name = "cc", nullable = false, length = 15)
    private String cc;

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
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

    public void setNif(String id) {
        this.nif = id;
    }
}