package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttributeModbus;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperDeviceTypeAttributeModbus {

    @Insert("insert into " +
            "device_type_attribute_modbus(rel_device_type_id,rel_device_type_attribute_id,slave_address,register_address,read_write_type,data_type) " +
            "value(#{obj.relDeviceTypeId},#{obj.relDeviceTypeAttributeId},#{obj.slaveAddress},#{obj.registerAddress},#{obj.readWriteType},#{obj.dataType})")
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

    @Select("<script>" +
            "select count(1) " +
            "from device_type_attribute_modbus where rel_device_type_id=#{relDeviceTypeId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (slave_address like concat('%', #{searchKey}, '%') or register_address like concat('%', #{searchKey}, '%') ) " +
            "   </when>" +
            "</choose>" +
            "</script>"
    )
    Long limitTotal(@Param("relDeviceTypeId") Long relDeviceTypeId, @Param("searchKey") String searchKey);


    @Select("<script>" +
            "select id, rel_device_type_id relDeviceTypeId, rel_device_type_attribute_id relDeviceTypeAttributeId, slave_address slaveAddress, register_address registerAddress, read_write_type readWriteType, data_type dataType " +
            "from device_type_attribute_modbus where rel_device_type_id=#{relDeviceTypeId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (slave_address like concat('%', #{searchKey}, '%') or register_address like concat('%', #{searchKey}, '%') ) " +
            "   </when>" +
            "</choose> " +
            "order by id desc " +
            "limit #{offset},#{rows}" +
            "</script>"
    )
    List<EntityDeviceTypeAttributeModbus> limit(@Param("relDeviceTypeId") Long relDeviceTypeId, @Param("searchKey") String searchKey, @Param("offset") Long offset, @Param("rows") int rows);
}
