package cn.scj.mapper;

import cn.scj.model.Country;

import java.util.List;

/**
 * @author Shaochenjie
 */
public interface CountryMapper {

    List<Country> findAll();

}