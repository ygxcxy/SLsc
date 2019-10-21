package cn.scj.controller;

import cn.scj.dto.AccountDto;
import cn.scj.dto.ResponseCode;
import cn.scj.model.AuUser;
import cn.scj.service.UserCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("cash")
public class UserCashController {

    @Autowired
    private UserCashService cashService;

    @RequestMapping("toCashList")
    public String toCashList(){
        return "accountLog/cashList";
    }


    //申请提现
    @RequestMapping("cashWithdrawal")
    @ResponseBody
    public ResponseCode cashWithdrawal(HttpSession session, @RequestParam("money") BigDecimal money){
        AuUser user = (AuUser) session.getAttribute("user");
        ResponseCode code = cashService.cashWithdrawal(user,money);
        return code;
    }

    //展示提现明细
    @RequestMapping("showCashByEndDate")
    @ResponseBody
    public ResponseCode showCashByEndDate(HttpSession session, AccountDto accountDto){
        AuUser user = (AuUser) session.getAttribute("user");
        accountDto.setUserId(user.getId());
        ResponseCode code = cashService.showCashByEndDate(accountDto);
        return code;
    }
}
