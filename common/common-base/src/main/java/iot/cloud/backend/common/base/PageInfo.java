package iot.cloud.backend.common.base;

import lombok.Data;

import java.util.List;

/**
 * @author weichuang 2023/5/19 11:31
 */
@Data
public class PageInfo<T> {
    private Long total;
    private Long offset;
    private int rows;
    private List<T> list;
}
