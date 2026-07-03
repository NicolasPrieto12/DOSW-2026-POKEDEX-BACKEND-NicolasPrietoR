package com.pokedex.controller.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(

    String errorCode,
    String message,
    Map<String, String> fieldErrors,
    LocalDateTime timestamp

) {}
