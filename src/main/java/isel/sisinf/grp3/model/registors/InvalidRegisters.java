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

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

//import java.time.LocalDate;

/**
 * Mapping of table "registos_invalidos" present in DB.
 */
@Entity
@NamedQuery(name = "InvalidRegisters.findByKey",
        query = "SELECT ir FROM InvalidRegisters ir WHERE ir.id =:key")// finds a InvalidRegister by its key.
@NamedStoredProcedureQuery(
        name = "eliminateInvalidRegisters",
        procedureName = "eliminate_invalid_registers",
        parameters = {}
)
@Table(name = "Registos_invalidos")
public class InvalidRegisters implements IInvalidRegisters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_gps")
    private Long idGps;

    @Column(name = "marca_temporal", nullable = false)
    private Timestamp timeStamp;

    public InvalidRegisters(Long idGps) {
        this.idGps = idGps;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public InvalidRegisters() {
        this.idGps = null;
        this.timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long Id) {
        this.id = Id;
    }

    @Override
    public Long getIdGps() {
        return idGps;
    }

    @Override
    public void setIdGps(Long idGps) {
        this.idGps = idGps;
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
        InvalidRegisters invalidRegister = (InvalidRegisters) o;
        return id != null && Objects.equals(id, invalidRegister.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idGps = " + idGps + ", " +
                "timeStamp = " + timeStamp + ")";
    }
}