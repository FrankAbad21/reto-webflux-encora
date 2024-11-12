package com.reto.webflux.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationClientResponse {

    private String id;
    private String message;
}
