package com.first951.securitycompanyserver.dutyregister;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DutyRegisterServiceImpl implements DutyRegisterService {

    private final DutyRegisterRepository dutyRegisterRepository;
    private final ModelMapper modelMapper;

    @Override
    public DutyRegisterDto get(int id) {
        DutyRegisterEntity dutyRegister = dutyRegisterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DutyRegister", "id", String.valueOf(id)));

        return modelMapper.map(dutyRegister, DutyRegisterDto.class);
    }

    @Override
    public List<DutyRegisterDto> getAll() {
        Iterable<DutyRegisterEntity> dutyRegisters = dutyRegisterRepository.findAll();
        List<DutyRegisterDto> dutyRegisterDtos = new ArrayList<>();

        dutyRegisters.forEach(dutyRegister -> dutyRegisterDtos.add(modelMapper.map(dutyRegister, DutyRegisterDto.class)));
        return dutyRegisterDtos;
    }

    @Override
    public DutyRegisterDto create(DutyRegisterDto dutyRegisterDto) {
        DutyRegisterEntity dutyRegister = modelMapper.map(dutyRegisterDto, DutyRegisterEntity.class);

        DutyRegisterEntity createdDutyRegister = dutyRegisterRepository.save(dutyRegister);
        return modelMapper.map(createdDutyRegister, DutyRegisterDto.class);
    }

    @Override
    public DutyRegisterDto update(DutyRegisterDto dutyRegisterDto) {
        DutyRegisterEntity dutyRegisterRequest = modelMapper.map(dutyRegisterDto, DutyRegisterEntity.class);
        DutyRegisterEntity dutyRegister = dutyRegisterRepository.findById(dutyRegisterRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("DutyRegister", "id", String.valueOf(dutyRegisterRequest.getId())));
        dutyRegister.setPost(dutyRegisterRequest.getPost());
        dutyRegister.setGuard(dutyRegisterRequest.getGuard());
        dutyRegister.setBegin(dutyRegisterRequest.getBegin());
        dutyRegister.setEnd(dutyRegisterRequest.getEnd());

        DutyRegisterEntity createdDutyRegister = dutyRegisterRepository.save(dutyRegister);
        return modelMapper.map(createdDutyRegister, DutyRegisterDto.class);
    }

    @Override
    public DutyRegisterDto delete(int id) {
        DutyRegisterEntity dutyRegister = dutyRegisterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DutyRegister", "id", String.valueOf(id)));

        dutyRegisterRepository.delete(dutyRegister);
        return modelMapper.map(dutyRegister, DutyRegisterDto.class);
    }

}
