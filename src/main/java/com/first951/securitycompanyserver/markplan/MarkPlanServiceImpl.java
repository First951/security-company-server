package com.first951.securitycompanyserver.markplan;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkPlanServiceImpl implements MarkPlanService {

    private final MarkPlanRepository markPlanRepository;
    private final ModelMapper modelMapper;

    @Override
    public MarkPlanDto get(int id) {
        MarkPlanEntity markPlan = markPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkPlan", "id", String.valueOf(id)));

        return modelMapper.map(markPlan, MarkPlanDto.class);
    }

    @Override
    public List<MarkPlanDto> getAll() {
        Iterable<MarkPlanEntity> markPlans = markPlanRepository.findAll();
        List<MarkPlanDto> markPlanDtos = new ArrayList<>();

        markPlans.forEach(markPlan -> markPlanDtos.add(modelMapper.map(markPlan, MarkPlanDto.class)));
        return markPlanDtos;
    }

    @Override
    public MarkPlanDto create(MarkPlanDto markPlanDto) {
        MarkPlanEntity markPlan = modelMapper.map(markPlanDto, MarkPlanEntity.class);

        MarkPlanEntity createdMarkPlan = markPlanRepository.save(markPlan);
        return modelMapper.map(createdMarkPlan, MarkPlanDto.class);
    }

    @Override
    public MarkPlanDto update(MarkPlanDto markPlanDto) {
        MarkPlanEntity markPlanRequest = modelMapper.map(markPlanDto, MarkPlanEntity.class);
        MarkPlanEntity markPlan = markPlanRepository.findById(markPlanRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("MarkPlan", "id", String.valueOf(markPlanRequest.getId())));
        markPlan.setSchedule(markPlanRequest.getSchedule());
        markPlan.setTimestamp(markPlanRequest.getTimestamp());

        MarkPlanEntity createdMarkPlan = markPlanRepository.save(markPlan);
        return modelMapper.map(createdMarkPlan, MarkPlanDto.class);
    }

    @Override
    public MarkPlanDto delete(int id) {
        MarkPlanEntity markPlan = markPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkPlan", "id", String.valueOf(id)));

        markPlanRepository.delete(markPlan);
        return modelMapper.map(markPlan, MarkPlanDto.class);
    }

}
