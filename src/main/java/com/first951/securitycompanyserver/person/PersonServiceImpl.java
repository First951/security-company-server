package com.first951.securitycompanyserver.person;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PersonDto get(int id) {
        PersonEntity person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", String.valueOf(id)));

        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> getAll() {
        Iterable<PersonEntity> persons = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();

        persons.forEach(person -> modelMapper.map(person, PersonDto.class));
        return personDtos;
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        PersonEntity person = modelMapper.map(personDto, PersonEntity.class);

        PersonEntity createdPerson = personRepository.save(person);
        return modelMapper.map(createdPerson, PersonDto.class);
    }

    @Override
    public PersonDto update(int id, PersonDto personDto) {
        PersonEntity personRequest = modelMapper.map(personDto, PersonEntity.class);
        PersonEntity person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", String.valueOf(id)));
        person.setLastName(personRequest.getLastName());
        person.setFirstName(personRequest.getFirstName());
        person.setPatronymic(personRequest.getPatronymic());
        person.setPhoneNumber(personRequest.getPhoneNumber());

        PersonEntity createdPerson = personRepository.save(person);
        return modelMapper.map(createdPerson, PersonDto.class);
    }

    @Override
    public PersonDto deletePost(int id) {
        PersonEntity person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "id", String.valueOf(id)));

        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }
}
