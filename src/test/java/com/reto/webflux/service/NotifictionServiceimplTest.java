package com.reto.webflux.service;

import com.reto.webflux.dto.NotificationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

class NotificationServiceImplTest {


    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnviarNotificacion_successfulResponse() {
        // Simula un NotificationDto de entrada
        NotificationDto notificationDto = NotificationDto.builder()
                .userId("idididididiid")
                .message("Notificacion enviada con exito").build();
        Mono<NotificationDto> notificationDtoMono = Mono.just(notificationDto);

        // Ejecuta el método y verifica la respuesta con StepVerifier
        StepVerifier.create(notificationService.enviarNotificacion(notificationDtoMono))
                .expectNextMatches(response ->
                        response.getMessage().equals("Notificacion enviada con exito") &&
                                response.getId().equals("idididididiid")
                )
                .verifyComplete();
    }

    @Test
    void testEnviarNotificacion_errorResponse() {
        // Simula un NotificationDto de entrada
        NotificationDto notificationDto = new NotificationDto("idididididiid",
                "Notificacion enviada con exito", LocalDateTime.now());
        Mono<NotificationDto> notificationDtoMono = Mono.just(notificationDto);

        // Ejecuta el método y verifica la respuesta predeterminada con StepVerifier
        StepVerifier.create(notificationService.enviarNotificacion(notificationDtoMono))
                .expectNextMatches(response ->
                        response.getMessage().equals("Notificacion enviada con exito") &&
                                response.getId().equals("idididididiid")
                )
                .verifyComplete();
    }
}
