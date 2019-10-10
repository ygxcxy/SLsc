package cn.scj.service;

import cn.scj.dto.PwdDto;
import cn.scj.dto.ResponseCode;
import cn.scj.model.AuUser;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname UserService
 * @Description TODO
 * @Date 2019/10/7 16:30
 */
public interface UserService {
    void save(AuUser user);

    AuUser findUserName(String userName);

    AuUser findLoginCode(String loginCode);

    ResponseCode LoginCodeupdatePwd(PwdDto pwdDto);

    ResponseCode LoginCodeupdatePwd2(PwdDto pwdDto);

    AuUser findAll(Long id);

    void updateUser(AuUser user);

    ResponseCode findAllCode(ResponseCode responseCode,String loginCode);

    ResponseCode delById(Long id);

    ResponseCode delByIds(List<Integer> ids);
}
