package com.example.concurrency.application.dto;

import com.example.concurrency.domain.Post;

public sealed interface PostCreate permits PostCreate.Request, PostCreate.Response {

    record Request(
            String title,
            String contents
    ) implements PostCreate {

        public Post toEntity() {
            return new Post(title, contents);
        }
    }

    record Response(
            Long id
    ) implements PostCreate {

        public static PostCreate.Response from(Post post) {
            return new PostCreate.Response(
                    post.getId()
            );
        }
    }
}
