package br.com.softpethouse.api.negotiation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NegotiationDtoUpdate {

    private long userId;

    private long businessId;

    private double value;

    private LocalDateTime dateTime;

    private String description;

}
