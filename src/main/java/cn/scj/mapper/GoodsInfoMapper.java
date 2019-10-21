package cn.scj.mapper;

import cn.scj.model.GoodsInfo;
import cn.scj.model.GoodsInfoWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfoWithBLOBs record);

    int insertSelective(GoodsInfoWithBLOBs record);

    GoodsInfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfoWithBLOBs record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfoWithBLOBs> findList(String goodsName);

    GoodsInfoWithBLOBs findBySn(@Param("goodsSn") String goodsSn);

    void dels(List<Integer> ids);
}