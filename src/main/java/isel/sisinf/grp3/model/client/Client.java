package isel.sisinf.grp3.model.client;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Objects;

@Entity
@Table(name = "cliente")
abstract class Client {
    @Id
    @Column(name = "NIF")
    private String  NIF; // TODO: 26/05/2022 Change to Integer

    @Column(name = "nome")
    private String  name;

    @Column(name = "morada")
    private String address;

    @Column(name = "telefone")
    private String phone;

    @Column(name = "tipo")
    private String  type;

    @Column(name = "referencia")
    private String  reference;

    @Column(name = "ativo")
    private Boolean status;


    public String getNif() {return this.NIF;}

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Client)){return false;}
        Client castOther = (Client)other;
        return (this.NIF == castOther.NIF); //check only primary key
    }
    @Override
    public int hashCode() {
        return Objects.hash(NIF);
    }
}
