package iot.cloud.backend.service.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weichuang 2023/5/13 22:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResResult<T> {
    private Integer status = 0;
    private String msg = "ok";
    private T data;

    public ResResult(T data) {
        this.data = data;
    }

    public ResResult(Integer status, String msg) {

        this.status = status;
        this.msg = msg;
    }
}
