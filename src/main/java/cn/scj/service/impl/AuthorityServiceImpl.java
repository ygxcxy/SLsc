package cn.scj.service.impl;

import cn.scj.mapper.AuthorityMapper;
import cn.scj.model.Authority;
import cn.scj.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname AuthoritySeriveImpl
 * @Description TODO
 * @Date 2019/10/11 12:53
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Authority> findAll() {
        return authorityMapper.findAll();

    }
}
