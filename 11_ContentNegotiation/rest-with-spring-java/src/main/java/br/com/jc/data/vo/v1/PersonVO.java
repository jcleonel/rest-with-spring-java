package br.com.jc.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
//@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class PersonVO implements Serializable {
    private static final long serialVersionUID = 1L;

    //@JsonProperty("id")
    private Long id;

    //@JsonProperty("firstName")
    private String firstName;

    //@JsonProperty("lastName")
    private String lastName;

    //@JsonProperty("address")
    private String address;

    //@JsonIgnore
    //@JsonProperty("gender")
    private String gender;

    public PersonVO() {
    }

    public PersonVO(Long id, String firstName, String lastName, String address, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, firstName, gender, id, lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonVO other = (PersonVO) obj;
        return Objects.equals(address, other.address) && Objects.equals(firstName, other.firstName)
                && Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
                && Objects.equals(lastName, other.lastName);
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
                + ", gender=" + gender + "]";
    }
}
