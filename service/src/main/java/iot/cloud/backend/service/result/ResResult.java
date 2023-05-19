package iot.cloud.backend.service.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weichuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResResult<T> {
    private Integer result = 1;
    private Integer errcode = 0;
    private String msg = "ok";
    private T data;

    public ResResult(T data) {
        this.data = data;
    }

    public ResResult(Integer errcode, String msg) {
        this.result = 0;
        this.errcode = errcode;
        this.msg = msg;
    }
}
