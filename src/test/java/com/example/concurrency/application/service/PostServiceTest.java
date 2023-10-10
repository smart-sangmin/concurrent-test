package com.example.concurrency.application.service;

import com.example.concurrency.domain.Post;
import com.example.concurrency.infra.PostJpaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConcurrentTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostJpaRepository postJpaRepository;

    @Autowired
    private EntityManager em;

    private Post post;

    @BeforeEach
    void setUp() {
        post = new Post("title", "contents");
        postJpaRepository.saveAndFlush(post);
    }

    @Test
    void concurrent() throws InterruptedException {
        Long id = post.getId();

        int numberOfThread = 100;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThread);
        CountDownLatch latch = new CountDownLatch(numberOfThread);

        for (int i = 0; i < numberOfThread; i++) {
            try {
                service.execute(() -> postService.getById(id));
            } finally {
                latch.countDown();
            }
        }
        latch.await();

        Post p = postJpaRepository.findById(id)
                .orElseThrow();
        assertThat(p.getViewCount()).isEqualTo(numberOfThread);
    }
}
