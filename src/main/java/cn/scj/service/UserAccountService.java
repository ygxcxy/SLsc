package cn.scj.service;

import cn.scj.dto.AccountDto;
import cn.scj.dto.ResponseCode;

public interface UserAccountService {

    ResponseCode showAccountByEndDate(AccountDto accountDto);
}
