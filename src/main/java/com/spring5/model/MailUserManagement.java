/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spring5.model;

import com.spring5.model.users.*;
import com.spring5.service.MailUserService;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Domain service to register {@link User}s in the system.
 *
 * @author Oliver Gierke
 */
@Transactional
@Service
@RequiredArgsConstructor
public class MailUserManagement {

    private final MailUserService repository;
    private final PasswordEncoder encoder;

    /**
     * Registers a {@link User} with the given {@link Username} and
     * {@link Password}.
     *
     * @param username must not be {@literal null}.
     * @param email must not be {@literal null}.
     * @return
     */
    public MailUser register(String username, String email) {

        Assert.notNull(username, "Username must not be null!");
        Assert.notNull(email, "Password must not be null!");

        repository.findByUsername(username).ifPresent(user -> {
            throw new IllegalArgumentException("User with that name already exists!");
        });

        MailUser user = new MailUser();
        user.setName(username);
        user.setEmail(email);

        repository.save(user);

        return user;
    }

    /**
     * Returns a {@link Page} of {@link User} for the given {@link Pageable}.
     *
     * @param pageable must not be {@literal null}.
     * @return
     */
    public Page<MailUser> findAll(Pageable pageable) {

        Assert.notNull(pageable, "Pageable must not be null!");

        return repository.findAll(pageable);
    }

    /**
     * Returns the {@link User} with the given {@link Username}.
     *
     * @param username must not be {@literal null}.
     * @return
     */
    public Optional<MailUser> findByUsername(String username) {

        Assert.notNull(username, "Username must not be null!");

        return repository.findByUsername(username);
    }
}
