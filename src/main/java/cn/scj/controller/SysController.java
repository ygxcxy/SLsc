package cn.scj.controller;

import cn.scj.component.FileConfig;
import cn.scj.dto.ResponseCode;
import cn.scj.model.AuUser;
import cn.scj.model.Country;
import cn.scj.model.DataDictionary;
import cn.scj.model.Role;
import cn.scj.service.CountryService;
import cn.scj.service.DataDictionaryService;
import cn.scj.service.RoleService;
import cn.scj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname SysController
 * @Description TODO
 * @Date 2019/10/10 15:51
 */
@Controller
@RequestMapping("sys")
public class SysController {

    @Autowired
    private UserService userService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FileConfig fileConfig;

    @Autowired
    private RoleService roleService;


    /**
     * 跳转到用户管理页面
     *
     * @return
     */
    @RequestMapping("user/to/list")
    public String toList() {
        return "sys/UserList";
    }

    /**
     * 显示用户管理模块
     *
     * @return
     */
    @RequestMapping("user/list")
    @ResponseBody
    public ResponseCode userList(ResponseCode responseCode, @RequestParam(value = "loginCode", defaultValue = "") String loginCode) {
        ResponseCode code = userService.findAllCode(responseCode, loginCode);
        return code;
    }

    /**
     * 跳转到add界面
     *
     * @param model
     * @return
     */
    @RequestMapping("user/to/add")
    public String userToAdd(Model model, HttpSession session) {
        /**
         * 角色类型
         */
        List<Role> roleList = roleService.findAll();
        model.addAttribute("role", roleList);
        /**
         * 会员类型
         */
        List<DataDictionary> dictionaryList = dataDictionaryService.findByTypeCode("USER_TYPE");
        model.addAttribute("data", dictionaryList);
        //证件类型
        List<DataDictionary> dataDictionaries = dataDictionaryService.findByTypeCode("CARD_TYPE");
        model.addAttribute("dic", dataDictionaries);
        //国家
        List<Country> countries = countryService.findAll();
        model.addAttribute("country", countries);
        return "sys/userAdd";
    }

    @RequestMapping("user/add")
    public String userAdd(
            @RequestParam("idCardPicPosPathFile")MultipartFile idCardPicPosPathFile,
            @RequestParam("idCardPicNegPathFile")MultipartFile idCardPicNegPathFile,
            @RequestParam("bankPicPathFile")MultipartFile bankPicPathFile,
            AuUser user, HttpSession session) {
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
        AuUser user1 = (AuUser) session.getAttribute("user");
        user.setReferId(user1.getId());
        user.setReferCode(user1.getLoginCode());
        DataDictionary cardType = dataDictionaryService.findByValueId(user.getCardType(), "CARD_TYPE");
        Role role = roleService.findById(user.getRoleId());
        DataDictionary userType = dataDictionaryService.findByValueId(user.getUserType(), "USER_TYPE");
        user.setCardTypeName(cardType.getValueName());
        user.setRoleName(role.getRoleName());
        user.setUserTypeName(userType.getValueName());
        AuUser user2 = userService.findUserName(user.getUserName());
        if(user2==null) {
            userService.add(user);
        }
        return "forward:/sys/user/to/list";
    }
    @RequestMapping("to/updateUser")
    public String toUpdateUser(Model model,HttpSession session){
        AuUser user = (AuUser) session.getAttribute("user");
        AuUser user1 = userService.findAll(user.getId());
        //证件类型
        List<DataDictionary> cardType = dataDictionaryService.findByTypeCode("CARD_TYPE");
        model.addAttribute("card",cardType);
        model.addAttribute("user",user1);
        List<Country> countries = countryService.findAll();
        model.addAttribute("coun",countries);
        /**
         * 角色类型
         */
        List<Role> roleList = roleService.findAll();
        model.addAttribute("role", roleList);
        /**
         * 会员类型
         */
        List<DataDictionary> dictionaryList = dataDictionaryService.findByTypeCode("USER_TYPE");
        model.addAttribute("data", dictionaryList);
        return "sys/updateUser";
    }

    @RequestMapping("user/userType")
    @ResponseBody
    public ResponseCode userType(){

        ResponseCode responseCode = new ResponseCode();
        List<DataDictionary> dictionaryList = dataDictionaryService.findByTypeCode("USER_TYPE");
        responseCode.setData(dictionaryList);
        return responseCode;
    }
    @RequestMapping("user/updateUser")
    public String updateUser(
            @RequestParam("idCardPicPosPathFile")MultipartFile idCardPicPosPathFile,
            @RequestParam("idCardPicNegPathFile")MultipartFile idCardPicNegPathFile,
            @RequestParam("bankPicPathFile")MultipartFile bankPicPathFile, AuUser user,HttpSession session
    ){
        if(idCardPicNegPathFile.getOriginalFilename()!=null &&idCardPicNegPathFile.getOriginalFilename()!=""){
            AuUser use1 = userService.findAll(user.getId());
            if(use1.getIdCardPicNegPath()!=null){
            File file = new File(fileConfig.getUploadRootPath(),use1.getIdCardPicNegPath());

                file.delete();
            }

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
            if(use1.getIdCardPicPosPath()!=null){
            File file = new File(fileConfig.getUploadRootPath(),use1.getIdCardPicPosPath());

                file.delete();
            }
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
            if(use1.getBankPicPath()!=null){
            File file = new File(fileConfig.getUploadRootPath(),use1.getBankPicPath());
                file.delete();
            }
            File f = new File(fileConfig.getUploadRootPath()+bankPicPathFile.getOriginalFilename());
            user.setBankPicPath(bankPicPathFile.getOriginalFilename());
            try {
                bankPicPathFile.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DataDictionary cardType = dataDictionaryService.findByValueId(user.getCardType(), "CARD_TYPE");
        Role role = roleService.findById(user.getRoleId());
        DataDictionary userType = dataDictionaryService.findByValueId(user.getUserType(), "USER_TYPE");

        user.setCardTypeName(cardType.getValueName());
        user.setRoleName(role.getRoleName());
        user.setUserTypeName(userType.getValueName());
        user.setLastUpdateTime(LocalDateTime.now());
        //推荐人
        AuUser user3 = (AuUser) session.getAttribute("user");
        user.setReferId(user3.getId());
        user.setReferCode(user3.getLoginCode());
        userService.updateUser(user);
        return "forward:/sys/user/to/list";
    }

}
