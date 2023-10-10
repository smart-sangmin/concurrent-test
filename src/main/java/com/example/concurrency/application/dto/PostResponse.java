package com.example.concurrency.application.dto;

import com.example.concurrency.domain.Post;

public record PostResponse(
        long id,
        String title,
        String contents,
        int viewCount
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContents(),
                post.getViewCount()
        );
    }
}
