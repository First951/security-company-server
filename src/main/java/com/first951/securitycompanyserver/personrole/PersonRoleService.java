package com.first951.securitycompanyserver.personrole;

import java.util.List;

public interface PersonRoleService {

    PersonRoleDto get(int id);

    List<PersonRoleDto> getAll();

    PersonRoleDto create(PersonRoleDto personRoleDto);

    PersonRoleDto update(PersonRoleDto personRoleDto);

    PersonRoleDto delete(int id);

}
