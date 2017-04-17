package com.bookstore.service.impl;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void createPasswordResetToken(final User user, final String token) {
        final PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
}
