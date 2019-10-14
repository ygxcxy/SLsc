package cn.scj.service.impl;

import cn.scj.dto.ResponseCode;
import cn.scj.mapper.AccountMapper;
import cn.scj.mapper.AccountRecordMapper;
import cn.scj.model.Account;
import cn.scj.model.AccountRecord;
import cn.scj.service.AccountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountRecordServiceImpl implements AccountRecordService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountRecordMapper recordMapper;

    @Override
    public ResponseCode recharge(Account account, AccountRecord record) {
        ResponseCode code = new ResponseCode();

        BigDecimal result1 = account.getBalance().add(record.getAmount());

        account.setBalance(result1);
        account.setUpdateTime(LocalDateTime.now());

        record.setBalance(result1);
        record.setTime(LocalDateTime.now());
        record.setAccountId(account.getId());
        int row = accountMapper.updateByPrimaryKeySelective(account);
        if(row > 0){
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("充值成功！");
            recordMapper.insertSelective(record);
        }else{
            code.setCode(ResponseCode.FAIL);
            code.setMsg("系统错误,充值失败！");
        }
        return code;
    }
}
