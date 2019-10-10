package cn.scj.service.impl;

import cn.scj.mapper.DataDictionaryMapper;
import cn.scj.model.DataDictionary;
import cn.scj.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname DataDictionaryServiceImpl
 * @Description TODO
 * @Date 2019/10/7 20:20
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionary> findByTypeCode(String card_type) {

        return dataDictionaryMapper.findByTypeCode(card_type);
    }

    @Override
    public DataDictionary findByValueId(String cardType, String card_type) {

        return dataDictionaryMapper.findByValueId(cardType,card_type);
    }
}
