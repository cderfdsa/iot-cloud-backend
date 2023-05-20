package iot.cloud.backend.mapper.modules.user;

import iot.cloud.backend.mapper.entity.EntityUserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperUserInfo {

    @Select("select id,email,account from user_info where email=#{email}")
    EntityUserInfo selectByEmail(@Param("email") String email);

    @Select("select id,email,account from user_info where id=#{id}")
    EntityUserInfo selectById(@Param("id") Long id);

    @Insert("insert into " +
            "user_info(email,account) " +
            "value(#{obj.email},#{obj.account})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityUserInfo entityUserInfo);

    @Select("select count(1) from user_info where account=#{account} and secret=#{secret}")
    int countByAccountAndSecret(@Param("account") String account, @Param("secret") String secret);
}
