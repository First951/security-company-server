package com.first951.securitycompanyserver.role;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDto get(int id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", String.valueOf(id)));

        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAll() {
        Iterable<RoleEntity> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();

        roles.forEach(role -> roleDtos.add(modelMapper.map(role, RoleDto.class)));
        return roleDtos;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        RoleEntity role = modelMapper.map(roleDto, RoleEntity.class);

        RoleEntity createdRole = roleRepository.save(role);
        return modelMapper.map(createdRole, RoleDto.class);
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        RoleEntity roleRequest = modelMapper.map(roleDto, RoleEntity.class);
        RoleEntity role = roleRepository.findById(roleRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", String.valueOf(roleRequest.getId())));
        role.setName(roleRequest.getName());

        RoleEntity createdRole = roleRepository.save(role);
        return modelMapper.map(createdRole, RoleDto.class);
    }

    @Override
    public RoleDto delete(int id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", String.valueOf(id)));

        roleRepository.delete(role);
        return modelMapper.map(role, RoleDto.class);
    }
}
