package com.first951.securitycompanyserver.organization;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper = Mappers.getMapper(OrganizationMapper.class);

    @Override
    public OrganizationDto create(OrganizationDto organizationDto) {
        try {
            Organization organizationRequest = mapper.toEntity(organizationDto);
            Organization organizationResponse = repository.save(organizationRequest);
            return mapper.toDto(organizationResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public OrganizationDto read(long id) {
        Organization organizationResponse = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Организация с id=" + id + " не найдена"));
        return mapper.toDto(organizationResponse);
    }

    @Override
    public List<OrganizationDto> search(OrganizationDto filter, Long from, Integer size) {
        Pageable pageable = new OffsetBasedPage(from, size);
        List<Organization> page = repository.findAllByAddressAndName(filter.getAddress(), filter.getName(), pageable);
        return mapper.toDtoList(page);
    }

    @Override
    public OrganizationDto update(long id, OrganizationDto organizationDto) {
        if (repository.existsById(id)) {
            Organization organizationRequest = mapper.toEntity(organizationDto);
            organizationRequest.setId(id);

            Organization organizationResponse = repository.save(organizationRequest);
            return mapper.toDto(organizationResponse);
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

    @Override
    public void delete(long id) {
        if (repository.existsById(id)) {
            try {
                repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить организацию с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

}
