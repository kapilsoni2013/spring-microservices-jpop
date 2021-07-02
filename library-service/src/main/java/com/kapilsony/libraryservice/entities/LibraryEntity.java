package com.kapilsony.libraryservice.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_books")
@Data
@EntityListeners(AuditingEntityListener.class)
public class LibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private Long book_id;

    @CreatedDate
    @Column(name = "createTime",updatable = false,nullable = false)
    private Date createTime;
    @LastModifiedDate
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}
