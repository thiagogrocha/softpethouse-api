package br.com.softpethouse.api.negotiation.dto;

import lombok.Data;

@Data
public class NegotiationItemDtoCreate {

    private long negotiationId;

    private long productId;

    private double quantity;

    private double value;

}
