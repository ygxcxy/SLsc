package cn.scj.controller;

import com.xshideu.dto.AccountDto;
import com.xshideu.dto.ResponseCode;
import com.xshideu.model.Account;
import com.xshideu.model.AccountRecord;
import com.xshideu.model.AuUser;
import com.xshideu.service.AccountRecordService;
import com.xshideu.service.AccountService;
import com.xshideu.service.UserAccountService;
import com.xshideu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService UserAccountService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRecordService recordService;

    @RequestMapping("toAccountLog")
    public String toAccountLog(){
        return "accountLog/asicAccount";
    }

    @RequestMapping("toInternalTransfer")
    public String toInternalTransfer(HttpSession session, Model model){
        AuUser user = (AuUser) session.getAttribute("sessionUser");
        Account account = accountService.queryByUserId(user.getId());
        model.addAttribute("dAccount",account);
        return "accountLog/internalTransfer";
    }
    //去汇款充值页面
    @RequestMapping("toRecharge")
    public String toRecharge(){
        return "accountLog/recharge";
    }

    @RequestMapping("showAccountByEndDate")
    @ResponseBody
    public ResponseCode showAccountByEndDate(HttpSession session, AccountDto accountDto){
        AuUser user = (AuUser) session.getAttribute("sessionUser");
        accountDto.setUserId(user.getId());
        ResponseCode code = UserAccountService.showAccountByEndDate(accountDto);
        return code;
    }

    //充值
    @RequestMapping("recharge")
    @ResponseBody
    public ResponseCode recharge(AccountRecord record,HttpSession session){
        AuUser user = (AuUser) session.getAttribute("sessionUser");
        Account account = accountService.queryByUserId(user.getId());

        ResponseCode code = recordService.recharge(account,record);

        return code;
    }


    //检查转账的用户是否合法
    @RequestMapping("checkPassword2")
    @ResponseBody
    public ResponseCode checkPassword2(HttpSession session,@RequestParam("password2")String password2){
        AuUser user = (AuUser) session.getAttribute("sessionUser");
        ResponseCode code = userService.checkPassword2(user.getId(),password2);
        return code;
    }
}
