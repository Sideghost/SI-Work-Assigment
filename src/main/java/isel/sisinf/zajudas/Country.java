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

import java.util.ArrayList;
import java.util.Set;
import java.util.Objects;


import jakarta.persistence.*;

@Entity
@NamedQuery(name="Country.findByKey",
			query="SELECT c FROM Country c WHERE c.countryId =:key")

@NamedStoredProcedureQuery(
	    name = "namedrand_fx", 
	    procedureName = "rand_fx", 
	    parameters = { 
	        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class), 
	        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Number.class)
	    }
	)

public class Country implements ICountry{
	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", name=" + name + "]";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long countryId;
	@Override
	public int hashCode() {
		return Objects.hash(countryId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return countryId == other.countryId;
	}
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="homeCountry",fetch = FetchType.LAZY)
    private Set<Student> students;
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
