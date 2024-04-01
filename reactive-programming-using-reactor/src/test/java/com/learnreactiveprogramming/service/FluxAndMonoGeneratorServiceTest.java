package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        // Arrange

        // Act
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();


        // Assert
        StepVerifier.create(namesFlux)
                .expectNext("alex", "ben", "chloe")
//                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {
        // Arrange
        int stringLength = 1;

        // Act
        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxMap(stringLength);


        // Assert
        StepVerifier.create(namesFluxMap)
                .expectNext("ALEX", "BEN", "CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        // Arrange
        int stringLength = 3;

        // Act
        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxFlatMap(stringLength);


        // Assert
        StepVerifier.create(namesFluxMap)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapAsync() {
        // Arrange
        int stringLength = 3;

        // Act
        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxFlatMapAsync(stringLength);


        // Assert
        StepVerifier.create(namesFluxMap)
//                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E") Fails because flatmap does not keep the original order
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFluxConcatMapAsync() {
        // Arrange
        int stringLength = 3;

        // Act
        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxConcatMapAsync(stringLength);


        // Assert
        // concatmap keeps the original order. There is a performance tradeoff.
        StepVerifier.create(namesFluxMap)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFluxImmutability() {
        // Arrange

        // Act
        var namesFluxMap = fluxAndMonoGeneratorService.namesFluxImmutability();


        // Assert - Will fail due to the immutability of reactive streams
        StepVerifier.create(namesFluxMap)
                .expectNext("ALEX", "BEN", "CHLOE")
                .verifyComplete();
    }

    @Test
    void namesMonoFlatMap() {
        // Arrange

        // Act
        var namesMonoFlatMap = fluxAndMonoGeneratorService.namesMonoFlatMap(2);


        // Assert - Will fail due to the immutability of reactive streams
        StepVerifier.create(namesMonoFlatMap)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

}