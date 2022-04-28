package br.com.softpethouse.api.commom.validation;

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
