package com.first951.securitycompanyserver.dutyregister;

import java.util.List;

public interface DutyRegisterService {

    DutyRegisterDto get(int id);

    List<DutyRegisterDto> getAll();

    DutyRegisterDto create(DutyRegisterDto dutyRegisterDto);

    DutyRegisterDto update(int id, DutyRegisterDto dutyRegisterDto);

    DutyRegisterDto deletePost(int id);

}
