package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "ADDRESSES")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    public Address() {

    }

    public Address(String city, String address) {
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return id == address1.id && Objects.equals(city, address1.city) && Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, address);
    }
}
