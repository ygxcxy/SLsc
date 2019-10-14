package cn.scj.service;

import cn.scj.dto.ResponseCode;
import cn.scj.model.GoodsInfoWithBLOBs;

import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname GoodsInfoService
 * @Description TODO
 * @Date 2019/10/14 10:11
 */
public interface GoodsInfoService {
    void insertGoods(GoodsInfoWithBLOBs goodsInfo);

    ResponseCode findList(ResponseCode code, String goodsName);

    GoodsInfoWithBLOBs findBySn(String goodsSn);

    ResponseCode dels(List<Integer> ids);

    GoodsInfoWithBLOBs query(Long id);

    int updateGoods(GoodsInfoWithBLOBs goodsInfoWithBLOBs);
}
