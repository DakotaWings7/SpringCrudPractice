package ru.aliev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aliev.springcourse.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Transactional(readOnly = true)
    public Optional<Person> show(String email) {

        return sessionFactory.getCurrentSession()
                .createQuery("FROM Person WHERE email = :email")
                .setParameter("email", email)
                .uniqueResultOptional();
    }

    @Transactional
    public void save(Person person) {
        sessionFactory.getCurrentSession().save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person personToUpdate = session.get(Person.class, id);
        personToUpdate.setAge(updatedPerson.getAge());
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("delete from Person").executeUpdate();
    }
}
