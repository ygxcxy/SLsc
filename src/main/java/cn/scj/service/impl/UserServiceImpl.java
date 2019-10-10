package cn.scj.service.impl;

import cn.scj.dto.PwdDto;
import cn.scj.dto.ResponseCode;
import cn.scj.dto.UidDto;
import cn.scj.mapper.AuUserMapper;
import cn.scj.model.AuUser;
import cn.scj.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname UserServiceImpl
 * @Description TODO
 * @Date 2019/10/7 16:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuUserMapper userMapper;

    @Override
    public void save(AuUser user) {
        userMapper.save(user);
    }

    @Override
    public AuUser findUserName(String userName) {

        return userMapper.findUserName(userName);
    }

    @Override
    public AuUser findLoginCode(String loginCode) {

        return userMapper.findLoginCode(loginCode);
    }

    @Override
    public ResponseCode LoginCodeupdatePwd(PwdDto pwdDto) {
        ResponseCode responseCode = new ResponseCode();
        userMapper.LoginCodeupdatePwd(pwdDto);
        responseCode.setMsg("更改成功");
        responseCode.setCode(ResponseCode.SUCCESS);
        return responseCode;
    }

    @Override
    public ResponseCode LoginCodeupdatePwd2(PwdDto pwdDto) {
        ResponseCode responseCode = new ResponseCode();
        userMapper.LoginCodeupdatePwd2(pwdDto);
        responseCode.setMsg("更改成功");
        responseCode.setCode(ResponseCode.SUCCESS);
        return responseCode;
    }

    @Override
    public AuUser findAll(Long id) {
        return userMapper.findAll(id);
    }

    @Override
    public void updateUser(AuUser user) {
        userMapper.updateUser(user);

    }

    @Override
    public ResponseCode findAllCode(ResponseCode responseCode,String loginCode) {
        ResponseCode code = new ResponseCode();
        PageHelper.startPage(responseCode.getPage(),responseCode.getLimit());
        List<AuUser> list = userMapper.findAllCode(loginCode);
        PageInfo<AuUser> pageInfo = new PageInfo<>(list);
        code.setCode(0);
        code.setData(list);
        code.setCount(pageInfo.getTotal());
        return code;
    }

    @Override
    public ResponseCode delById(Long id) {
        ResponseCode code = new ResponseCode();
        if(id==null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("ID错误");
        }else{
            userMapper.delById(id);
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("删除成功");
        }

        return code;
    }

    @Override
    public ResponseCode delByIds(List<Integer> ids) {
        ResponseCode code = new ResponseCode();
        if(ids==null){
            code.setCode(ResponseCode.FAIL);
            code.setMsg("ID错误");
        }else{
            userMapper.delByIds(ids);
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("删除成功");
        }
        return code;
    }


}
