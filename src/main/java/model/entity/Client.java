package model.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Client",
        uniqueConstraints={@UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class Client implements Serializable{

    private int clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int postalCode;
    private Set<Account> setOfAccounts;

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "postal_code")
    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @OneToMany(mappedBy = "clientID", orphanRemoval = true)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    public Set<Account> getSetOfAccounts() {
        return setOfAccounts;
    }

    public void setSetOfAccounts(Set<Account> setOfAccounts) {
        this.setOfAccounts = setOfAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (clientID != client.clientID) return false;
        if (postalCode != client.postalCode) return false;
        if (!address.equals(client.address)) return false;
        if (!city.equals(client.city)) return false;
        if (!firstName.equals(client.firstName)) return false;
        if (!lastName.equals(client.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientID;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + postalCode;
        result = 31 * result + (setOfAccounts != null ? setOfAccounts.hashCode() : 0);
        return result;
    }
}
