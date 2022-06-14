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

package isel.sisinf.grp3.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Interface Gps that has all the signatures of the setters and getters needed for
 * the "gps" table present in the DB.
 */
public interface IGps {
    Long getId();

    void setId(Long id);

    BigDecimal getLongitude();

    void setLongitude(BigDecimal longitude);

    BigDecimal getLatitude();

    void setLatitude(BigDecimal latitude);

    String getLicencePlate();

    void setLicensePlate(String licencePlate);

    String getStatus();

    void setStatus(String status);

    Timestamp getTimeStamp();

    void setTimeStamp(Timestamp timeStamp);
}
