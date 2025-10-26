/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info5100.university.example.Persona;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author kal bugrara
 */
public class PersonDirectory {
    
      ArrayList<Person> personlist ;
    
      public PersonDirectory (){
          
       this.personlist = new ArrayList<>();

    }

    public Person newPerson(String id) {
        if (id == null) throw new IllegalArgumentException("id cannot be null");
        if (idExists(id)) throw new IllegalArgumentException("ID already exists: " + id);
        Person p = new Person(id);
        personlist.add(p);
        return p;
    }

    public Person findPerson(String id) {
        if (id == null) return null;
        for (Person p : personlist) {
            if (p.isMatch(id)) return p;
        }
        return null;
    }

    public java.util.List<Person> getPersonlist() {
        return Collections.unmodifiableList(personlist);
    }

    public boolean emailExists(String email) {
        if (email == null) return false;
        for (Person p : personlist) {
            if (email.equalsIgnoreCase(p.getEmail())) return true;
        }
        return false;
    }

    public boolean idExists(String id) {
        if (id == null) return false;
        for (Person p : personlist) {
            if (id.equals(p.getPersonId())) return true;
        }
        return false;
    }


    public Person findByEmail(String email) {
        if (email == null) return null;
        for (Person p : personlist) {
            if (email.equalsIgnoreCase(p.getEmail())) return p;
        }
        return null;
    }

 
    public boolean removePerson(String id) {
        Person target = findPerson(id);
        return target != null && personlist.remove(target);
    }

  
    public boolean updateEmail(String personId, String newEmail) {
        Person p = findPerson(personId);
        if (p == null) return false;

        if (newEmail == null || newEmail.equalsIgnoreCase(p.getEmail())) {
            p.setEmail(newEmail); 
            return true;
        }
        if (emailExists(newEmail)) return false; 
        p.setEmail(newEmail);
        return true;
    }

 
    public boolean updatePhone(String personId, String newPhone) {
        Person p = findPerson(personId);
        if (p == null) return false;
        p.setPhone(newPhone);
        return true;
    }


    private static final AtomicLong SEQ = new AtomicLong(100000);

    private String nextUniversityId() {
        String id;
        do {
            id = "U" + SEQ.getAndIncrement();
        } while (idExists(id));
        return id;
    }

   
    public Person newPersonWithAutoId(String email, String phone) {
        if (email != null && emailExists(email)) {
            throw new IllegalArgumentException("Email already registered: " + email);
        }
        String id = nextUniversityId();
        Person p = new Person(id);
        p.setEmail(email);
        p.setPhone(phone);
        personlist.add(p);
        return p;
    }
    
}
