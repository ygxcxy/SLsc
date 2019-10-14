package cn.scj.mapper;

import cn.scj.dto.AccountDto;
import cn.scj.model.UserAccount;

import java.util.List;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    List<UserAccount> showAccountByEndDate(AccountDto accountDto);
}