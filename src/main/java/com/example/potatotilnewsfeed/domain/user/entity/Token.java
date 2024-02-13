package com.example.potatotilnewsfeed.domain.user.entity;

import com.example.potatotilnewsfeed.global.entity.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TB_Token")
@NoArgsConstructor
public class Token extends Timestamped {

    @Id
    private String accessToken;

    @Column(nullable = false)
    private Boolean isExpired;

    public Token(String tokenValue, boolean isExpired) {
        this.accessToken = tokenValue;
        this.isExpired = isExpired;
    }
}
