package com.nhnacademy.jdbc.board.comment.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultCommentServiceTest {
    private CommentService commentService;
    private Comment comment;

    @BeforeEach
    void setUp() {
        commentService = mock(DefaultCommentService.class);
        comment = new Comment(1L, 1L, 1L, "테스트");
    }

    @Test
    void findComment() {
        when(commentService.findComment(1L)).thenReturn(Optional.of(comment));
        Optional<Comment> testComment = commentService.findComment(1L);
        assertThat(testComment).isEqualTo(Optional.of(comment));
        verify(commentService, times(1)).findComment(any());
    }

    @Test
    void findAllComment() {
        when(commentService.findAllComment()).thenReturn(List.of(comment));
        List<Comment> allComment = commentService.findAllComment();
        assertThat(allComment).isNotEmpty();
        assertThat(allComment.get(0)).isEqualTo(comment);
        assertThat(allComment.get(0).getCommentNo()).isEqualTo(1L);

        verify(commentService, times(1)).findAllComment();
    }

    @Test
    void insertComment() {
        when(commentService.insertComment(any())).thenReturn(comment.getCommentNo());
        Long getNo = commentService.insertComment(comment);
        assertThat(getNo).isEqualTo(1L);
        verify(commentService, times(1)).insertComment(any());
    }

    @Test
    void removeComment() {
        when(commentService.removeComment(any())).thenReturn(comment.getCommentNo());
        Long removeComment = commentService.removeComment(1L);
        assertThat(removeComment).isEqualTo(removeComment);
        verify(commentService, times(1)).removeComment(removeComment);
    }

    @Test
    void modifyComment() {
        Comment testComment = new Comment(1L, 1L, 1L, "테스트 2");
        when(commentService.modifyComment(any(),any(),any())).thenReturn(Optional.of(testComment));
        commentService.modifyComment(any(), any(), any());
        verify(commentService, times(1)).modifyComment(any(),any(),any());
    }
}