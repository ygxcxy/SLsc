package cn.scj.service.impl;

import cn.scj.dto.AccountDto;
import cn.scj.dto.ResponseCode;
import cn.scj.mapper.AccountMapper;
import cn.scj.mapper.UserCashMapper;
import cn.scj.model.Account;
import cn.scj.model.AuUser;
import cn.scj.model.UserCash;
import cn.scj.service.UserCashService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
            cash.setCashNum("15465122454184");
            cash.setCashPv(BigDecimal.valueOf(0));
            cash.setCurrency("RMB");
            cash.setPvRate(BigDecimal.valueOf(0.05));
            cash.setCashMoney(money);
            cash.setNote("提现" + money + "元");
            //手续费
            BigDecimal fee = money.multiply(BigDecimal.valueOf(0.05));
            cash.setFee(fee);
            cash.setTax(BigDecimal.valueOf(0));
            cash.setOtherFee(BigDecimal.valueOf(0));

            //银行相关信息
            cash.setBankName(user.getBankName());
            cash.setBankBrance(user.getBankName());
            cash.setBankAccount(user.getBankAccount());
            cash.setAccountName(user.getAccountHolder());
            cash.setStat(0);

            //到账金额，转账金额减手续费
            BigDecimal subtract1 = money.subtract(fee);
            cash.setCreditedMoney(subtract1);

            //计算到账时间
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            //获得当天几号
            int dayOfMonth = dateTime.getDayOfMonth();
            //获得这个月月份
            int month = dateTime.getMonthValue();
            int year = dateTime.getYear();
            LocalDateTime ldt = null;
            String date;
            if(dayOfMonth < 10 || dayOfMonth >= 30){
                date = year+"-" + month +"-20 00:00:00";
                ldt = LocalDateTime.parse(date,df);
            }else if(dayOfMonth < 20){
                month+=1;
                if(month > 12){
                    month = 1;
                    year += 1;
                }
                date = year+"-" + month +"-10 00:00:00";
                ldt = LocalDateTime.parse(date,df);
            }else if(dayOfMonth < 30){
                month+=1;
                if(month > 12){
                    month = 1;
                    year += 1;
                }
                date = year+"-" + month +"-20 00:00:00";
                ldt = LocalDateTime.parse(date,df);
            }

            cash.setCreditedTime(ldt);
            cash.setOperator(user.getUserName());

            cashMapper.insertSelective(cash);

            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("提现成功！");
        }else{
            code.setCode(ResponseCode.FAIL);
            code.setMsg("系统异常，提现失败，请联系客服！");
        }
        return code;
    }

    @Override
    public ResponseCode showCashByEndDate(AccountDto accountDto) {
        ResponseCode code = new ResponseCode();
        PageHelper.startPage(accountDto.getPage(),accountDto.getLimit());

        List<UserCash> userCashList =  cashMapper.showCashByEndDateAndUserID(accountDto);

        PageInfo<UserCash> pageInfo = new PageInfo<>(userCashList);
        code.setCode(0);
        code.setData(pageInfo.getList());
        code.setCount(pageInfo.getTotal());
        return code;
    }
}
