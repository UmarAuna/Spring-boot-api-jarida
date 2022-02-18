package com.jarida.server.jaridaserver.spring_security_2.controller;

import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.CommentDtoTwos;
import com.jarida.server.jaridaserver.spring_security_2.service.CommentServiceTwos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v3")
@Validated
@Api(tags = "Rest APIs for Comment")
@SwaggerDefinition(tags = {
        @Tag(name = "Security API 3.1", description = "This is for getting Security API 3.1")
})
@CrossOrigin
public class CommentControllerTwos {

    private CommentServiceTwos commentService;

    public CommentControllerTwos(CommentServiceTwos commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create Comment REST API")
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDtoTwos> createComment(@PathVariable(value = "postId") long postId,
                                                        @Valid @RequestBody CommentDtoTwos commentDto) throws NoHandlerFoundException {
        try {
            return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
        } catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Get All Comments By Post ID REST API")
    @GetMapping("/post/{postId}/comments")
    public List<CommentDtoTwos> getCommentsByPostId(@PathVariable(value = "postId") Long postId) throws NoHandlerFoundException {
        try {
            return commentService.getCommentsByPostId(postId);
        }catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Get Single Comment By ID REST API")
    @GetMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDtoTwos> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "commentId") Long commentId) throws NoHandlerFoundException {
        try {
            CommentDtoTwos commentDto = commentService.getCommentById(postId, commentId);
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Update Comment By ID REST API")
    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDtoTwos> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDtoTwos commentDto) throws NoHandlerFoundException {
        try{
            CommentDtoTwos updatedComment = commentService.updateComment(postId, commentId, commentDto);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        }catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "Delete Comment By ID REST API")
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<ApiResponseTwos> deleteComment(@PathVariable(value = "postId") Long postId,
                                                         @PathVariable(value = "commentId") Long commentId) throws NoHandlerFoundException {
        try {
            commentService.deleteComment(postId, commentId);
            ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, "Comment deleted successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }
}
