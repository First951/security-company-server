package com.first951.securitycompanyserver.object;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectServiceImpl implements ObjectService {

    private final ObjectRepository objectRepository;
    private final ModelMapper modelMapper;

    @Override
    public ObjectDto get(int id) {
        ObjectEntity object = objectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object", "id", String.valueOf(id)));

        return modelMapper.map(object, ObjectDto.class);
    }

    @Override
    public List<ObjectDto> getAll() {
        Iterable<ObjectEntity> objects = objectRepository.findAll();
        List<ObjectDto> objectDtos = new ArrayList<>();

        objects.forEach(object -> objectDtos.add(modelMapper.map(object, ObjectDto.class)));
        return objectDtos;
    }

    @Override
    public ObjectDto create(ObjectDto objectDto) {
        ObjectEntity object = modelMapper.map(objectDto, ObjectEntity.class);

        ObjectEntity createdObject = objectRepository.save(object);
        return modelMapper.map(createdObject, ObjectDto.class);
    }

    @Override
    public ObjectDto update(ObjectDto objectDto) {
        ObjectEntity objectRequest = modelMapper.map(objectDto, ObjectEntity.class);
        ObjectEntity object = objectRepository.findById(objectRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Object", "id", String.valueOf(objectRequest.getId())));
        object.setOrganization(objectRequest.getOrganization());
        object.setName(objectRequest.getName());

        ObjectEntity createdObject = objectRepository.save(object);
        return modelMapper.map(createdObject, ObjectDto.class);
    }

    @Override
    public ObjectDto delete(int id) {
        ObjectEntity object = objectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object", "id", String.valueOf(id)));

        objectRepository.delete(object);
        return modelMapper.map(object, ObjectDto.class);
    }

}
