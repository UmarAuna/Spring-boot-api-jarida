package com.jarida.server.jaridaserver.jarida_v2.controller;

import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.jarida_v2.model.Comment;
import com.jarida.server.jaridaserver.jarida_v2.repository.CommentRepository;
import com.jarida.server.jaridaserver.jarida_v2.repository.PostRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
@Validated
@Api(tags = "Jarida Comment API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Jarida-V2", description = "This is for getting Jarida Comment v2")
})
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Page<Comment> getAllCommentsByPost(@PathVariable(value = "postId") Long postId,
                                                Pageable pageable){

        if(!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("Post Id not found");
        }

        return commentRepository.findByPostId(postId, pageable);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "commentId") Long commentId){
        if(!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("Post Id not found");
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + commentId));
        return ResponseEntity.ok().body(comment);
    }



    @PostMapping("/posts/{postId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Comment createComment(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody Comment comment){
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(()-> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@PathVariable(value = "postId") Long postId,
                                 @PathVariable(value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest){

        if (!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }


        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment Id " + commentId + " not found"));
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> deleteComment(@PathVariable(value = "postId") Long postId,
                                              @PathVariable(value = "commentId") Long commentId){

        if(!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("Post Id not found");
        }

        if(!commentRepository.existsById(commentId)){
            throw new ResourceNotFoundException("Comment Id not found");
        }

        this.commentRepository.deleteById(commentId);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;


       /* return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
          commentRepository.delete(comment);
          return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId ));*/
    }


}
