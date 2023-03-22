package com.first951.securitycompanyserver.schema.organization;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import com.first951.securitycompanyserver.schema.post.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final PostMapper postMapper;

    @Override
    public OrganizationDto create(OrganizationDto organizationDto) {
        try {
            Organization organizationRequest = organizationMapper.toEntity(organizationDto);
            Organization organizationResponse = organizationRepository.save(organizationRequest);
            return toDto(organizationResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public OrganizationDto read(long id) {
        Organization organizationResponse = organizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Организация с id=" + id + " не найдена"));
        return toDto(organizationResponse);
    }

    @Override
    public List<OrganizationDto> search(OrganizationDto filter, Long from, Integer size) {
        Pageable pageable = new OffsetBasedPage(from, size);
        List<Organization> page = organizationRepository.search(filter.getAddress(), filter.getName(), pageable);
        return toDtoList(page);
    }

    @Override
    public OrganizationDto update(long id, OrganizationDto organizationDto) {
        if (organizationRepository.existsById(id)) {
            Organization organizationRequest = organizationMapper.toEntity(organizationDto);
            organizationRequest.setId(id);

            Organization organizationResponse = organizationRepository.save(organizationRequest);
            return toDto(organizationResponse);
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

    @Override
    public void delete(long id) {
        if (organizationRepository.existsById(id)) {
            try {
                organizationRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить организацию с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

    private OrganizationDto toDto(Organization organization) {
        OrganizationDto organizationDto = organizationMapper.toDto(organization);
        organizationDto.setPostDtos(postMapper.toDtoList(organization.getPosts()));
        return organizationDto;
    }

    private List<OrganizationDto> toDtoList(List<Organization> organizations) {
        List<OrganizationDto> response = new ArrayList<>();
        organizations.forEach(organization -> response.add(toDto(organization)));
        return response;
    }

}
