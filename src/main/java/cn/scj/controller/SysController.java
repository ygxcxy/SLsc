package cn.scj.controller;

import cn.scj.component.FileConfig;
import cn.scj.dto.ResponseCode;
import cn.scj.model.*;
import cn.scj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private FunctionService functionService;

    @Autowired
    private AuthorityService authoritySerive;

    @Autowired
    private GoodsInfoService goodsInfoService;

   @Autowired
   private GoodsPackService goodsPackService;


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
        return "redirect:/sys/user/to/list";
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
        return "redirect:/sys/user/to/list";
    }



    /**
     * todo
     * @param model
     * @param session
     * @return
     * 权限管理
     */
    @RequestMapping("fun/to/list")
    public String funList(Model model,HttpSession session){
        /**
         * function表
         */
        List<Function> f = functionService.findAll();
        /**
         * 第一父级
         */
        List<Function> functions = functionService.findFirst(0);
        /**
         * Authority
         */
        List<Authority> authorities = authoritySerive.findAll();
        List<Long> i = new ArrayList<>();

        for (Function o:functions){
              i.add(o.getId());
        }
        /**
         * 二級
         */
        List<Function> functionSecond = functionService.findSecond(i);
        model.addAttribute("second",functionSecond);
        model.addAttribute("func",f);
        model.addAttribute("auth",authorities);
        model.addAttribute("first",functions);
        AuUser auUser = (AuUser) session.getAttribute("user");
        model.addAttribute("user",auUser);
        return "sys/functionList";
    }

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("role/list")
    @ResponseBody
    public ResponseCode roleList(@RequestParam(value = "code",defaultValue = "")String code){
        List<Role> roles = roleService.findCode(code);
        ResponseCode responseCode = new ResponseCode();
        responseCode.setCode(0);
        responseCode.setData(roles);
        return responseCode;
    }


    /**
     * 角色删除
     * @param ids
     * @return
     */
    @RequestMapping("role/del")
    @ResponseBody
    public ResponseCode roleDel(@RequestBody List<Integer> ids){
        ResponseCode code = roleService.delById(ids);
        return code;
    }
    /**
     * 挑转到角色界面
     * @return
     */
    @RequestMapping("role/to/list")
    public String roleToList(){

        return "sys/roleList";
    }

    /**
     * 挑转到添加角色界面
     * @return
     */
    @RequestMapping("role/to/add")
    public String roleToAdd(){

        return "sys/roleAdd";
    }

    /**
     * @param role
     * 添加角色
     * @return
     */
    @RequestMapping("role/add")
    public String roleAdd(Role role,HttpSession session){

        AuUser user = (AuUser) session.getAttribute("user");
        role.setCreateDate(LocalDateTime.now());
        role.setCreatedBy(user.getLoginCode());
        roleService.save(role);
        return "redirect:/sys/role/to/list";
    }
    /**
     * 挑转到角色修改界面
     * @return
     */
    @RequestMapping("role/to/updateRole")
    public String roleToUpdateRole(HttpSession session,Model model){
        AuUser user = (AuUser) session.getAttribute("user");
        Role role = roleService.findById(user.getRoleId());
        model.addAttribute("role",role);
        return "sys/updateRole";
    }
    /**
     * 角色修改
     * @return
     */
    @RequestMapping("role/updateRole")
    public String UpdateRole(Role role){
        roleService.update(role);


        return "redirect:/sys/role/to/list";
    }
    /**
     * 跳转到商品管理页面
     *
     * @return
     */
    @RequestMapping("goods/to/list")
    public String goodsToList() {
        return "sys/goodsList";
    }

    /**
     * 商品管理
     * @param goodsName
     * @param code
     * @return
     */
    @RequestMapping("goods/list")
    @ResponseBody
    public ResponseCode goodsList(@RequestParam(value = "goodsName",defaultValue = "")String goodsName,ResponseCode code){
        ResponseCode responseCode = goodsInfoService.findList(code,goodsName);
        return responseCode;
    }

    /**
     * 跳转到商品添加页面
     * @return
     */
    @RequestMapping("goods/to/add")
    public String goodsToAdd(){
        return "sys/goodsAdd";
    }

    /**
     * 商品添加
     * @param goodsInfo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("goods/add")
    public String goodsAdd(GoodsInfoWithBLOBs goodsInfo,Model model,HttpSession session){
        AuUser user = (AuUser) session.getAttribute("user");
        goodsInfo.setCreatedBy(user.getLoginCode());
        goodsInfo.setCreateTime(LocalDateTime.now());
        GoodsInfoWithBLOBs goodsInfoWithBLOBs = goodsInfoService.findBySn(goodsInfo.getGoodsSn());
        if(goodsInfoWithBLOBs!=null){
            return "redirect:/sys/goods/to/add";
        }
        goodsInfoService.insertGoods(goodsInfo);
        return "redirect:/sys/goods/to/list";
    }

    /**
     * 商品删除
     * @param ids
     * @return
     */
    @RequestMapping("goods/dels")
    @ResponseBody
    public ResponseCode del(@RequestBody List<Integer> ids){
        ResponseCode responseCode = goodsInfoService.dels(ids);
        return responseCode;
    }

    /**
     * 商品查看
     * @param id
     * @return
     */
    @RequestMapping("goods/to/query")
    public String query(@RequestParam("id")Long id,Model model){
       GoodsInfoWithBLOBs goodsInfoWithBLOBs = goodsInfoService.query(id);
       model.addAttribute("goods",goodsInfoWithBLOBs);
        return "sys/queryGoods";
    }

    /**
     * 跳转到商品修改界面
     * @param id
     * @return
     */
    @RequestMapping("goods/to/updateGoods")
    public String toUpdateGoods(@RequestParam("id")Long id,Model model){
        GoodsInfoWithBLOBs goodsInfoWithBLOBs = goodsInfoService.query(id);
        model.addAttribute("goods",goodsInfoWithBLOBs);
        return "sys/goodsUpdate";
    }

    /**
     * 商品修改
     * @param goodsInfoWithBLOBs
     * @return
     */
    @RequestMapping("goods/updateGoods")
    public String updateGoods(GoodsInfoWithBLOBs goodsInfoWithBLOBs){
        //sss
        goodsInfoWithBLOBs.setLastUpdateTime(LocalDateTime.now());
        goodsInfoService.updateGoods(goodsInfoWithBLOBs);
        return "redirect:/sys/goods/to/list";
    }

    @RequestMapping("pack/list")
    public String packList(){

        return "";
    }
}
