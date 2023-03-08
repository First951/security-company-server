package com.first951.securitycompanyserver.schema.markplan;

import java.util.List;

public interface MarkPlanService {

    MarkPlanDto get(int id);

    List<MarkPlanDto> getAll();

    MarkPlanDto create(MarkPlanDto markPlanDto);

    MarkPlanDto update(MarkPlanDto markPlanDto);

    MarkPlanDto delete(int id);

}
