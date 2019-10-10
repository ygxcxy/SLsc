package cn.scj.service.impl;

import cn.scj.mapper.CountryMapper;
import cn.scj.model.Country;
import cn.scj.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname CountryServiceImpl
 * @Description TODO
 * @Date 2019/10/7 20:20
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<Country> findAll() {

        return countryMapper.findAll();
    }
}
