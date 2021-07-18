package com.jarida.server.jaridaserver.jarida_v2.controller;

import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.jarida_v2.model.Post;
import com.jarida.server.jaridaserver.jarida_v2.repository.PostRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
@Api(tags = "Jarida Post API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Jarida-V2", description = "This is for getting Jarida Post v2")
})
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> getAllPosts(/*@PageableDefault(size = 20)*/ Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post>getPostById(@PathVariable(value = "postId")Long postId)throws ResourceNotFoundException {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found :: " + postId));
        return ResponseEntity.ok().body(post);

    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public Post createPost(@Valid @RequestBody /*@FieldValue */Post post){
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable(value = "postId") Long postId, @Valid /*@FieldValue*/  @RequestBody Post postRequest){
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setAuthor(postRequest.getAuthor());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @DeleteMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> deletePost(@PathVariable(value = "postId") Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found :: " + postId));

        postRepository.delete(post);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }

}
