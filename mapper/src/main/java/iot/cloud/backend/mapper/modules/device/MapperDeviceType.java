package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperDeviceType {

    @Insert("insert into " +
            "device_type(name,type,communication_type,protocol_type,protocol_format) " +
            "value(#{obj.name},#{obj.type},#{obj.communicationType},#{obj.protocolType},#{obj.protocolFormat})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityDeviceType entityDeviceType);

    @Delete("delete from device_type where id=#{id}")
    @Delete("delete from device_type_attribute where rel_device_type_id=#{id}")
    @Delete("delete from device_type_attribute_modbus where rel_device_type_id=#{id}")
    int delete(@Param("id") Long id);

    @Update("update device_type set " +
            "name=#{obj.name}," +
            "type=#{obj.type}," +
            "communication_type=#{obj.communicationType}," +
            "protocol_type=#{obj.protocolType}," +
            "protocol_format=#{obj.protocolFormat} " +
            "where id=#{obj.id}")
    int update(@Param("obj") EntityDeviceType entityDeviceType);

    @Select("select id,name,type,communication_type,protocol_type,protocol_format " +
            "from device_type where id=#{id}")
    EntityDeviceType selectById(@Param("id") Long id);

    @Select("select count(1) " +
            "from device_type where name like '%#{nameKey}%'")
    Long limitTotal(@Param("nameKey") String nameKey);

    @Select("select id,name,type,communication_type,protocol_type,protocol_format " +
            "from device_type where name like '%#{nameKey}%' limit #{offset},#{rows}")
    List<EntityDeviceType> limit(@Param("nameKey") String nameKey, @Param("offset") Long offset, @Param("rows") int rows);
}
