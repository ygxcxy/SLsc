package cn.scj.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * goods_pack_affiliated
 * @author 
 */
@Setter
@Getter
public class GoodsPackAffiliated implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 商品套餐主表ID
     */
    private Long goodsPackId;

    /**
     * 商品ID
     */
    private Long goodsInfoId;

    /**
     * 商品数量
     */
    private Integer goodsNum;

}