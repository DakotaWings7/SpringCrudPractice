package ru.aliev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.aliev.springcourse.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 15, "aliev@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 25, "aliev@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 21, "aliev@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 43, "aliev@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }


    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person person = show(id);

        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        person.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }

    public void clear() {
        people.clear();
    }
}
