package cn.scj.mapper;

import cn.scj.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findAll();

    void delById(List<Integer> ids);

    List<Role> findCode(@Param("code") String code);
}