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
            "device_type(rel_user_info_id,name,type,communication_type,protocol_type,protocol_format, bus_time_value, bus_time_unit) " +
            "value(#{obj.relUserInfoId},#{obj.name},#{obj.type},#{obj.communicationType},#{obj.protocolType},#{obj.protocolFormat},#{obj.busTimeValue},#{obj.busTimeUnit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityDeviceType entityDeviceType);

    @Delete("delete from device_type where id=#{id};" +
            "delete from device_type_attribute where rel_device_type_id=#{id};" +
            "delete from device_type_attribute_modbus where rel_device_type_id=#{id};")
    int delete(@Param("id") Long id);

    @Update("update device_type set " +
            "name=#{obj.name}," +
            "type=#{obj.type}," +
            "communication_type=#{obj.communicationType}," +
            "protocol_type=#{obj.protocolType}," +
            "protocol_format=#{obj.protocolFormat} " +
            "where id=#{obj.id}")
    int update(@Param("obj") EntityDeviceType entityDeviceType);

    @Select("select id,rel_user_info_id,name,type,communication_type,protocol_type,protocol_format " +
            "from device_type where id=#{id}")
    EntityDeviceType selectById(@Param("id") Long id);

    @Select("<script>" +
            "select count(1) " +
            "from device_type where rel_user_info_id=#{relUserInfoId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (name like concat('%', #{searchKey}, '%')) " +
            "   </when>" +
            "</choose>" +
            "</script>"
    )
    Long limitTotal(@Param("relUserInfoId") Long relUserInfoId, @Param("searchKey") String searchKey);


    @Select("<script>" +
            "select id, name, `type`, communication_type communicationType, protocol_type protocolType, protocol_format protocolFormat, rel_user_info_id relUserInfoId, bus_time_value busTimeValue, bus_time_unit busTimeUnit " +
            "from device_type where rel_user_info_id=#{relUserInfoId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (name like concat('%', #{searchKey}, '%')  ) " +
            "   </when>" +
            "</choose> " +
            "order by id desc " +
            "limit #{offset},#{rows}" +
            "</script>"
    )
    List<EntityDeviceType> limit(@Param("relUserInfoId") Long relUserInfoId, @Param("searchKey") String searchKey, @Param("offset") Long offset, @Param("rows") int rows);
}
