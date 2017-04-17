package com.bookstore.domain.security;

import com.bookstore.domain.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class PasswordResetToken {
    private static final int EXPATRIATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public PasswordResetToken(final String token, final User user) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPATRIATION);
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPATRIATION);
    }

    public static int getEXPATRIATION() {
        return EXPATRIATION;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id=" + id +
                ", token='" + token +
                ", user=" + user +
                ", expiryDate=" + expiryDate +
                '}';
    }

    private Date calculateExpiryDate(final int expiryTimeInMinute) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiryTimeInMinute);
        return new Date(calendar.getTime().getTime());
    }
}
