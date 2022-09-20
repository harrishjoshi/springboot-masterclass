package com.haxsh.spring.security.client.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_verification_token")
@NoArgsConstructor
public class VerificationToken {

    private static final int EXPIRATION_TIME_IN_MIN = 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_VERIFICATION_TOKEN")
    )
    private User user;

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    private Date calculateExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME_IN_MIN);

        return new Date(calendar.getTime().getTime());
    }
}
