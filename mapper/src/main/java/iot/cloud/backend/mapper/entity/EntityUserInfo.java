package iot.cloud.backend.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weichuang 2023/5/13 19:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityUserInfo implements Serializable {
    private Long id;
    private String email;
}