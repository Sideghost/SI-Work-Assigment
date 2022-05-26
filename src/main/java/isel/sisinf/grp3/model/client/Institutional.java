package isel.sisinf.grp3.model.client;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Institucionais")
public class Institutional extends Client {

    @Column(name = "nome_contacto")
    private String contactName;

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Client)){return false;}
        Institutional castOther = (Institutional)other;
        return (Objects.equals(this.getNif(), castOther.getNif())) &&
                this.contactName.equals(castOther.contactName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getNif());
    }

}
