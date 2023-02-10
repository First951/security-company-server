package com.first951.securitycompanyserver.organization;

import java.util.List;

public interface OrganizationService {

    OrganizationDto get(int id);

    List<OrganizationDto> getAll();

    OrganizationDto create(OrganizationDto organizationDto);

    OrganizationDto update(OrganizationDto organizationDto);

    OrganizationDto delete(int id);

}
