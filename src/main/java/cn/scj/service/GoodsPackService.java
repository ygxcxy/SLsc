package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.GoodsPack;
import org.springframework.stereotype.Service;

/**
 * @author by Shaochenjie
 * @Classname GoodsPackService
 * @Description TODO
 * @Date 2019/10/14 16:27
 */
@Service
public interface GoodsPackService {
    ResponseCode showGoodsPackByType(GoodsPack goodsPack);
}
