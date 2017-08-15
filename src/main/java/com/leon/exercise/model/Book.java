package com.leon.exercise.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String title;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String author;

    private String description;

    @CreatedDate
    @Column(nullable = false)
    private long createdAt;

    @LastModifiedDate
    private long updatedAt;

    @Builder
    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

}
