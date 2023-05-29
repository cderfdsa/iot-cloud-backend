package iot.cloud.backend.mapper.modules.history;

import iot.cloud.backend.mapper.entity.EntityHistoryDeviceAttribute;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperHistoryDeviceAttribute {

    @Insert("insert into " +
            "history_device_attribute(rel_device_info_id, rel_user_info_id, device_name, device_code, device_type_name, device_type_attribute_name, device_type_attribute_code, create_dt,value) " +
            "value(#{obj.relDeviceInfoId},#{obj.relUserInfoId},#{obj.deviceName},#{obj.deviceCode},#{obj.deviceTypeName},#{obj.deviceTypeAttributeName},#{obj.deviceTypeAttributeCode},now(),#{obj.value})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityHistoryDeviceAttribute entityHistoryDeviceAttribute);


    @Select("select count(1) " +
            "from history_device_attribute where rel_user_info_id=#{relUserInfoId}")
    Long limitTotal(@Param("relUserInfoId") Long relUserInfoId);

    @Select("select id,rel_device_info_id, rel_user_info_id, device_name, device_code, device_type_name, device_type_attribute_name, device_type_attribute_code, create_dt " +
            "from history_device_attribute where rel_user_info_id=#{relUserInfoId} limit #{offset},#{rows}")
    List<EntityHistoryDeviceAttribute> limit(@Param("relUserInfoId") Long relUserInfoId, @Param("offset") Long offset, @Param("rows") int rows);

    @Select("select count(1) " +
            "from history_device_attribute where rel_user_info_id=#{relUserInfoId} and rel_device_info_id=#{relDeviceInfoId}")
    Long limitTotalByDeviceInfoId(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceInfoId") Long relDeviceInfoId);

    @Select("select id,rel_device_info_id, rel_user_info_id, device_name, device_code, device_type_name, device_type_attribute_name, device_type_attribute_code, create_dt " +
            "from history_device_attribute where rel_user_info_id=#{relUserInfoId} and rel_device_info_id=#{relDeviceInfoId} limit #{offset},#{rows}")
    List<EntityHistoryDeviceAttribute> limitByDeviceInfoId(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceInfoId") Long relDeviceInfoId, @Param("offset") Long offset, @Param("rows") int rows);

    @Select("select a.id as relDeviceInfoId," +
            "a.rel_user_info_id as relUserInfoId," +
            "a.name as deviceName, " +
            "a.code as deviceCode, " +
            "c.name as deviceTypeName, " +
            "b.name as deviceTypeAttributeName, " +
            "b.code as deviceTypeAttributeCode " +
            "from " +
            "device_info a,device_type_attribute b,device_type c " +
            "where " +
            "a.rel_device_type_id = b.rel_device_type_id and c.id = a.rel_device_type_id and c.id = b.rel_device_type_id " +
            "and a.code = #{deviceCode} " +
            "and b.code = #{attributeCode} ")
    EntityHistoryDeviceAttribute selectAttributeByTwoCode(
            @Param("deviceCode") String deviceCode,
            @Param("attributeCode") String attributeCode
    );
}
