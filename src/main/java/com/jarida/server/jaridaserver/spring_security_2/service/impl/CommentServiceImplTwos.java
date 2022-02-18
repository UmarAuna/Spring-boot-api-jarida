package com.jarida.server.jaridaserver.spring_security_2.service.impl;

import com.jarida.server.jaridaserver.spring_security_2.entity.CommentTwos;
import com.jarida.server.jaridaserver.spring_security_2.entity.PostTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.BlogAPIExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.ResourceNotFoundExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.CommentDtoTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.CommentRepositoryTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.PostRepositoryTwos;
import com.jarida.server.jaridaserver.spring_security_2.service.CommentServiceTwos;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplTwos implements CommentServiceTwos {

    private CommentRepositoryTwos commentRepository;

    private PostRepositoryTwos postRepository;

    private ModelMapper mapper;

    public CommentServiceImplTwos(CommentRepositoryTwos commentRepository, PostRepositoryTwos postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDtoTwos createComment(long postId, CommentDtoTwos commentDto) {

        CommentTwos comment = mapToEntity(commentDto);

        // retrieve post entity by id
        PostTwos post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("Post", "id", postId));

        // set post to comment entity
        comment.setPostTwos(post);

        // comment entity to DB
        CommentTwos newComment =  commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDtoTwos> getCommentsByPostId(long postId) {


        PostTwos post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundExceptionTwos("Post", "id", postId));

        // retrieve comments by postId
        List<CommentTwos> comments = commentRepository.findByPostTwosId(post.getId());

        // convert list of comment entities to list of comment dto's
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDtoTwos getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        PostTwos post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("Post", "id", postId));

        // retrieve comment by id

        CommentTwos comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundExceptionTwos("Comment", "id", commentId));

        String comments = String.valueOf(comment.getPostTwos().getId());

        if(!comments.equals(String.valueOf(post.getId()))){
            throw new BlogAPIExceptionTwos(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDtoTwos updateComment(Long postId, long commentId, CommentDtoTwos commentRequest) {
        // retrieve post entity by id
        PostTwos post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("Post", "id", postId));

        // retrieve comment by id
        CommentTwos comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundExceptionTwos("Comment", "id", commentId));

        String comments = String.valueOf(comment.getPostTwos().getId());

        if(!comments.equals(String.valueOf(post.getId()))){
            throw new BlogAPIExceptionTwos(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        CommentTwos updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        PostTwos post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundExceptionTwos("Post", "id", postId));

        // retrieve comment by id
        CommentTwos comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundExceptionTwos("Comment", "id", commentId));

        String comments = String.valueOf(comment.getPostTwos().getId());

        if(!comments.equals(String.valueOf(post.getId()))){
            throw new BlogAPIExceptionTwos(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }




    private CommentDtoTwos mapToDTO(CommentTwos comment){
        CommentDtoTwos commentDto = mapper.map(comment, CommentDtoTwos.class);

//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return  commentDto;
    }

    private CommentTwos mapToEntity(CommentDtoTwos commentDto){
        CommentTwos comment = mapper.map(commentDto, CommentTwos.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return  comment;
    }


}
