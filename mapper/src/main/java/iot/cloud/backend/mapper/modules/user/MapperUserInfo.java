package iot.cloud.backend.mapper.modules.user;

import iot.cloud.backend.mapper.entity.EntityUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author weichuang 2023/5/13 19:28
 */
@Repository
@Mapper
public interface MapperUserInfo {
    @Select("select id,email from user_info where email=#{email}")
    EntityUserInfo selectByEmail(@Param("email") String email);

    @Select("select id,email from user_info where id=#{id}")
    EntityUserInfo selectById(@Param("id") Long id);
}
