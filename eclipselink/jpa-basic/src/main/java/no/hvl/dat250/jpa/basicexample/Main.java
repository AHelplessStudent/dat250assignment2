package no.hvl.dat250.jpa.basicexample;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Person p = new Person();
        p.setName("Max Mustermann");

        Address a = new Address();
        a.setNumber(28);
        a.setStreet("Inndalsveien");

        List<Address> adresses = new ArrayList<>();
        adresses.add(a);
        p.setAddresses(adresses);

        CreditCard card1 = new CreditCard();
        card1.setNumber(12345);
        card1.setBalance(-5000);
        card1.setLimit(-10000);

        Pincode pin1 = new Pincode();
        pin1.setCount(1);
        pin1.setPincode("123");

        card1.setPincode(pin1);

        CreditCard card2 = new CreditCard();
        card2.setNumber(123);
        card2.setBalance(1);
        card2.setLimit(2000);

        card2.setPincode(pin1);

        Bank bank = new Bank();
        bank.setName("Pengebank");

        List<CreditCard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        bank.setCreditCards(cards);
        p.setCreditCards(cards);


        em.persist(pin1);
        em.persist(card1);
        em.persist(card2);
        em.persist(bank);
        em.persist(a);
        em.persist(p);

        em.getTransaction().commit();


        // print output

        Query q = em.createQuery("select per from Person per");


        List<Person> personList = q.getResultList();

        for (Person person : personList) {
            System.out.println(person);
        }
        System.out.println("Size: " + personList.size());

        Query q2 = em.createQuery("select add from Address add");
        List<Address> addlist = q2.getResultList();

        for (Address ad : addlist) {
            System.out.println(ad);
        }
        System.out.println("Size add =" + addlist.size());

    }

}
