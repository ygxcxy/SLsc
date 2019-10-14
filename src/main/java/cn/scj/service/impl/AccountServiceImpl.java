package cn.scj.service.impl;

import cn.scj.mapper.AccountMapper;
import cn.scj.model.Account;
import cn.scj.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account queryByUserId(Long userId) {
        return accountMapper.queryByUserId(userId);
    }
}
