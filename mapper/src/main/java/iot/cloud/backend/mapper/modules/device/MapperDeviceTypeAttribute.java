package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttribute;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author weichuang 2023/5/18 16:48
 */
@Repository
@Mapper
public interface MapperDeviceTypeAttribute {

    @Insert("insert into " +
            "device_type_attribute(rel_device_type_id,name,code,type,data_type) " +
            "value(#{obj.relDeviceTypeId},#{obj.name},#{obj.code},#{obj.type},#{obj.dataType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityDeviceTypeAttribute entityDeviceTypeAttribute);

    @Delete("delete from device_type_attribute where id=#{id}")
    @Delete("delete from device_type_attribute_modbus where rel_device_type_attribute_id=#{id}")
    int delete(@Param("id") Long id);

    @Update("update device_type_attribute set " +
            "name=#{obj.name}," +
            "code=#{obj.code}," +
            "type=#{obj.type}," +
            "data_type=#{obj.dataType} " +
            "where id=#{obj.id}")
    int update(@Param("obj") EntityDeviceTypeAttribute entityDeviceTypeAttribute);

    @Select("select id,rel_device_type_id,name,code,type,data_type " +
            "from device_type_attribute where id=#{id}")
    EntityDeviceTypeAttribute selectById(@Param("id") Long id);
}
