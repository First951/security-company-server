package com.first951.securitycompanyserver.post;

import com.first951.securitycompanyserver.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


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

        posts.forEach(post -> modelMapper.map(post, PostDto.class));
        return postDtos;
    }

    @Override
    public PostDto create(PostDto postDto) {
        PostEntity post = modelMapper.map(postDto, PostEntity.class);

        PostEntity createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto update(int id, PostDto postDto) {
        PostEntity postRequest = modelMapper.map(postDto, PostEntity.class);
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        post.setObject(postRequest.getObject());
        post.setName(postRequest.getName());
        post.setComment(postRequest.getComment());
        post.setAddress(postRequest.getAddress());

        PostEntity createdPost = postRepository.save(post);
        return modelMapper.map(createdPost, PostDto.class);
    }

    @Override
    public PostDto deletePost(int id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));

        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }
}
