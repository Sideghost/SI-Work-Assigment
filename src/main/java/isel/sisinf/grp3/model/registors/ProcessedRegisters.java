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

package isel.sisinf.grp3.model.registors;

import isel.sisinf.grp3.model.Gps;
import isel.sisinf.grp3.model.Vehicle;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Mapping of table "registos_processados" present in DB.
 */
@Entity
@NamedQuery(name = "ProcessedRegisters.findByKey",
        query = "SELECT pr FROM ProcessedRegisters pr WHERE pr.id =:key")// finds a ProcessedRegister by its key
@Table(name = "registos_processados")
public class ProcessedRegisters implements IProcessedRegisters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "marca_temporal", nullable = false)
    private Timestamp timeStamp;

    @ManyToMany // anotacao e esta ou OnetoOne
    @JoinTable(name = "alarmes",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    // inverseJoinColumns WTF is this? tabela no meio para saber juntar info
    private Set<Vehicle> vehicles = new LinkedHashSet<>();


    public ProcessedRegisters() {
    }

    public ProcessedRegisters(Long id, Timestamp timeStamp) {
        this.id = id;
        this.id = getGps().getId();
        this.timeStamp = timeStamp;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Gps getGps() {
        return null;
    }

    @Override
    public void setGps(Gps gps) {

    }

    @Override
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    @Override
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProcessedRegisters processedRegister = (ProcessedRegisters) o;
        return id != null && Objects.equals(id, processedRegister.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idGps = " + getGps().getId() + ", " +
                "timeStamp = " + timeStamp + ")";
    }
}
