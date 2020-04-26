package com.example.demo.reactor.domain;

public class EmailAddress {

    private String localPart;
    private String domainPart;

    private EmailAddress() {
    }

    public static EmailAddress of(String localPart, String domainPart) {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.localPart = localPart;
        emailAddress.domainPart = domainPart;
        return emailAddress;
    }

    @Override
    public String toString() {
        return new StringBuilder(localPart)
                .append("@")
                .append(domainPart)
                .toString();
    }

}
