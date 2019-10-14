package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.Account;
import cn.scj.model.AccountRecord;

public interface AccountRecordService {
    ResponseCode recharge(Account account, AccountRecord record);
}
