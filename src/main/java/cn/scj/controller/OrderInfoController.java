package cn.scj.controller;

import cn.scj.dto.ResponseCode;

import cn.scj.model.*;
import cn.scj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DataDictionaryService dictionaryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private GoodsPackService goodsPackService;

    
    @RequestMapping("toGetCode")
    public String toGetCode(){
        return "ShoppingManagement/getCode";
    }

    @RequestMapping("toRePurchase")
    public String toRePurchase(){
        return "ShoppingManagement/rePurchase";
    }


    //检查输入的用户名是否正确
    @RequestMapping("checkUserByCode")
    @ResponseBody
    public ResponseCode checkUserByCode(@RequestParam("shoppingCode")String shoppingCode){
        ResponseCode code = new ResponseCode();
        AuUser user = orderInfoService.queryUserByCard(shoppingCode);
        if(user == null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("该用户不存在请输入正确的用户名！");
        }else if(user.getUserType().equals("")){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("该用户为管理员，请输入其他用户名！");
        }else{
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("");
        }
        return code;
    }


    @RequestMapping("toTaxationForm")
    public String toTaxationForm(Model model, @RequestParam("shoppingCode")String shoppingCode, HttpSession session){
        AuUser sessionUser = (AuUser) session.getAttribute("user");
        AuUser shoppingUser = orderInfoService.queryUserByCard(shoppingCode);
        Account account = accountService.queryByUserId(sessionUser.getId());

        if(shoppingUser == null){
            return "";
        }else if(shoppingUser.getUserType().equals("")){
            return "";
        }else{
            List<DataDictionary> byTypeCode = dictionaryService.findByTypeCode("EXPRESS_TYPE");
            List<Country> countryList = countryService.findAll();

            model.addAttribute("shoppingUser",shoppingUser);
            model.addAttribute("account",account);
            model.addAttribute("byTypeCode",byTypeCode);
            model.addAttribute("countryList",countryList);
        }
        return "ShoppingManagement/taxationForm";
    }

    @RequestMapping("addOrderInfo")
    @ResponseBody
    public ResponseCode addOrderInfo(OrderInfo orderInfo){
        ResponseCode code = orderInfoService.addOrderInfo(orderInfo);
        return code;
    }

    @RequestMapping("showGoodsPackByType")
    @ResponseBody
    public ResponseCode showGoodsPackByType(GoodsPack goodsPack){
        ResponseCode code = goodsPackService.showGoodsPackByType(goodsPack);
        return code;
    }
}
