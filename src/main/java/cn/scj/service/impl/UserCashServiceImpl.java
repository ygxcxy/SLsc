package cn.scj.service.impl;

import cn.scj.dto.ResponseCode;
import cn.scj.mapper.AccountMapper;
import cn.scj.mapper.UserCashMapper;
import cn.scj.model.Account;
import cn.scj.model.AuUser;
import cn.scj.model.UserCash;
import cn.scj.service.UserCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserCashServiceImpl implements UserCashService {
    @Autowired
    private UserCashMapper cashMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseCode cashWithdrawal(AuUser user, BigDecimal money) {
        ResponseCode code = new ResponseCode();
        if(money == null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("违规操作，警告");
            return code;
        }
        Account account = accountMapper.queryByUserId(user.getId());
        if(account == null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("系统错误，请联系客服");
            return code;
        }
        BigDecimal subtract = account.getBalance().subtract(money);

        account.setBalance(subtract);
        account.setUpdateTime(LocalDateTime.now());

        int row = accountMapper.updateByPrimaryKeySelective(account);
        if(row > 0){

            UserCash cash = new UserCash();

            cash.setUserId(user.getId());
            cash.setCashTime(LocalDateTime.now());

            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("提现成功！");
        }else{
            code.setCode(ResponseCode.FAIL);
            code.setMsg("系统异常，提现失败，请联系客服！");
        }
        return code;
    }
}
