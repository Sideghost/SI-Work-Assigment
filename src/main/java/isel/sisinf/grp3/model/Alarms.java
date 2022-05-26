package isel.sisinf.grp3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "Alarmes")
public class Alarms {
   @Id
   private Integer id;
   private Integer id_GPS;
   @Column(name = "marca_temporal")
   private Time timeStamp;

   @Override
   public boolean equals (Object other) {
      if (this == other) {
         return true;
      }
      if(!(other instanceof Alarms)){return false;}
      Alarms castOther = (Alarms)other;
      return (this.id.equals(castOther.id));
   }
   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
