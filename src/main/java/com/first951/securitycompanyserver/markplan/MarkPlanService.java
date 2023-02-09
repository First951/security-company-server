package com.first951.securitycompanyserver.markplan;

import java.util.List;

public interface MarkPlanService {

    MarkPlanDto get(int id);

    List<MarkPlanDto> getAll();

    MarkPlanDto create(MarkPlanDto markPlanDto);

    MarkPlanDto update(int id, MarkPlanDto markPlanDto);

    MarkPlanDto deletePost(int id);

}
