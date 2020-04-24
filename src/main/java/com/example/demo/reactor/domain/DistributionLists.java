package com.example.demo.reactor.domain;

import java.util.List;

public class DistributionLists {

    public static final List<EmailAddress> AD_TEAM = List.of(
            EmailAddress.of("bk", "test.com"),
            EmailAddress.of("k", "test.com"),
            EmailAddress.of("j", "test.com")
    );

    public static final List<EmailAddress> API_TEAM = List.of(
            EmailAddress.of("jw", "test.com"),
            EmailAddress.of("kelly", "test.com")
    );

    public static final List<EmailAddress> WEB_TEAM = List.of(
            EmailAddress.of("andy", "test.com"),
            EmailAddress.of("nicole", "test.com")
    );
}
