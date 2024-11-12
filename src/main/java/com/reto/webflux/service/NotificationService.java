package com.reto.webflux.service;

import com.reto.webflux.dto.NotificationClientResponse;
import com.reto.webflux.dto.NotificationDto;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<NotificationClientResponse> enviarNotificacion(Mono<NotificationDto> notificationDtoMono);

}
