package br.com.softpethouse.api.negotiation.dto;

import lombok.Data;

@Data
public class NegotiationItemDtoCreate {

    private long productId;

    private double qtt;

    private double value;

}
