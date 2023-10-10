package com.example.concurrency.ui;

import com.example.concurrency.application.dto.PostCreate.Request;
import com.example.concurrency.application.dto.PostCreate.Response;
import com.example.concurrency.application.dto.PostResponse;
import com.example.concurrency.application.usecase.PostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostRestController {
    private final PostUseCase postUseCase;

    @PostMapping
    @ResponseStatus(OK)
    public Response create(
            @RequestBody Request request
    ) {
        return postUseCase.create(request);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(OK)
    public PostResponse view(@PathVariable Long postId) {
        return postUseCase.getById(postId);
    }
}
