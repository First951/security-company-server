package com.first951.securitycompanyserver.role;

import java.util.List;

public interface RoleService {

    RoleDto get(int id);

    List<RoleDto> getAll();

    RoleDto create(RoleDto roleDto);

    RoleDto update(RoleDto roleDto);

    RoleDto delete(int id);

}
