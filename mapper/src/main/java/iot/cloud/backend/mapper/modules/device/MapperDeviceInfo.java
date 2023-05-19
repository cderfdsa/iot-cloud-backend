package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
}
