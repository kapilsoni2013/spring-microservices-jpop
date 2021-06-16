package com.kapilsony.bookservice.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
@Data
@EntityListeners(AuditingEntityListener.class)
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private Float price;
    private String author;
    private String category;
    private String description;

    @CreatedDate
    @Column(name = "createTime",updatable = false,nullable = false)
    private Date createTime;
    @LastModifiedDate
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}
