package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.exception.BadRequestException;
import com.first951.securitycompanyserver.exception.ConflictException;
import com.first951.securitycompanyserver.exception.NotFoundException;
import com.first951.securitycompanyserver.page.OffsetBasedPage;
import com.first951.securitycompanyserver.schema.organization.OrganizationDto;
import com.first951.securitycompanyserver.schema.organization.OrganizationMapper;
import com.first951.securitycompanyserver.schema.organization.OrganizationService;
import com.first951.securitycompanyserver.schema.place.PlaceDto;
import com.first951.securitycompanyserver.schema.place.PlaceMapper;
import com.first951.securitycompanyserver.schema.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PlaceService placeService;
    private final PlaceMapper placeMapper;
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    @Override
    public PostDto create(PostDto postDto) {
        try {
            Post postRequest = postMapper.toEntity(postDto);
            PlaceDto placeDto = placeService.read(postDto.getPlaceId());
            postRequest.setPlace(placeMapper.toEntity(placeDto));
            OrganizationDto organizationDto = organizationService.read(postDto.getOrganizationId());
            postRequest.setOrganization(organizationMapper.toEntity(organizationDto));

            Post postResponse = postRepository.save(postRequest);
            return toDto(postResponse);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Нарушение целостности данных");
        }
    }

    @Override
    public PostDto read(long id) {
        Post postResponse = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пост с id=" + id + " не найден"));
        return toDto(postResponse);
    }

    @Override
    public List<PostDto> search(PostDto filterDto, Long from, Integer size) {
        Post filter = postMapper.toEntity(filterDto);
        Pageable pageable = new OffsetBasedPage(from, size);

        List<Post> posts = postRepository.search(filter.getPlace(), filter.getName(), filter.getComment(), pageable);
        return toDtoList(posts);
    }

    @Override
    public PostDto update(long id, PostDto postDto) {
        if (postRepository.existsById(id)) {
            Post postRequest = postMapper.toEntity(postDto);
            postRequest.setId(id);
            PlaceDto placeDto = placeService.read(postDto.getPlaceId());
            postRequest.setPlace(placeMapper.toEntity(placeDto));
            OrganizationDto organizationDto = organizationService.read(postDto.getOrganizationId());
            postRequest.setOrganization(organizationMapper.toEntity(organizationDto));

            Post postResponse = postRepository.save(postRequest);
            return toDto(postResponse);
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

    private PostDto toDto(Post post) {
        PostDto postDto = postMapper.toDto(post);
        postDto.setPlaceDto(placeMapper.toDto(post.getPlace()));
        postDto.setOrganizationDto(organizationMapper.toDto(post.getOrganization()));
        return postDto;
    }

    private List<PostDto> toDtoList(List<Post> posts) {
        List<PostDto> response = new ArrayList<>();
        posts.forEach(post -> response.add(toDto(post)));
        return response;
    }

}
