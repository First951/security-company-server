package com.first951.securitycompanyserver.organization;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrganizationDto get(int id) {
        OrganizationEntity organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", String.valueOf(id)));

        return modelMapper.map(organization, OrganizationDto.class);
    }

    @Override
    public List<OrganizationDto> getAll() {
        Iterable<OrganizationEntity> organizations = organizationRepository.findAll();
        List<OrganizationDto> organizationDtos = new ArrayList<>();

        organizations.forEach(organization -> modelMapper.map(organization, OrganizationDto.class));
        return organizationDtos;
    }

    @Override
    public OrganizationDto create(OrganizationDto organizationDto) {
        OrganizationEntity organization = modelMapper.map(organizationDto, OrganizationEntity.class);

        OrganizationEntity createdOrganization = organizationRepository.save(organization);
        return modelMapper.map(createdOrganization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto update(int id, OrganizationDto organizationDto) {
        OrganizationEntity organizationRequest = modelMapper.map(organizationDto, OrganizationEntity.class);
        OrganizationEntity organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", String.valueOf(id)));
        organization.setAddress(organizationRequest.getAddress());
        organization.setName(organizationRequest.getName());

        OrganizationEntity createdOrganization = organizationRepository.save(organization);
        return modelMapper.map(createdOrganization, OrganizationDto.class);
    }

    @Override
    public OrganizationDto deletePost(int id) {
        OrganizationEntity organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", String.valueOf(id)));

        organizationRepository.delete(organization);
        return modelMapper.map(organization, OrganizationDto.class);
    }
}
