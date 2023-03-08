package com.first951.securitycompanyserver.schema.person;

import java.util.List;

public interface PersonService {

    PersonDto get(int id);

    List<PersonDto> getAll();

    PersonDto create(PersonDto personDto);

    PersonDto update(PersonDto personDto);

    PersonDto delete(int id);

}
