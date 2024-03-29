package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceInfo;
import iot.cloud.backend.mapper.vo.VoModbusAttribute;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperDeviceInfo {

    @Insert("insert into " +
            "device_info(rel_device_type_id,rel_user_info_id,name,code,pwd) " +
            "value(#{obj.relDeviceTypeId},#{obj.relUserInfoId},#{obj.name},#{obj.code},#{obj.pwd})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityDeviceInfo entityDeviceInfo);

    @Delete("delete from device_info where id=#{id}")
    int delete(@Param("id") Long id);

    @Update("update device_info set " +
            "rel_device_type_id=#{obj.relDeviceTypeId}," +
            "rel_user_info_id=#{obj.relUserInfoId}," +
            "name=#{obj.name}," +
            "code=#{obj.code}," +
            "pwd=#{obj.pwd} " +
            "where id=#{obj.id}")
    int update(@Param("obj") EntityDeviceInfo entityDeviceInfo);

    @Select("select id,rel_device_type_id,rel_user_info_id,name,code,pwd " +
            "from device_info where id=#{id}")
    EntityDeviceInfo selectById(@Param("id") Long id);

    @Select("select id,rel_device_type_id as relDeviceTypeId,rel_user_info_id as relUserInfoId,name,code,pwd " +
            "from device_info where code=#{code}")
    EntityDeviceInfo selectByCode(@Param("code") String code);

    @Select("select count(1) from device_info where code=#{code} and pwd=#{pwd}")
    int countByCodeAndPwd(@Param("code") String code, @Param("pwd") String pwd);

    @Select("select dtam.slave_address as slaveAddress, dtam.register_address as registerAddress, dta.code " +
            "from device_info di , device_type_attribute_modbus dtam ,device_type dt, device_type_attribute dta " +
            "where di.rel_device_type_id = dtam.rel_device_type_id and di.rel_device_type_id = dt.id and dta.rel_device_type_id=dt.id and dta.id = dtam.rel_device_type_attribute_id " +
            "and di.code = #{code} and dt.bus_time_value = #{busTimeValue} and dt.bus_time_unit = #{busTimeUnit} ")
    List<VoModbusAttribute> selectAttributeModbusByCodeAndTimeBus(
            @Param("code") String code,
            @Param("busTimeValue") Integer busTimeValue,
            @Param("busTimeUnit") Character busTimeUnit
    );

    @Select("SELECT b.account from device_info a,user_info b " +
            "where a.rel_user_info_id = b.id " +
            "and a.code = #{deviceCode}")
    String selectAccountByDeviceCode(@Param("deviceCode") String deviceCode);

    @Select("select count(1) from device_info \n" +
            "where rel_user_info_id = #{relUserInfoId}\n" +
            "union all\n" +
            "select count(1) from device_info \n" +
            "where rel_user_info_id = #{relUserInfoId} and online_status = 1\n" +
            "union all\n" +
            "select count(1) from device_info \n" +
            "where rel_user_info_id = #{relUserInfoId} and online_status = 2\n" +
            "union all\n" +
            "select count(1) from device_info \n" +
            "where rel_user_info_id = #{relUserInfoId} and alarm_status  = 1")
    List<Integer> countManyStatus(@Param("relUserInfoId") Long relUserInfoId);

    @Select("<script>" +
            "select count(1) " +
            "from device_info where rel_user_info_id=#{relUserInfoId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (name like concat('%', #{searchKey}, '%')  or code like concat('%', #{searchKey}, '%') ) " +
            "   </when>" +
            "   <when test=' relDeviceTypeId != null and relDeviceTypeId != 0 '> " +
            "   and rel_device_type_id = #{relDeviceTypeId} " +
            "   </when>" +
            "</choose> " +
            "</script>"
    )
    Long limitTotal(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceTypeId") Long relDeviceTypeId, @Param("searchKey") String searchKey);

    @Select("<script>" +
            "select id, rel_device_type_id relDeviceTypeId, rel_user_info_id relUserInfoId, name, code, pwd, online_status onlineStatus, alarm_status alarmStatus, active_status activeStatus " +
            "from device_info where rel_user_info_id=#{relUserInfoId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (name like concat('%', #{searchKey}, '%')  or code like concat('%', #{searchKey}, '%') ) " +
            "   </when>" +
            "   <when test=' relDeviceTypeId != null and relDeviceTypeId != 0 '> " +
            "   and rel_device_type_id = #{relDeviceTypeId} " +
            "   </when>" +
            "</choose> " +
            "limit #{offset},#{rows}" +
            "</script>"
    )
    List<EntityDeviceInfo> limit(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceTypeId") Long relDeviceTypeId, @Param("searchKey") String searchKey, @Param("offset") Long offset, @Param("rows") int rows);

    @Select("<script>" +
            "select count(1) " +
            "from device_info where rel_user_info_id=#{relUserInfoId} " +
            "<choose> " +
            "   <when test=' relDeviceTypeId != null and relDeviceTypeId != 0 '> " +
            "   and rel_device_type_id = #{relDeviceTypeId} " +
            "   </when>" +
            "</choose> " +
            "</script>"
    )
    Long countByDeviceTypeId(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceTypeId") Long relDeviceTypeId);
}
