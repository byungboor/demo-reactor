package com.example.demo.reactor.domain;

import lombok.Getter;

@Getter
public class User {

    public static final User JOHN_DOE = User.of("John Doe", EmailAddress.of("john.doe", "anonymous.com"));
    public static final User JANE_DOE = User.of("Jane Doe", EmailAddress.of("jane.doe", "anonymous.com"));

    private String name;
    private EmailAddress emailAddress;

    private User() {

    }

    public static User of(String name, EmailAddress emailAddress) {

        User user = new User();
        user.emailAddress = emailAddress;
        user.name = name;
        return user;
    }
}
