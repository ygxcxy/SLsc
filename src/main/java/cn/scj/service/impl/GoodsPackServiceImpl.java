package cn.scj.service.impl;

import cn.scj.mapper.GoodsPackMapper;
import cn.scj.model.GoodsPack;
import cn.scj.service.GoodsPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by Shaochenjie
 * @Classname GoodsPackServiceImpl
 * @Description TODO
 * @Date 2019/10/14 16:28
 */
@Service
public class GoodsPackServiceImpl implements GoodsPackService {

    @Autowired
    private GoodsPackMapper goodsPackMapper;
}
