package cn.scj.service;

import cn.scj.model.DataDictionary;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname DataDictionaryService
 * @Description TODO
 * @Date 2019/10/7 20:20
 */
public interface DataDictionaryService {
    List<DataDictionary> findByTypeCode(String card_type);

    DataDictionary findByValueId(String cardType, String card_type);
}
