package com.cheapflights.tickets.service;

import com.cheapflights.tickets.domain.dto.CommentDTO;
import com.cheapflights.tickets.domain.model.Comment;
import com.cheapflights.tickets.repository.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public CommentDTO toDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor().getId())
                .city(comment.getCity().getId())
                .timestamp(comment.getTimestamp())
                .build();
    }

    public List<CommentDTO> toDTO(List<Comment> comments) {
        return comments.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
