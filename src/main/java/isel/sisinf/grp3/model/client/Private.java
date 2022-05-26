package isel.sisinf.grp3.model.client;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "Particulares")
public class Private extends Client{

    private String CC;

    @Override
    public boolean equals (Object other) {
        if (this == other) {
            return true;
        }
        if(!(other instanceof Client)){return false;}
        Private castOther = (Private)other;
        return (this.getNif().equals(castOther.getNif())) &&
                this.CC.equals(castOther.CC);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getNif());
    }
}
