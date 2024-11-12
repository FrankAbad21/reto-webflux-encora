package com.reto.webflux.controller;

import com.reto.webflux.dto.NotificationClientResponse;
import com.reto.webflux.dto.NotificationDto;
import com.reto.webflux.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/notification")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Mono<NotificationClientResponse> enviarNotificacion(@RequestBody Mono<NotificationDto> notificationDtoMono) {
        return notificationService.enviarNotificacion(notificationDtoMono);
    }

}
