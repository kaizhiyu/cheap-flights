package com.cheapflights.tickets.service;

import com.cheapflights.tickets.domain.dto.CommentDTO;
import com.cheapflights.tickets.domain.model.City;
import com.cheapflights.tickets.domain.model.Comment;
import com.cheapflights.tickets.domain.model.User;
import com.cheapflights.tickets.repository.CityRepository;
import com.cheapflights.tickets.repository.CommentRepository;
import com.cheapflights.tickets.repository.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
public class CommentService implements CrudService<Comment> {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, UserRepository userRepository, CityRepository cityRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
    }

    public CommentDTO save(CommentDTO commentDTO, Long userId) {
        Optional<City> city = cityRepository.findById(commentDTO.getCity());
        if (city.isEmpty()) {
            throw new EntityNotFoundException(String.format("City with id [%d] couldn't be found.", commentDTO.getCity()));
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new EntityNotFoundException(String.format("User with id [%d] couldn't be found.", userId));
        }

        Comment comment = Comment.builder()
                .author(user.get())
                .text(commentDTO.getText())
                .city(city.get())
                .timestamp(commentDTO.getTimestamp())
                .build();
        return commentMapper.toDTO(commentRepository.save(comment));
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Collection<Comment> findAll() {
        return IteratorUtils.toList(commentRepository.findAll().iterator());
    }
}