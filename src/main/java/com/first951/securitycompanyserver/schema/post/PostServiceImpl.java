package com.first951.securitycompanyserver.schema.post;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto get(int id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));

        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAll() {
        Iterable<PostEntity> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();

        posts.forEach(post -> postDtos.add(modelMapper.map(post, PostDto.class)));
        return postDtos;
    }

    @Override
    public PostDto create(PostDto postDto) {
        PostEntity post = modelMapper.map(postDto, PostEntity.class);

        PostEntity createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto update(PostDto postDto) {
        PostEntity postRequest = modelMapper.map(postDto, PostEntity.class);
        PostEntity post = postRepository.findById(postRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postRequest.getId())));
        post.setPlace(postRequest.getPlace());
        post.setName(postRequest.getName());
        post.setComment(postRequest.getComment());
        post.setAddress(postRequest.getAddress());

        PostEntity createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto delete(int id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));

        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

}
