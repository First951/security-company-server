package com.first951.securitycompanyserver.schema.person;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonDto create(PersonDto personDto) {
        try {
            Person personRequest = personMapper.toEntity(personDto, MappingType.DEFAULT);
            Person personResponse = personRepository.save(personRequest);
            return personMapper.toDto(personResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public PersonDto read(long id) {
        Person personResponse = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Человек с id=" + id + " не найден"));
        return personMapper.toDto(personResponse);
    }

    @Override
    public List<PersonDto> search(PersonDto filter, Long from, Integer size) {
        Pageable pageable = new OffsetBasedPage(from, size);
        List<Person> page = personRepository.search(filter.getLastName(), filter.getFirstName(),
                filter.getPatronymic(), filter.getPhoneNumber(), pageable);
        return personMapper.toDtoList(page);
    }

    @Override
    public PersonDto update(long id, PersonDto personDto) {
        if (personRepository.existsById(id)) {
            Person personRequest = personMapper.toEntity(personDto, MappingType.DEFAULT);
            personRequest.setId(id);

            Person personResponse = personRepository.save(personRequest);
            return personMapper.toDto(personResponse);
        } else {
            throw new NotFoundException("Человек с id=" + id + " не найден");
        }
    }

    @Override
    public void delete(long id) {
        if (personRepository.existsById(id)) {
            try {
                personRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить человека с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Человек с id=" + id + " не найден");
        }
    }

}
