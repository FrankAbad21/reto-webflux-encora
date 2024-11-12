package com.reto.webflux.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationClientRequest {

    private String userId;
    private String message;
}
