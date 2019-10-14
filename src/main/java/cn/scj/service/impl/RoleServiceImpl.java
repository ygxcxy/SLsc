package cn.scj.service.impl;

import cn.scj.dto.ResponseCode;
import cn.scj.mapper.AuRoleMapper;
import cn.scj.model.Role;
import cn.scj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname RoleServiceImpl
 * @Description TODO
 * @Date 2019/10/10 16:45
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AuRoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();

    }

    @Override
    public Role findById(Long roleId) {

        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public ResponseCode delById(List<Integer> ids) {
        ResponseCode code = new ResponseCode();
        if(ids==null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("ID错误");
        }else{
            roleMapper.delById(ids);
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("删除成功");
        }
        return code;
    }

    @Override
    public void save(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public List<Role> findCode(String code) {

        return roleMapper.findCode(code);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
