package iot.cloud.backend.mapper.modules.history;

import iot.cloud.backend.mapper.entity.EntityHistoryDeviceOnline;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperHistoryDeviceOnline {

    @Insert("insert into " +
            "history_device_online(rel_device_info_id, rel_user_info_id, device_name, device_code, create_dt, status, status_reason) " +
            "value(#{obj.relDeviceInfoId},#{obj.relUserInfoId},#{obj.deviceName},#{obj.deviceCode},now(),#{obj.status},#{obj.statusReason})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityHistoryDeviceOnline entityHistoryDeviceOnline);


    @Select("select count(1) " +
            "from history_device_online where rel_user_info_id=#{relUserInfoId}")
    Long limitTotal(@Param("relUserInfoId") Long relUserInfoId);

    @Select("select id,rel_device_info_id, rel_user_info_id, device_name, device_code, create_dt, status, status_reason " +
            "from history_device_online where rel_user_info_id=#{relUserInfoId} limit #{offset},#{rows}")
    List<EntityHistoryDeviceOnline> limit(@Param("relUserInfoId") Long relUserInfoId, @Param("offset") Long offset, @Param("rows") int rows);

    @Select("select count(1) " +
            "from history_device_online where rel_user_info_id=#{relUserInfoId} and rel_device_info_id=#{relDeviceInfoId}")
    Long limitTotalByDeviceInfoId(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceInfoId") Long relDeviceInfoId);

    @Select("select id,rel_device_info_id, rel_user_info_id, device_name, device_code, create_dt, status, status_reason " +
            "from history_device_online where rel_user_info_id=#{relUserInfoId} and rel_device_info_id=#{relDeviceInfoId} limit #{offset},#{rows}")
    List<EntityHistoryDeviceOnline> limitByDeviceInfoId(@Param("relUserInfoId") Long relUserInfoId, @Param("relDeviceInfoId") Long relDeviceInfoId, @Param("offset") Long offset, @Param("rows") int rows);
}
