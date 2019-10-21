package cn.scj.service.impl;

import cn.scj.dto.ResponseCode;
import cn.scj.mapper.AccountMapper;
import cn.scj.mapper.AuUserMapper;
import cn.scj.mapper.DataDictionaryMapper;
import cn.scj.mapper.OrderInfoMapper;
import cn.scj.model.Account;
import cn.scj.model.AuUser;
import cn.scj.model.OrderInfo;
import cn.scj.service.OrderInfoService;
import cn.scj.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private AuUserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private DataDictionaryMapper dictionaryMapper;

    @Override
    public AuUser queryUserByCard(String shoppingCode) {
        return userMapper.queryUserByCard(shoppingCode);
    }

    @Override
    public ResponseCode addOrderInfo(OrderInfo orderInfo) {
        ResponseCode code = new ResponseCode();


        Account account = accountMapper.queryByUserId(orderInfo.getBuyUserId());



        if(account.getBalance().compareTo(orderInfo.getTotalPrice()) == -1){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("账户余额不足，请先汇款充值！");
            return code;
        }

        BigDecimal subtract = account.getBalance().subtract(orderInfo.getTotalPrice());
        account.setBalance(subtract);
        account.setUpdateTime(LocalDateTime.now());

        int i = accountMapper.updateByPrimaryKeySelective(account);

        if(i > 0){

            int valueId = dictionaryMapper.findValueIdByValue("USER_TYPE",orderInfo.getShipNote());
            AuUser user = userMapper.queryUserById(orderInfo.getPickUserId());

            user.setUserType(String.valueOf(valueId));
            user.setUserTypeName(orderInfo.getShipNote());
            user.setLastUpdateTime(LocalDateTime.now());
            userMapper.updateUser(user);

            orderInfo.setOrderTime(LocalDateTime.now());
            orderInfo.setOrderSn(MathUtil.getRandom1());
            orderInfo.setRepeatPv(BigDecimal.valueOf(0));
            orderInfo.setShipFre(BigDecimal.valueOf(0));
            orderInfo.setTax(BigDecimal.valueOf(0));
            orderInfo.setStat(0);
            orderInfo.setShipNum(orderInfo.getOrderSn());

            //计算发货时间
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
            orderInfo.setShipTime(ldt);
            orderInfo.setTypeId(1);
            orderInfo.setTypeName("报单套餐");
            orderInfo.setShipNote("会员升级为：" + orderInfo.getShipNote());

            int row = orderInfoMapper.insertSelective(orderInfo);
            if(row > 0){
                code.setCode(ResponseCode.SUCCESS);
                code.setMsg("报单购货成功！");
            }else{
                code.setCode(ResponseCode.FAIL);
                code.setMsg("报单购货失败！");
            }
        }else{
            code.setCode(ResponseCode.FAIL);
            code.setMsg("系统异常,报单购货失败！");
        }


        return code;
    }


}
