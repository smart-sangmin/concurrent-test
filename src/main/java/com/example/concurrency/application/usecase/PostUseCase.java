package com.example.concurrency.application.usecase;

import com.example.concurrency.application.dto.PostCreate;
import com.example.concurrency.application.dto.PostResponse;

public interface PostUseCase {
    PostCreate.Response create(PostCreate.Request request);

    PostResponse getById(Long postId);
}
