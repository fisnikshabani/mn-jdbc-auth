package com.example;

import com.example.auth.persistence.UserEntity;
import com.example.auth.persistence.UserRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.inject.Singleton;

@Singleton
public class TestDataProvider {

    private final UserRepository users;

    public TestDataProvider(UserRepository users) {
        this.users = users;
    }

    @EventListener
    public void init(StartupEvent event){
        final String email = "fisnik@example.com";
        if (users.findByEmail(email).isEmpty()){
            final UserEntity fisnik = new UserEntity();
            fisnik.setEmail(email);
            fisnik.setPassword("secret");
            users.save(fisnik);
        }
    }
}
