package com.jarida.server.jaridaserver.jarida_v2.controller;

import com.jarida.server.jaridaserver.jarida_v2.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.jarida_v2.model.Post;
import com.jarida.server.jaridaserver.jarida_v2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post>getPostById(@PathVariable(value = "postId")Long postId)throws ResourceNotFoundException{
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found :: " + postId));
        return ResponseEntity.ok().body(post);

    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable(value = "postId") Long postId, @Valid @RequestBody Post postRequest){
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setAuthor(postRequest.getAuthor());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @DeleteMapping("/posts/{postId}")
    public Map<String, Boolean> deletePost(@PathVariable(value = "postId") Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found :: " + postId));

        postRepository.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

}
