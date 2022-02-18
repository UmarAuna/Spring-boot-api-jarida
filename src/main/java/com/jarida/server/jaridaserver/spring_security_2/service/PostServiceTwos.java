package com.jarida.server.jaridaserver.spring_security_2.service;

import com.jarida.server.jaridaserver.spring_security_2.payload.PostDtoTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.PostResponseTwos;

public interface PostServiceTwos {
    PostDtoTwos createPost(long userId,PostDtoTwos postDto);

    PostResponseTwos getAllPostsByUserId(long userId ,int pageNo, int pageSize, String sortBy, String sortDir);

    PostDtoTwos getPostById(Long userId,long id);

    PostDtoTwos updatePost( Long userId, long id, PostDtoTwos postDto);

    void deletePostById(Long userId, long id);

}
