package com.jarida.server.jaridaserver.spring_security_2.service;

import com.jarida.server.jaridaserver.spring_security_2.payload.CommentDtoTwos;

import java.util.List;

public interface CommentServiceTwos {
    CommentDtoTwos createComment(long postId, CommentDtoTwos commentDto);

    List<CommentDtoTwos> getCommentsByPostId(long postId);

    CommentDtoTwos getCommentById(Long postId, Long commentId);

    CommentDtoTwos updateComment(Long postId, long commentId, CommentDtoTwos commentRequest);

    void deleteComment(Long postId, Long commentId);
}
