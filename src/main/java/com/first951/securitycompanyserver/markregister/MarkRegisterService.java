package com.first951.securitycompanyserver.markregister;

import java.util.List;

public interface MarkRegisterService {

    MarkRegisterDto get(int id);

    List<MarkRegisterDto> getAll();

    MarkRegisterDto create(MarkRegisterDto markRegisterDto);

    MarkRegisterDto update(int id, MarkRegisterDto markRegisterDto);

    MarkRegisterDto deletePost(int id);

}
