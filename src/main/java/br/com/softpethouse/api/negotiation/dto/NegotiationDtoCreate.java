package br.com.softpethouse.api.negotiation.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NegotiationDtoCreate {

    private long userId;

    private long businessId;

    private double value;

    private LocalDateTime dateTime;

    private String description;

    private List<NegotiationItemDtoCreate> negotiationItems;

}
