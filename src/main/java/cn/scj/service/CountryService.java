package cn.scj.service;

import cn.scj.model.Country;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname CountryService
 * @Description TODO
 * @Date 2019/10/7 20:20
 */
public interface CountryService {
    List<Country> findAll();
}
