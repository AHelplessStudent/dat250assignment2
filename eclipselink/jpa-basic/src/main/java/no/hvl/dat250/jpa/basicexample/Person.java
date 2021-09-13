package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Chose to not specify the names for the connecting table.
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<CreditCard> creditCards = new ArrayList<>();

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        StringBuilder adds = new StringBuilder();
        for (Address a : addresses) {
            adds.append(a.getStreet()).append(",");
        }

        StringBuilder cards = new StringBuilder();
        for (CreditCard c : creditCards) {
            cards.append(c.getNumber()).append(",");
        }

        return String.format("Person : [ id = %d, name = %s, Addresses = [%s], CreditCards = [%s] ]", id, name, adds, cards);

    }


    public Collection<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
