package cn.scj.service.impl;

import cn.scj.dto.ResponseCode;
import cn.scj.mapper.AccountMapper;
import cn.scj.mapper.AccountRecordMapper;
import cn.scj.mapper.AuUserMapper;
import cn.scj.model.Account;
import cn.scj.model.AccountRecord;
import cn.scj.model.AuUser;
import cn.scj.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AuUserMapper userMapper;

    @Autowired
    private AccountRecordMapper recordMapper;

    @Override
    public Account queryByUserId(Long userId) {
        return accountMapper.queryByUserId(userId);
    }

    @Override
    public ResponseCode transfer(Long id, String transferCard, BigDecimal transferMoney) {
        ResponseCode code = new ResponseCode();

        //登录用户
        AuUser loginUser = userMapper.queryUserById(id);
        //转帐目标会员
        AuUser transferUser = userMapper.queryUserByCard(transferCard);


        if(loginUser.getId().equals(transferUser.getId())){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("违规操作，目标会员不能为自己！");
            return code;
        }
        if(transferUser == null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("违规操作，用户不存在！");
        }else if(transferUser.getUserType().equals("")){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("违规操作，不能给管理员转账！");
        }else if(transferUser.getUserType().equals("1")){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("违规操作，不能给注册用户转账！");
        }else{
            //登录用户账户
            Account loginAccount = accountMapper.queryByUserId(id);
            //转账目标账户
            Account transferAccount = accountMapper.queryByUserId(transferUser.getId());


            if(transferMoney.compareTo(loginAccount.getBalance()) == 1){
                code.setCode(ResponseCode.FAIL);
                code.setMsg("转账失败，你的账户余额不足！");
            }else{
                BigDecimal money = loginAccount.getBalance().subtract(transferMoney);

                loginAccount.setBalance(money);
                loginAccount.setUpdateTime(LocalDateTime.now());

                int row = accountMapper.updateByPrimaryKeySelective(loginAccount);

                if(row > 0){

                    BigDecimal add = transferAccount.getBalance().add(transferMoney);

                    transferAccount.setUpdateTime(LocalDateTime.now());
                    transferAccount.setBalance(add);

                    accountMapper.updateByPrimaryKeySelective(transferAccount);

                    BigDecimal transferMoney2 = transferMoney.negate();

                    AccountRecord loginRecord = new AccountRecord();
                    loginRecord.setAccountId(loginAccount.getId());
                    loginRecord.setTime(LocalDateTime.now());
                    loginRecord.setAmount(transferMoney2);
                    loginRecord.setBalance(loginAccount.getBalance());
                    loginRecord.setRemark("转账给" + transferCard + "会员，" + transferMoney + "元");



                    AccountRecord transferRecord = new AccountRecord();
                    transferRecord.setAccountId(transferAccount.getId());
                    transferRecord.setTime(LocalDateTime.now());
                    transferRecord.setAmount(transferMoney);
                    transferRecord.setBalance(transferAccount.getBalance());
                    transferRecord.setRemark("收款" + transferMoney + "元");

                    recordMapper.insertSelective(loginRecord);
                    recordMapper.insertSelective(transferRecord);
                    code.setCode(ResponseCode.SUCCESS);
                    code.setMsg("转账成功！");
                }else{
                    code.setCode(ResponseCode.FAIL);
                    code.setMsg("转账失败，系统繁忙！");
                }
            }
        }
        return code;
    }
}
