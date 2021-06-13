package com.kapilsony.userservice.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private Integer age;
    private String firstName;
    private String lastName;
    private String address;

    @CreatedDate
    @Column(name = "createTime",updatable = false,nullable = false)
    private Date createTime;
    @LastModifiedDate
    @Column(name = "updateTime",nullable = false)
    private Date updateTime;
}
