package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.AuUser;

import java.math.BigDecimal;

public interface UserCashService {
    ResponseCode cashWithdrawal(AuUser user, BigDecimal money);
}
