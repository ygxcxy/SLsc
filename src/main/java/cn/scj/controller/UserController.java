package cn.scj.controller;

import cn.scj.Component.FileConfig;
import cn.scj.dto.PwdDto;
import cn.scj.dto.ResponseCode;
import cn.scj.dto.UidDto;
import cn.scj.model.AuUser;
import cn.scj.model.Country;
import cn.scj.model.DataDictionary;
import cn.scj.service.CountryService;
import cn.scj.service.DataDictionaryService;
import cn.scj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname UserController
 * @Description TODO
 * @Date 2019/10/7 16:30
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FileConfig fileConfig;

    @RequestMapping("to/register")
    public String toRegister(Model model){
        List<DataDictionary> dictionaryList =  dataDictionaryService.findByTypeCode("CARD_TYPE");
        List<Country> countries = countryService.findAll();
        model.addAttribute("dic",dictionaryList);
        model.addAttribute("coun",countries);
        return "user/index";
    }

    @RequestMapping("register")
    public String Register(
            @RequestParam("idCardPicPosPathFile")MultipartFile idCardPicPosPathFile,
            @RequestParam("idCardPicNegPathFile")MultipartFile idCardPicNegPathFile,
            @RequestParam("bankPicPathFile")MultipartFile bankPicPathFile, AuUser user
            ){
        File idCardPicPosPath = new File(fileConfig.getUploadRootPath()+idCardPicPosPathFile.getOriginalFilename());
        File idCardPicNegPath= new File(fileConfig.getUploadRootPath()+idCardPicNegPathFile.getOriginalFilename());
        File bankPicPath = new File(fileConfig.getUploadRootPath()+bankPicPathFile.getOriginalFilename());
       user.setIdCardPicPosPath(idCardPicPosPathFile.getOriginalFilename());
       user.setIdCardPicNegPath(idCardPicNegPathFile.getOriginalFilename());
       user.setBankPicPath(bankPicPathFile.getOriginalFilename());
        try {
            idCardPicPosPathFile.transferTo(idCardPicPosPath);
            idCardPicNegPathFile.transferTo(idCardPicNegPath);
            bankPicPathFile.transferTo(bankPicPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setCreateTime(LocalDateTime.now());
        DataDictionary dataDictionary = dataDictionaryService.findByValueId(user.getCardType(),"CARD_TYPE");
        user.setCardTypeName(dataDictionary.getValueName());
        user.setIsStart(2);
        user.setUserType("1");
        user.setUserTypeName("注册会员");
        user.setRoleId(2L);
        user.setRoleName("会员");
        AuUser user1 = userService.findUserName(user.getUserName());
        if(user1!=null){
            user.setReferId(user1.getId());
            user.setReferCode(user1.getLoginCode());
            user.setPassword("1");
            user.setPassword2("1");
            userService.save(user);
        }
        return "forward:to/register";
    }

    @RequestMapping("to/login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("login")
    @ResponseBody
    public ResponseCode Login(AuUser user, HttpSession session){
        AuUser user1=userService.findLoginCode(user.getLoginCode());
        ResponseCode responseCode = new ResponseCode();
        if(user1==null){
            //没有这个账户
            responseCode.setCode(ResponseCode.FAIL);
            responseCode.setMsg("没有这个账户");
        }else{
            if(user1.getPassword().equals(user.getPassword())){
                //登录成功
                user1.setPassword("");
                user1.setPassword2("");
                session.setAttribute("user",user1);
                responseCode.setCode(ResponseCode.SUCCESS);
            }else{
                //密码错误
                responseCode.setCode(ResponseCode.FAIL);
                responseCode.setMsg("密码错误,请重新输入.");
            }
        }
        return responseCode;
    }
    @RequestMapping("main")
    public String main(){

        return "main";
    }
    @RequestMapping("to/updatePwd")
    public String toUpdatePwd(){
        return "user/updatePwd";
    }
    @RequestMapping("updatePwd")
    @ResponseBody
    public ResponseCode updatePwd(PwdDto pwdDto,HttpSession session){
        ResponseCode responseCode = new ResponseCode();
        AuUser user = (AuUser) session.getAttribute("user");

        AuUser user1 = userService.findLoginCode(user.getLoginCode());
        if(!user1.getPassword().equals(pwdDto.getOldPwd())){
            //旧密码输入错误
            responseCode.setCode(ResponseCode.FAIL);
            responseCode.setMsg("旧密码输入错误");
        }else{
            pwdDto.setLoginCode(user1.getLoginCode());
            responseCode = userService.LoginCodeupdatePwd(pwdDto);

        }
        return responseCode;
    }
    @RequestMapping("updatePwd2")
    @ResponseBody
    public ResponseCode updatePwd2(PwdDto pwdDto,HttpSession session){
        ResponseCode responseCode = new ResponseCode();
        AuUser user = (AuUser) session.getAttribute("user");

        AuUser user1 = userService.findLoginCode(user.getLoginCode());
        if(!user1.getPassword2().equals(pwdDto.getOldPwd2())){
            //旧密码输入错误
            responseCode.setCode(ResponseCode.FAIL);
            responseCode.setMsg("旧密码输入错误");
        }else{
            pwdDto.setLoginCode(user1.getLoginCode());
            responseCode = userService.LoginCodeupdatePwd2(pwdDto);

        }
        return responseCode;
    }
    @RequestMapping("to/updateUser")
    public String toUpdateUser(Model model,HttpSession session){
        AuUser user = (AuUser) session.getAttribute("user");
        AuUser user1 = userService.findAll(user.getId());
        List<DataDictionary> cardType = dataDictionaryService.findByTypeCode("CARD_TYPE");
        model.addAttribute("card",cardType);
        model.addAttribute("user",user1);
        List<Country> countries = countryService.findAll();
        model.addAttribute("coun",countries);
        return "user/updateUser";
    }
    @RequestMapping("updateUser")
    public String updateUser(
        @RequestParam("idCardPicPosPathFile")MultipartFile idCardPicPosPathFile,
        @RequestParam("idCardPicNegPathFile")MultipartFile idCardPicNegPathFile,
        @RequestParam("bankPicPathFile")MultipartFile bankPicPathFile, AuUser user
    ){
        if(idCardPicNegPathFile.getOriginalFilename()!=null &&idCardPicNegPathFile.getOriginalFilename()!=""){
            AuUser use1 = userService.findAll(user.getId());
            File file = new File(fileConfig.getUploadRootPath(),use1.getIdCardPicNegPath());
            file.delete();
            File file1 = new File(fileConfig.getUploadRootPath()+idCardPicNegPathFile.getOriginalFilename());
            user.setIdCardPicNegPath(idCardPicNegPathFile.getOriginalFilename());
            try {
                idCardPicNegPathFile.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(idCardPicPosPathFile.getOriginalFilename()!=null &&idCardPicPosPathFile.getOriginalFilename()!=""){
            AuUser use1 = userService.findAll(user.getId());
            File file = new File(fileConfig.getUploadRootPath(),use1.getIdCardPicPosPath());
            file.delete();
            File f = new File(fileConfig.getUploadRootPath()+idCardPicPosPathFile.getOriginalFilename());
            user.setIdCardPicPosPath(idCardPicPosPathFile.getOriginalFilename());
            try {
                idCardPicPosPathFile.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(bankPicPathFile.getOriginalFilename()!=null &&bankPicPathFile    .getOriginalFilename()!=""){
            AuUser use1 = userService.findAll(user.getId());
            File file = new File(fileConfig.getUploadRootPath(),use1.getBankPicPath());
            file.delete();
            File f = new File(fileConfig.getUploadRootPath()+bankPicPathFile.getOriginalFilename());
            user.setBankPicPath(bankPicPathFile.getOriginalFilename());
            try {
                bankPicPathFile.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DataDictionary cardType = dataDictionaryService.findByValueId(user.getCardType(),"CARD_TYPE");
        user.setCardTypeName(cardType.getValueName());
        userService.updateUser(user);
        return "forward:to/updateUser";
    }
    @RequestMapping("to/list")
    public String toList(){
        return "user/list";
    }
    @RequestMapping("list")
    @ResponseBody
    public ResponseCode list(ResponseCode responseCode,@RequestParam(value = "loginCode",defaultValue = "")String loginCode){
        ResponseCode code = userService.findAllCode(responseCode,loginCode);
        return code;
    }
    @RequestMapping("del/{id}")
    @ResponseBody
    public ResponseCode del(ResponseCode responseCode,@PathVariable(value = "id")Long id){
        ResponseCode code = userService.delById(id);
        return code;
    }
    @RequestMapping("dels")
    @ResponseBody
    public ResponseCode delS(@RequestBody List<Integer> ids){
        ResponseCode code = userService.delByIds(ids);
        return code;
    }
}
