package br.com.softpethouse.api.service.validation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMsg {

    private String msg;

    public ResponseMsg(String msg) {
        this.msg = msg;
    }

}
