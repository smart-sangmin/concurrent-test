package com.example.concurrency.application.service;

import com.example.concurrency.application.dto.PostCreate.Request;
import com.example.concurrency.application.dto.PostCreate.Response;
import com.example.concurrency.application.dto.PostResponse;
import com.example.concurrency.application.usecase.PostUseCase;
import com.example.concurrency.domain.Post;
import com.example.concurrency.infra.PostJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService implements PostUseCase {
    private final PostJpaRepository postJpaRepository;

    @Override
    @Transactional
    public Response create(Request request) {
        Post savedPost = postJpaRepository.save(
                request.toEntity());
        return Response.from(savedPost);
    }

    @Override
    @Transactional
    public PostResponse getById(Long postId) {
        Post post = postJpaRepository.findById(postId)
                .orElseThrow(EntityNotFoundException::new);
        post.increaseViewCount();
        return PostResponse.from(post);
    }
}
