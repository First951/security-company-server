package com.first951.securitycompanyserver.post;

import java.util.List;

public interface PostService {

    PostDto get(int id);

    List<PostDto> getAll();

    PostDto create(PostDto postDto);

    PostDto update(int id, PostDto postDto);

    PostDto deletePost(int id);

}
