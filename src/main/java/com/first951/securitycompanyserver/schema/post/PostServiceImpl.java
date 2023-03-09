package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.mapper.MappingType;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public PostDto create(PostDto postDto) {
        try {
            Post postRequest = postMapper.toEntity(postDto, MappingType.DEFAULT);
            Post postResponse = postRepository.save(postRequest);
            return postMapper.toDto(postResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public PostDto read(long id) {
        Post postResponse = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пост с id=" + id + " не найден"));
        return postMapper.toDto(postResponse);
    }

    @Override
    public List<PostDto> search(PostDto filterDto, Long from, Integer size) {
        Post filter = postMapper.toEntity(filterDto, MappingType.FORCE);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Post> posts = postRepository.search(filter.getPlace(), filter.getName(), filter.getComment(),
                filter.getAddress(), pageable);
        return postMapper.toDtoList(posts);
    }

    @Override
    public PostDto update(long id, PostDto postDto) {
        if (postRepository.existsById(id)) {
            Post postRequest = postMapper.toEntity(postDto, MappingType.DEFAULT);
            postRequest.setId(id);

            Post postResponse = postRepository.save(postRequest);
            return postMapper.toDto(postResponse);
        } else {
            throw new NotFoundException("Пост с id=" + id + " не найден");
        }
    }

    @Override
    public void delete(long id) {
        if (postRepository.existsById(id)) {
            try {
                postRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new BadRequestException("Невозможно удалить пост с id= " + id + ". Нарушение целостности" +
                        " данных");
            }
        } else {
            throw new NotFoundException("Пост с id=" + id + " не найден");
        }
    }

}
