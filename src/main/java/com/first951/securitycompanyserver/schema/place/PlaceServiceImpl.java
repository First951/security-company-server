package com.first951.securitycompanyserver.schema.place;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import com.first951.securitycompanyserver.schema.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    private final OrganizationService organizationService;

    @Override
    public PlaceDto create(PlaceDto placeDto) {
        try {
            Place placeRequest = placeMapper.toEntity(placeDto, organizationService, MappingType.DEFAULT);
            Place placeResponse = placeRepository.save(placeRequest);
            return placeMapper.toDto(placeResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public PlaceDto read(long id) {
        Place placeResponse = placeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Организация с id=" + id + " не найдена"));
        return placeMapper.toDto(placeResponse);
    }

    @Override
    public List<PlaceDto> searchByName(PlaceDto filterDto, Long from, Integer size) {
        Place filter = placeMapper.toEntity(filterDto, organizationService, MappingType.FORCE);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Place> places = placeRepository.findAllByName(filter.getName(), pageable);
        return placeMapper.toDtoList(places);
    }

    @Override
    public PlaceDto update(long id, PlaceDto placeDto) {
        if (placeRepository.existsById(id)) {
            Place placeRequest = placeMapper.toEntity(placeDto, organizationService, MappingType.DEFAULT);
            placeRequest.setId(id);

            Place placeResponse = placeRepository.save(placeRequest);
            return placeMapper.toDto(placeResponse);
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

    @Override
    public void delete(long id) {
        if (placeRepository.existsById(id)) {
            try {
                placeRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить организацию с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Организация с id=" + id + " не найдена");
        }
    }

}
