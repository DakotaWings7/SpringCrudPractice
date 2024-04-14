package ru.aliev.springcourse.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aliev.springcourse.model.Person;
import ru.aliev.springcourse.repository.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PeopleService {

    private PeopleRepository peopleRepository;

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findPersonById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public Optional<Person> findPersonByEmail(String email) {
        return peopleRepository.findPersonByEmail(email);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void clear() {
        peopleRepository.deleteAll();
    }
}
