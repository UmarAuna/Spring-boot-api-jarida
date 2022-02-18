package com.jarida.server.jaridaserver.spring_security_2.service.impl;

import com.jarida.server.jaridaserver.spring_security_2.entity.PostTwos;
import com.jarida.server.jaridaserver.spring_security_2.entity.UserTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.BlogAPIExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.ResourceNotFoundExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.PostDtoTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.PostResponseTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.PostRepositoryTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.UserRepositoryTwos;
import com.jarida.server.jaridaserver.spring_security_2.service.PostServiceTwos;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplTwos implements PostServiceTwos {

    private PostRepositoryTwos postRepositoryTwos;

    private UserRepositoryTwos userRepositoryTwos;

    private ModelMapper mapper;

    public PostServiceImplTwos(PostRepositoryTwos postRepositoryTwos, UserRepositoryTwos userRepositoryTwos ,ModelMapper mapper) {
        this.postRepositoryTwos = postRepositoryTwos;
        this.userRepositoryTwos = userRepositoryTwos;
        this.mapper = mapper;
    }


    @Override
    public PostDtoTwos createPost(long userId , PostDtoTwos postDto) {

        //after
        UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("User", "id", userId));

        // convert DTO to entity
        PostTwos post = mapToEntity(postDto);

        //after
        post.setUserTwos(user);

        //after
        PostTwos postTwos = postRepositoryTwos.save(post);

        //after
        return mapToDTO(postTwos);

        //Before
        //PostTwos newPost = postRepositoryTwos.save(post);
        /*// convert entity to DTO
        PostDtoTwos postResponse = mapToDTO(newPost);

        return postResponse;*/
    }

    @Override
    public PostResponseTwos getAllPostsByUserId(long userId ,int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //after
        UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("User", "id", userId));


        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<PostTwos> posts = postRepositoryTwos.findByUserTwosId(user.getId(),pageable);

        // get content for page object
        List<PostTwos> listOfPosts = posts.getContent();

        List<PostDtoTwos> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponseTwos postResponse = new PostResponseTwos();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDtoTwos getPostById(Long userId, long id) {

        //after
        UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("User", "id", userId));


        PostTwos post = postRepositoryTwos.findById(id).orElseThrow(() -> new ResourceNotFoundExceptionTwos("Post", "id", id));


        String posts = String.valueOf(post.getUserTwos().getId());

        if(!posts.equals(String.valueOf(user.getId()))){
            throw new BlogAPIExceptionTwos(HttpStatus.BAD_REQUEST, "Post does not belong to user");
        }

        return mapToDTO(post);
    }

    @Override
    public PostDtoTwos updatePost(Long userId ,long id, PostDtoTwos postDto) {

        //after
        UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("User", "id", userId));

        // get post by id from the database
        PostTwos post = postRepositoryTwos.findById(id).orElseThrow(() -> new ResourceNotFoundExceptionTwos("Post", "id", id));

        String posts = String.valueOf(post.getUserTwos().getId());

        if(!posts.equals(String.valueOf(user.getId()))){
            throw new BlogAPIExceptionTwos(HttpStatus.BAD_REQUEST, "Post does not belong to user");
        }

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        PostTwos updatedPost = postRepositoryTwos.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long userId, long id) {

        //after
        UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("User", "id", userId));

        // get post by id from the database
        PostTwos post = postRepositoryTwos.findById(id).orElseThrow(() -> new ResourceNotFoundExceptionTwos("Post", "id", id));

        String posts = String.valueOf(post.getUserTwos().getId());

        if(!posts.equals(String.valueOf(user.getId()))){
            throw new BlogAPIExceptionTwos(HttpStatus.BAD_REQUEST, "Post does not belong to user");
        }

        postRepositoryTwos.delete(post);
    }

    // convert Entity into DTO
    private PostDtoTwos mapToDTO(PostTwos post){
        PostDtoTwos postDto = mapper.map(post, PostDtoTwos.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert DTO to entity
    private PostTwos mapToEntity(PostDtoTwos postDto){
        PostTwos post = mapper.map(postDto, PostTwos.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
}
