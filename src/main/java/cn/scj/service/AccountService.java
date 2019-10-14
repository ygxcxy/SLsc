package cn.scj.service;

import cn.scj.model.Account;

public interface AccountService {
    Account queryByUserId(Long userId);
}
