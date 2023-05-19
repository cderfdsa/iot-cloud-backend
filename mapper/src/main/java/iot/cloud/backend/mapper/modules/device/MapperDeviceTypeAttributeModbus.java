package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttributeModbus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperDeviceTypeAttributeModbus {

    @Insert("insert into " +
            "device_type_attribute_modbus(rel_device_type_id,rel_device_type_attribute_id,slave_address,register_address,read_write_type,data_type) " +
            "value(#{obj.relDeviceTypeId},#{obj.relDeviceTypeAttributesId},#{obj.slaveAddress},#{obj.registerAddress},#{obj.readWriteType},#{obj.dataType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityDeviceTypeAttributeModbus entityDeviceTypeAttributeModbus);

    @Delete("delete from device_type_attribute_modbus where id=#{id}")
    int delete(@Param("id") Long id);

    @Update("update device_type_attribute_modbus set " +
            "rel_device_type_id=#{obj.relDeviceTypeId}," +
            "rel_device_type_attribute_id=#{obj.relDeviceTypeAttributesId}," +
            "slave_address=#{obj.slaveAddress}," +
            "register_address=#{obj.registerAddress} " +
            "read_write_type=#{obj.readWriteType} " +
            "data_type=#{obj.dataType} " +
            "where id=#{obj.id}")
    int update(@Param("obj") EntityDeviceTypeAttributeModbus entityDeviceTypeAttributeModbus);

    @Select("select id,rel_device_type_id,rel_device_type_attribute_id,slave_address,register_address,read_write_type,data_type " +
            "from device_type_attribute_modbus where id=#{id}")
    EntityDeviceTypeAttributeModbus selectById(@Param("id") Long id);
}
