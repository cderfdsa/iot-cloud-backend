package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceInfo;
import iot.cloud.backend.mapper.entity.EntityDeviceType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author weichuang 2023/5/18 16:48
 */
@Repository
@Mapper
public interface MapperDeviceInfo extends tk.mybatis.mapper.common.Mapper<EntityDeviceInfo>, MySqlMapper<EntityDeviceInfo> {
}
