package iot.cloud.backend.mapper.modules.device;

import iot.cloud.backend.mapper.entity.EntityDeviceTypeAttribute;
import iot.cloud.backend.mapper.vo.VoIdName;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weichuang
 */
@Repository
@Mapper
public interface MapperDeviceTypeAttribute {

    @Insert("insert into " +
            "device_type_attribute(rel_device_type_id,name,code,type,data_type) " +
            "value(#{obj.relDeviceTypeId},#{obj.name},#{obj.code},#{obj.type},#{obj.dataType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("obj") EntityDeviceTypeAttribute entityDeviceTypeAttribute);

    @Delete("delete from device_type_attribute where id=#{id};" +
            "delete from device_type_attribute_modbus where rel_device_type_attribute_id=#{id};")
    int delete(@Param("id") Long id);

    @Update("update device_type_attribute set " +
            "name=#{obj.name}," +
            "code=#{obj.code}," +
            "type=#{obj.type}," +
            "data_type=#{obj.dataType} " +
            "where id=#{obj.id}")
    int update(@Param("obj") EntityDeviceTypeAttribute entityDeviceTypeAttribute);

    @Select("select id,rel_device_type_id,name,code,type,data_type " +
            "from device_type_attribute where id=#{id}")
    EntityDeviceTypeAttribute selectById(@Param("id") Long id);

    @Select("<script>" +
            "select count(1) " +
            "from device_type_attribute where rel_device_type_id=#{relDeviceTypeId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (name like concat('%', #{searchKey}, '%') or code like concat('%', #{searchKey}, '%') ) " +
            "   </when>" +
            "</choose>" +
            "</script>"
    )
    Long limitTotal(@Param("relDeviceTypeId") Long relDeviceTypeId, @Param("searchKey") String searchKey);


    @Select("<script>" +
            "select id, rel_device_type_id relDeviceTypeId, name, code, `type`, data_type dataType " +
            "from device_type_attribute where rel_device_type_id=#{relDeviceTypeId} " +
            "<choose> " +
            "   <when test='searchKey != null and searchKey != &apos;&apos;'> " +
            "   and (name like concat('%', #{searchKey}, '%') or code like concat('%', #{searchKey}, '%') ) " +
            "   </when>" +
            "</choose> " +
            "order by id desc " +
            "limit #{offset},#{rows}" +
            "</script>"
    )
    List<EntityDeviceTypeAttribute> limit(@Param("relDeviceTypeId") Long relDeviceTypeId, @Param("searchKey") String searchKey, @Param("offset") Long offset, @Param("rows") int rows);

    @Select("<script>" +
            "select id,name from device_type_attribute where id in" +
            "<foreach collection='ids' item='item' open='(' separator=',' close=')'> " +
            "#{item} " +
            "</foreach> " +
            "</script> "
    )
    List<VoIdName> selectIdNamesByIds(@Param("ids") List<Long> ids);
}
