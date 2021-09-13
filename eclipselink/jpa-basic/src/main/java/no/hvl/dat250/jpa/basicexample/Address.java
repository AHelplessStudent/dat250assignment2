package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;

    @ManyToMany(mappedBy = "addresses")
    private Collection<Person> people = new ArrayList<>();

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }

    public Collection<Person> getPeople() {
        return people;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        StringBuilder peepz = new StringBuilder();
        for (Person p : people) {
            peepz.append(p.getName()).append(",");
        }
        return String.format("Address [ People = [%s], street = %s, number = %d, id = %d ]", peepz, street, number, id);
    }


}
