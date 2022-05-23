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
package isel.sisinf.ap6.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Student {

    @Id
    @ManyToOne(mapedBy="student",)
    private int studentNumber;

    @Override
    public int hashCode() {
        return Objects.hash(studentNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return studentNumber == other.studentNumber;
    }

    public Student() {
    }

    public Student(int stNumber, String name, java.util.Date dtBirth, char sex, Country homeCountry) {
        this.studentNumber = stNumber;
        this.name = name;
        this.dateBirth = new Date(dtBirth.getTime());
        this.sex = sex;
        this.homeCountry = homeCountry;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Date dateBirth;

    private char sex;


    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Country getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(Country homeCountry) {
        this.homeCountry = homeCountry;
    }

    private Country homeCountry;

    @Override
    public String toString() {
        return "Student [studentNumber=" + studentNumber + ", name=" + name + ", dateBirth=" + dateBirth + ", sex="
                + sex + ", homeCountry=" + homeCountry + "]";
    }

}
