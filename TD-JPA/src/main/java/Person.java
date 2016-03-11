import com.google.maps.model.LatLng;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String surname;
    String firstName;
    String adress;
    Date dateOfBirth;
    int age;
    double latitude, longitude;

    Person()
    {
    }

    public Person(String surname, String firstName, String adress, Date dateOfBirth, int age) {
        this.surname = surname;
        this.firstName = firstName;
        this.adress = adress;
        this.dateOfBirth = dateOfBirth;
        this.age = age;

        LatLng latLng = Utils.getLatLng(adress);
        latitude = latLng.lat;
        longitude = latLng.lng;
    }

    @Override
    public String toString() {
        return firstName + " " + surname + ", born on " + dateOfBirth + ", is " + age + " years old." +
                "\r\n" + "He lives at " + adress + ", for which coordinates are : " + longitude + "/" + latitude;
    }

    // Specific methods for persistence
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdress() {
      return adress;
    }

    public void setAdress(String adress) {
      this.adress = adress;

      LatLng latLng = Utils.getLatLng(this.adress);
      this.latitude = latLng.lat;
      this.longitude = latLng.lng;
    }
}
