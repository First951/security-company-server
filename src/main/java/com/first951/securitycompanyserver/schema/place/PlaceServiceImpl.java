package com.first951.securitycompanyserver.schema.place;

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
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;
    private final PostMapper postMapper;

    @Override
    public PlaceDto create(PlaceDto placeDto) {
        try {
            Place placeRequest = placeMapper.toEntity(placeDto);
            Place placeResponse = placeRepository.save(placeRequest);
            return toDto(placeResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public PlaceDto read(long id) {
        Place placeResponse = placeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объект с id=" + id + " не найден"));
        return toDto(placeResponse);
    }

    @Override
    public List<PlaceDto> search(PlaceDto filterDto, Long from, Integer size) {
        Place filter = placeMapper.toEntity(filterDto);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Place> places = placeRepository.search(filter.getAddress(), pageable);
        return toDtoList(places);
    }

    @Override
    public PlaceDto update(long id, PlaceDto placeDto) {
        if (placeRepository.existsById(id)) {
            Place placeRequest = placeMapper.toEntity(placeDto);
            placeRequest.setId(id);

            Place placeResponse = placeRepository.save(placeRequest);
            return toDto(placeResponse);
        } else {
            throw new NotFoundException("Объект с id=" + id + " не найден");
        }
    }

    @Override
    public void delete(long id) {
        if (placeRepository.existsById(id)) {
            try {
                placeRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить объект с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Объект с id=" + id + " не найден");
        }
    }

    private PlaceDto toDto(Place place) {
        PlaceDto placeDto = placeMapper.toDto(place);
        placeDto.setPostDtos(postMapper.toDtoList(place.getPosts()));
        return placeDto;
    }

    private List<PlaceDto> toDtoList(List<Place> places) {
        List<PlaceDto> response = new ArrayList<>();
        places.forEach(place -> response.add(toDto(place)));
        return response;
    }

}
