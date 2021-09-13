package no.hvl.dat250.jpa.basicexample;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Relation between Bank and CreditCards
    @OneToMany
    private Collection<CreditCard> creditCards = new ArrayList<CreditCard>();

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

    public Collection<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        StringBuilder cards = new StringBuilder();

        for (CreditCard a : creditCards) {
            cards.append(a.getNumber()).append(",");
        }

        return String.format("Bank [ id = %d, name = %s, CreditCards [ %s] ]", id, name, cards);
    }
}
