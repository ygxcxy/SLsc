package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account queryByUserId(Long userId);

    ResponseCode transfer(Long id, String transferCard, BigDecimal transferMoney);
}
