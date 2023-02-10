package com.first951.securitycompanyserver.role;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


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

        roles.forEach(role -> modelMapper.map(role, RoleDto.class));
        return roleDtos;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        RoleEntity role = modelMapper.map(roleDto, RoleEntity.class);

        RoleEntity createdRole = roleRepository.save(role);
        return modelMapper.map(createdRole, RoleDto.class);
    }

    @Override
    public RoleDto update(int id, RoleDto roleDto) {
        RoleEntity roleRequest = modelMapper.map(roleDto, RoleEntity.class);
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", String.valueOf(id)));
        role.setName(roleRequest.getName());

        RoleEntity createdRole = roleRepository.save(role);
        return modelMapper.map(createdRole, RoleDto.class);
    }

    @Override
    public RoleDto deletePost(int id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", String.valueOf(id)));

        roleRepository.delete(role);
        return modelMapper.map(role, RoleDto.class);
    }
}
