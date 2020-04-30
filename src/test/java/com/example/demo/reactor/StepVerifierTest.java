package com.example.demo.reactor;

import com.example.demo.reactor.domain.DistributionLists;
import com.example.demo.reactor.domain.EmailAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierTest {

    @Test
    public void testExpectNext() {
        StepVerifier.create(Flux.just("a", "b", "c"))
                .expectNext("a")
                .expectNext("b")
                .expectNext("c")
                .expectNextCount(0L)
                .expectComplete()
                .verify();
    }

    @Test
    public void testExpectNextCount() {
        StepVerifier.create(Flux.just("a", "b", "c"))
                .assertNext(token -> {
                    Assertions.assertNotNull(token);
                    Assertions.assertEquals("a", token);
                })
                .expectNextCount(2)
                .verifyComplete();  // verifyComplete = expectComplete + verify;
    }

    @Test
    public void testExpectError() {

        Flux<EmailAddress> emailAddressFlux = Flux.fromIterable(DistributionLists.DEV_DEPT)
                .map(e -> {
                    if ("j".equalsIgnoreCase(e.getLocalPart()))
                        throw new RuntimeException("Wow!!!!");
                    return e;
                });

        // TODO 1.
        // 1번째 element 의 localPart 가 bk 이고 -> expectNextMatches
        // 2번째 element 의 localPart 가 k 이고  -> expectNextMatches
        // 3번째 element 는 RuntimeException 이 예상된다. -> expectError
        // 이를 검증 합시다. -> verify

        StepVerifier.create(emailAddressFlux);
    }

    @Test
    public void testAssertNext() {

        // TODO 2.
        // 1번째 element 의 localPart 가 bk 이고 -> assertNext 내부에서
        // 1번째 element 의 domainPart 가 test.com 이고  -> assertNext 내부에서
        // 나머지 element 의 갯수는 6개이다.
        // 이를 검증 합시다. -> verify

        Flux<EmailAddress> emailAddressFlux = Flux.fromIterable(DistributionLists.DEV_DEPT);
        StepVerifier.create(emailAddressFlux);

    }
}
