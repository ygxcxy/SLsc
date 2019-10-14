package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.Role;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname RoleService
 * @Description TODO
 * @Date 2019/10/10 16:44
 */
public interface RoleService {
    List<Role> findAll();

    Role findById(Long roleId);

    ResponseCode delById(List<Integer> ids);

    void save(Role role);

    List<Role> findCode(String code);

    void update(Role role);
}
