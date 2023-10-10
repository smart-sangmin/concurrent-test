package com.example.concurrency.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private static final int MAX_TITLE_LENGTH = 20;
    private static final int MAX_CONTENTS_LENGTH = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(nullable = false, length = MAX_CONTENTS_LENGTH)
    private String contents;

    private int viewCount;

    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.viewCount = 0;
    }

    public void increaseViewCount() {
        viewCount++;
    }
}
