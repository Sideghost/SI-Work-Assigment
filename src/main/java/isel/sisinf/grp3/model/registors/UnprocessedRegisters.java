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
 * Mapping of table "registos_nao_processados" present in DB.
 */
@Entity
@NamedQuery(name = "UnprocessedRegisters.findByKey",
        query = "SELECT ur FROM UnprocessedRegisters ur WHERE ur.id =:key") // finds a UnprocessedRegister by its key
@Table(name = "registos_nao_processados")
public class UnprocessedRegisters implements IUnprocessedRegisters {

    @Id
    @Column(name = "id", nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gps", nullable = false, length = 50)
    private Long idGps;

    @Column(name = "marca_temporal")
    private Timestamp timeStamp;

    public UnprocessedRegisters() {

    }

    public UnprocessedRegisters(Long id, Timestamp timeStamp, Long idGps) {
        this.id = id;
        this.idGps = idGps;
        this.timeStamp = timeStamp;
    }

    public UnprocessedRegisters(Long id, Timestamp timestamp) {
        this.id = id;
        this.idGps = null;
        this.timeStamp = timestamp;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
        UnprocessedRegisters unprocessedRegister = (UnprocessedRegisters) o;
        return id != null && Objects.equals(id, unprocessedRegister.id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idGps = " + idGps + ", " +
                "timeStamp = " + timeStamp + ")";
    }
}
