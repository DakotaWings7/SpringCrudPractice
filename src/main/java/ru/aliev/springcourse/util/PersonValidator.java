package ru.aliev.springcourse.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.aliev.springcourse.model.Person;
import ru.aliev.springcourse.service.PeopleService;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> personFromDB = peopleService.findPersonByEmail(person.getEmail());
        if (personFromDB.isPresent() && personFromDB.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "User with that email is already exists.");
        }
    }
}
