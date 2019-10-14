package cn.scj.controller;

import cn.scj.dto.AccountDto;
import cn.scj.dto.ResponseCode;
import cn.scj.model.Account;
import cn.scj.model.AccountRecord;
import cn.scj.model.AuUser;
import cn.scj.service.AccountRecordService;
import cn.scj.service.AccountService;
import cn.scj.service.UserAccountService;
import cn.scj.service.UserService;
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
        AuUser user = (AuUser) session.getAttribute("user");
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
        AuUser user = (AuUser) session.getAttribute("user");
        accountDto.setUserId(user.getId());
        ResponseCode code = UserAccountService.showAccountByEndDate(accountDto);
        return code;
    }

    //充值
    @RequestMapping("recharge")
    @ResponseBody
    public ResponseCode recharge(AccountRecord record, HttpSession session){
        AuUser user = (AuUser) session.getAttribute("user");
        Account account = accountService.queryByUserId(user.getId());

        ResponseCode code = recordService.recharge(account,record);

        return code;
    }


    //检查转账的用户是否合法来了
    @RequestMapping("checkPassword2")
    @ResponseBody
    public ResponseCode checkPassword2(HttpSession session, @RequestParam("password2")String password2){
        AuUser user = (AuUser) session.getAttribute("user");
        ResponseCode code = userService.checkPassword2(user.getId(),password2);
        return code;
    }
}
