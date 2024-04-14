package ru.aliev.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.aliev.springcourse.dao.PersonDAO;
import ru.aliev.springcourse.model.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> personFromDB = personDAO.show(person.getEmail());
        if (personFromDB.isPresent() && personFromDB.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "User with that email is already exists.");
        }
    }
}
