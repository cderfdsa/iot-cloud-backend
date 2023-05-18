package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceType;
import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author weichuang 2023/5/18 16:48
 */
@Repository
@Mapper
public interface MapperDeviceTypeAttribute extends tk.mybatis.mapper.common.Mapper<EntityDeviceTypeAttribute>, MySqlMapper<EntityDeviceTypeAttribute> {
}
