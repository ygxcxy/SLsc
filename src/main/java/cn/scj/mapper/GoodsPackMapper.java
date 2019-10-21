package cn.scj.mapper;

import cn.scj.dto.ResponseCode;
import cn.scj.model.GoodsPack;

public interface GoodsPackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsPack record);

    int insertSelective(GoodsPack record);

    GoodsPack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsPack record);

    int updateByPrimaryKeyWithBLOBs(GoodsPack record);

    int updateByPrimaryKey(GoodsPack record);

    ResponseCode showGoodsPackByType(GoodsPack goodsPack);
}