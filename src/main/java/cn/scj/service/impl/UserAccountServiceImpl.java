package cn.scj.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.scj.dto.AccountDto;
import cn.scj.dto.ResponseCode;
import cn.scj.mapper.UserAccountMapper;
import cn.scj.model.UserAccount;
import cn.scj.service.UserAccountService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountMapper accountMapper;

    @Override
    public ResponseCode showAccountByEndDate(AccountDto accountDto) {
        ResponseCode code = new ResponseCode();
        PageHelper.startPage(accountDto.getPage(),accountDto.getLimit());
        List<UserAccount> accountList = accountMapper.showAccountByEndDate(accountDto);

        PageInfo<UserAccount> pageInfo = new PageInfo<>(accountList);
        code.setData(pageInfo.getList());
        code.setCode(0);
        code.setCount(pageInfo.getTotal());
        return code;
    }
}
