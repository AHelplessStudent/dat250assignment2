package no.hvl.dat250.jpa.basicexample;


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

        setUp();

        checkObjects();

    }

    static void setUp() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();


        // Fill the tables with information
        Person person = new Person();
        person.setName("Max Mustermann");

        Address address = new Address();
        address.setNumber(28);
        address.setStreet("Inndalsveien");
        address.setPeople(new ArrayList<>(List.of(person)));

        person.setAddresses(new ArrayList<>(List.of(address)));

        Bank bank = new Bank();
        bank.setName("Pengebank");

        Pincode pincode = new Pincode();
        pincode.setPincode("123");
        pincode.setCount(1);

        CreditCard card1 = new CreditCard();
        card1.setNumber(12345);
        card1.setBalance(-5000);
        card1.setLimit(-10000);
        card1.setBank(bank);
        card1.setPincode(pincode);

        CreditCard card2 = new CreditCard();
        card2.setNumber(123);
        card2.setBalance(1);
        card2.setLimit(2000);
        card2.setBank(bank);
        card2.setPincode(pincode);

        Collection<CreditCard> creditCards = new ArrayList<>(List.of(card1, card2));
        person.setCreditCards(creditCards);
        bank.setCreditCards(creditCards);

        // add the Person value and the relations between the tables adds the rest.
        em.persist(person);

        em.getTransaction().commit();
        em.close();
    }


    /**
     * Debugging method / printing method to see which tables were created
     * and the values of the rows created in main.
     */
    static void checkObjects() {
        String[] names = new String[]{"Person", "Address", "CreditCard", "Pincode", "Bank"};
        EntityManager em = factory.createEntityManager();

        System.out.println("-----------------------------------");
        for (String name : names) {
            Query q = em.createQuery(String.format("select x from %s x", name));

            List resultList = q.getResultList();
            for (Object x : resultList) {
                System.out.println(x);
            }
            System.out.println("-----------------------------------");
        }
        em.close();
    }


}
