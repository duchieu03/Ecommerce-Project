package com.ecommerceproject.ecommerceproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="verification_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "token", nullable = false, unique = true)
    private String token;
    private Timestamp expiredTime;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
