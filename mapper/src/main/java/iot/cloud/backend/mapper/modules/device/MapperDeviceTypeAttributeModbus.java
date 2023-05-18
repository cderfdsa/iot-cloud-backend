package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttribute;
import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttributeModbus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author weichuang 2023/5/18 16:48
 */
@Repository
@Mapper
public interface MapperDeviceTypeAttributeModbus extends tk.mybatis.mapper.common.Mapper<EntityDeviceTypeAttributeModbus>, MySqlMapper<EntityDeviceTypeAttributeModbus> {
}
