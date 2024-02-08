package com.example.potatotilnewsfeed.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_USER")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String introduce;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.role = UserRoleEnum.USER;
    }

    public void update(String nickname,String password, String introduce) {
        this.nickname = nickname;
        this.password = password;
        this.introduce = introduce;

    }
}