package com.first951.securitycompanyserver.schema.organization;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Override
    public OrganizationDto create(OrganizationDto organizationDto) {
        try {
            Organization organizationRequest = organizationMapper.toEntity(organizationDto, MappingType.DEFAULT);
            Organization organizationResponse = organizationRepository.save(organizationRequest);
            return organizationMapper.toDto(organizationResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public OrganizationDto read(long id) {
        Organization organizationResponse = organizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Организация с id=" + id + " не найдена"));
        return organizationMapper.toDto(organizationResponse);
    }

    @Override
    public List<OrganizationDto> search(OrganizationDto filter, Long from, Integer size) {
        Pageable pageable = new OffsetBasedPage(from, size);
        List<Organization> page = organizationRepository.findAllByAddressAndName(filter.getAddress(), filter.getName(), pageable);
        return organizationMapper.toDtoList(page);
    }

    @Override
    public OrganizationDto update(long id, OrganizationDto organizationDto) {
        if (organizationRepository.existsById(id)) {
            Organization organizationRequest = organizationMapper.toEntity(organizationDto, MappingType.DEFAULT);
            organizationRequest.setId(id);

            Organization organizationResponse = organizationRepository.save(organizationRequest);
            return organizationMapper.toDto(organizationResponse);
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

}
