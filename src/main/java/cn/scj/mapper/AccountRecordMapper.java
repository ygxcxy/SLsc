package cn.scj.mapper;

import cn.scj.model.AccountRecord;

public interface AccountRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountRecord record);

    int insertSelective(AccountRecord record);

    AccountRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountRecord record);

    int updateByPrimaryKey(AccountRecord record);
}