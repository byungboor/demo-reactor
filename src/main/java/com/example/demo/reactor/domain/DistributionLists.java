package com.example.demo.reactor.domain;

import java.util.ArrayList;
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

    public static final List<EmailAddress> DEV_DEPT;

    static {
        DEV_DEPT = new ArrayList<>();
        DEV_DEPT.addAll(AD_TEAM);
        DEV_DEPT.addAll(API_TEAM);
        DEV_DEPT.addAll(WEB_TEAM);
    }
}
