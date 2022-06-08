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
package isel.sisinf.zajudas;

import java.sql.Date;
import java.util.Set;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@NamedQuery(name="Student.findByKey",
			query="SELECT s FROM Student s WHERE s.studentNumber =:key")
@NamedQuery(name="Student.EnrolledInCourse",
	query="SELECT s FROM Student s join s.courses c WHERE c.courseId =:key")

@NamedStoredProcedureQuery(
	    name = "namedfromCountry", 
	    procedureName = "fromCountry", 
	    parameters = { 
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)//, 
	        //@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
	    }
	)

@NamedStoredProcedureQuery(
	    name = "altnamedfromCountry", 
	    procedureName = "fromCountry", 
	    		resultSetMappings = {"namedfromCountryResult"},
	    parameters = { 
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class)//, 
	        //@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
	    }
	)
@SqlResultSetMapping(name="namedfromCountryResult",
classes={
    @ConstructorResult(targetClass=Student.class,
        columns={
            @ColumnResult(name="studentNumber"),
            @ColumnResult(name="name"),
            @ColumnResult(name="dateBirth"),
            @ColumnResult(name="sex", type=Character.class),
            @ColumnResult(name="country")})
})
public class Student {
	@Id
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
	
	@Override
	public String toString() {
		return "Student [studentNumber=" + studentNumber + ", name=" + name + ", dateBirth=" + dateBirth + ", sex="
				+ sex + ", homeCountry=" + homeCountry + "]";
	}
	
	public Student() {} 
	public Student(int stNumber,String name, java.util.Date dtBirth, char sex, int homeCountry)
	{
		//How to construct a country? No JPAcontext code should be here. Again, the solution is to use a Virtual Proxy
		this(stNumber,name,dtBirth,sex,null);
	}
	public Student(int stNumber,String name, java.util.Date dtBirth, char sex, Country homeCountry)
	{
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
	
	@ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name="country",referencedColumnName="countryId")
	private Country homeCountry;

	
	
	@ManyToMany
    @JoinTable(name="StudentCourse",
          joinColumns=@JoinColumn(name="studentId"),
          inverseJoinColumns=@JoinColumn(name="courseId"))
	private Set<Course> courses;
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

	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
