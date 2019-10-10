package cn.scj.mapper;

import cn.scj.dto.PwdDto;
import cn.scj.model.AuUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Shaochenjie
 */
public interface AuUserMapper {

    void save(AuUser user);

    AuUser findUserName(@Param("userName") String userName);

    AuUser findLoginCode(@Param("loginCode") String loginCode);

    void LoginCodeupdatePwd(PwdDto pwdDto);

    void LoginCodeupdatePwd2(PwdDto pwdDto);

    AuUser findAll(@Param("id") Long id);

    void updateUser(AuUser user);

    List<AuUser> findAllCode(String loginCode);

    void delById(@Param("id") Long id);

    void delByIds(List<Integer> ids);
}