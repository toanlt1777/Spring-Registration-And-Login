package com.toanlt.springJWT.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ConfirmationToken {

    public ConfirmationToken(String token, LocalDateTime createAt, LocalDateTime expiresAt, AppUser appUser) {
        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }

    @Id
    @SequenceGenerator(name = "confirmation_token_sequence", sequenceName = "confirmation_token_sequence", allocationSize = 1)
    @GeneratedValue(generator = "confirmation_token_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String token;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmAt;
    @ManyToOne
    @JoinColumn(nullable = false,
            name = "app_user_id")
    private AppUser appUser;
}
