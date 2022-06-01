package isel.sisinf.grp3.model;

import isel.sisinf.grp3.model.registos.Unprocessed_Registers;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "gps")
public class Gps {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "marca_temporal", nullable = false)
    private Instant timeStamp;

    @Column(name = "latitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 8, scale = 5)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "idGps")
    private Set<Veiculo> veiculos = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "Gps")
    private Unprocessed_Registers registosNaoProcessado;

    public Unprocessed_Registers getRegistosNaoProcessado() {
        return registosNaoProcessado;
    }

    public void setRegistosNaoProcessado(Unprocessed_Registers registosNaoProcessado) {
        this.registosNaoProcessado = registosNaoProcessado;
    }

    public Set<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Set<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant marcaTemporal) {
        this.timeStamp = marcaTemporal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
