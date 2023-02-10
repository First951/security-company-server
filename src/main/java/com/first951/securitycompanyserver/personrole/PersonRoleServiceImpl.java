package com.first951.securitycompanyserver.personrole;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonRoleServiceImpl implements PersonRoleService {

    private final PersonRoleRepository personRoleRepository;
    private final ModelMapper modelMapper;

    @Override
    public PersonRoleDto get(int id) {
        PersonRoleEntity personRole = personRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonRole", "id", String.valueOf(id)));

        return modelMapper.map(personRole, PersonRoleDto.class);
    }

    @Override
    public List<PersonRoleDto> getAll() {
        Iterable<PersonRoleEntity> personRoles = personRoleRepository.findAll();
        List<PersonRoleDto> personRoleDtos = new ArrayList<>();

        personRoles.forEach(personRole -> personRoleDtos.add(modelMapper.map(personRole, PersonRoleDto.class)));
        return personRoleDtos;
    }

    @Override
    public PersonRoleDto create(PersonRoleDto personRoleDto) {
        PersonRoleEntity personRole = modelMapper.map(personRoleDto, PersonRoleEntity.class);

        PersonRoleEntity createdPersonRole = personRoleRepository.save(personRole);
        return modelMapper.map(createdPersonRole, PersonRoleDto.class);
    }

    @Override
    public PersonRoleDto update(PersonRoleDto personRoleDto) {
        PersonRoleEntity personRoleRequest = modelMapper.map(personRoleDto, PersonRoleEntity.class);
        PersonRoleEntity personRole = personRoleRepository.findById(personRoleRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("PersonRole", "id", String.valueOf(personRoleRequest.getId())));
        personRole.setPerson(personRoleRequest.getPerson());
        personRole.setRole(personRoleRequest.getRole());
        personRole.setPeriodFrom(personRoleRequest.getPeriodFrom());
        personRole.setPeriodTo(personRoleRequest.getPeriodTo());

        PersonRoleEntity createdPersonRole = personRoleRepository.save(personRole);
        return modelMapper.map(createdPersonRole, PersonRoleDto.class);
    }

    @Override
    public PersonRoleDto delete(int id) {
        PersonRoleEntity personRole = personRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PersonRole", "id", String.valueOf(id)));

        personRoleRepository.delete(personRole);
        return modelMapper.map(personRole, PersonRoleDto.class);
    }
}
