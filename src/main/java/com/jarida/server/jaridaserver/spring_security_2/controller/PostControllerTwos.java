package com.jarida.server.jaridaserver.spring_security_2.controller;

import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.PostDtoTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.PostResponseTwos;
import com.jarida.server.jaridaserver.spring_security_2.service.PostServiceTwos;
import com.jarida.server.jaridaserver.spring_security_2.utils.AppConstantsTwo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v3")
@Validated
@Api(tags = "Rest APIs for Post")
@SwaggerDefinition(tags = {
        @Tag(name = "Security API 3.1", description = "This is for getting Security API 3.1")
})
@CrossOrigin
public class PostControllerTwos {

    private PostServiceTwos postService;



    public PostControllerTwos(PostServiceTwos postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // create blog post rest api
    @PostMapping("/user/{userId}/post")
    public ResponseEntity<PostDtoTwos> createPost(
            @PathVariable(value = "userId") Long userId,
            @Valid @RequestBody PostDtoTwos postDto) throws NoHandlerFoundException {

        try {

            return new ResponseEntity<>(postService.createPost(userId, postDto), HttpStatus.CREATED);

        } catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }

        //Before
        // return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Posts REST API")
    // get all posts rest api
    @GetMapping("/user/{userId}/posts")
    public PostResponseTwos getAllPostsByUserId(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "pageNo", defaultValue = AppConstantsTwo.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstantsTwo.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstantsTwo.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstantsTwo.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) throws NoHandlerFoundException {
        try{

            return postService.getAllPostsByUserId(userId,pageNo, pageSize, sortBy, sortDir);

        } catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Get Post By Id REST API")
    // get post by id
    @GetMapping(value = "/user/{userId}/post/{postId}")
    public ResponseEntity<PostDtoTwos> getPostByIdV1(
            @PathVariable(name = "userId") long userId,
            @PathVariable(name = "postId") long id) throws NoHandlerFoundException {

        try{

        PostDtoTwos postDtoTwos = postService.getPostById(userId, id);
        return new ResponseEntity<>(postDtoTwos, HttpStatus.OK);

        } catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // update post by id rest api
    @PutMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<PostDtoTwos> updatePost(
            @Valid @RequestBody PostDtoTwos postDto,
            @PathVariable(name = "userId") long userId,
            @PathVariable(name = "postId") long id) throws NoHandlerFoundException {

        try {
            PostDtoTwos postResponse = postService.updatePost(userId, id, postDto);

            return new ResponseEntity<>(postResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete post rest api
    @DeleteMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<ApiResponseTwos> deletePost(
            @PathVariable(name = "userId") long userId,
            @PathVariable(name = "postId") long id) throws NoHandlerFoundException {

        try {

            postService.deletePostById(userId, id);
            ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE,"Post entity deleted successfully.");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }







}
