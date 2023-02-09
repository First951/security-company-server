package com.first951.securitycompanyserver.object;

import java.util.List;

public interface ObjectService {

    ObjectDto get(int id);

    List<ObjectDto> getAll();

    ObjectDto create(ObjectDto objectDto);

    ObjectDto update(int id, ObjectDto objectDto);

    ObjectDto deletePost(int id);

}
