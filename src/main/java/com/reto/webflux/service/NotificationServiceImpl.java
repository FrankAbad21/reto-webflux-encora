package com.reto.webflux.service;

import com.reto.webflux.dto.NotificationClientRequest;
import com.reto.webflux.dto.NotificationClientResponse;
import com.reto.webflux.dto.NotificationDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class NotificationServiceImpl implements  NotificationService{

    private static final String URI = "https://d0c79438-6cd6-488d-9b2c-66d9d742a0e9.mock.pstmn.io";
    private static final String API = "/notifications/mock";

    @Override
    public Mono<NotificationClientResponse> enviarNotificacion(Mono<NotificationDto> notificationDtoMono) {
        Mono<NotificationClientResponse> notificationClientResponseMono = Mono
                .just(NotificationClientResponse.builder()
                        .id("idididididiid")
                        .message("Notificacion enviada con exito").build());
        WebClient webClient = WebClient.builder().baseUrl(URI).build();
        Mono<NotificationClientRequest> notificationClientRequestMono =  notificationDtoMono
                .map(noti -> NotificationClientRequest.builder().userId(noti.getUserId())
                        .message(noti.getMessage()).build());
        return webClient.post().uri(API).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(notificationClientRequestMono, NotificationClientRequest.class)
                .retrieve()
                .bodyToMono(NotificationClientResponse.class)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    return notificationClientResponseMono;
                });
    }
}
