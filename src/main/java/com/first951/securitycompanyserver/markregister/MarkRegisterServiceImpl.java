package com.first951.securitycompanyserver.markregister;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkRegisterServiceImpl implements MarkRegisterService {

    private final MarkRegisterRepository markRegisterRepository;
    private final ModelMapper modelMapper;

    @Override
    public MarkRegisterDto get(int id) {
        MarkRegisterEntity markRegister = markRegisterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkRegister", "id", String.valueOf(id)));

        return modelMapper.map(markRegister, MarkRegisterDto.class);
    }

    @Override
    public List<MarkRegisterDto> getAll() {
        Iterable<MarkRegisterEntity> markRegisters = markRegisterRepository.findAll();
        List<MarkRegisterDto> markRegisterDtos = new ArrayList<>();

        markRegisters.forEach(markRegister -> modelMapper.map(markRegister, MarkRegisterDto.class));
        return markRegisterDtos;
    }

    @Override
    public MarkRegisterDto create(MarkRegisterDto markRegisterDto) {
        MarkRegisterEntity markRegister = modelMapper.map(markRegisterDto, MarkRegisterEntity.class);

        MarkRegisterEntity createdMarkRegister = markRegisterRepository.save(markRegister);
        return modelMapper.map(createdMarkRegister, MarkRegisterDto.class);
    }

    @Override
    public MarkRegisterDto update(int id, MarkRegisterDto markRegisterDto) {
        MarkRegisterEntity markRegisterRequest = modelMapper.map(markRegisterDto, MarkRegisterEntity.class);
        MarkRegisterEntity markRegister = markRegisterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkRegister", "id", String.valueOf(id)));
        markRegister.setMarkPlan(markRegisterRequest.getMarkPlan());
        markRegister.setTimestamp(markRegisterRequest.getTimestamp());
        markRegister.setType(markRegisterRequest.getType());
        markRegister.setComment(markRegisterRequest.getComment());

        MarkRegisterEntity createdMarkRegister = markRegisterRepository.save(markRegister);
        return modelMapper.map(createdMarkRegister, MarkRegisterDto.class);
    }

    @Override
    public MarkRegisterDto deletePost(int id) {
        MarkRegisterEntity markRegister = markRegisterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MarkRegister", "id", String.valueOf(id)));

        markRegisterRepository.delete(markRegister);
        return modelMapper.map(markRegister, MarkRegisterDto.class);
    }
}
