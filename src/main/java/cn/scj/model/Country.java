package cn.scj.model;

import lombok.Data;

import java.io.Serializable;

/**
 * country
 * @author 
 */
@Data
public class Country implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 对应七大陆continent表的id
     */
    private Integer continentId;

    /**
     * 英文常用标准名称，主要用于显示
     */
    private String name;

    /**
     * 对应于英文标准名称的小写，主要用于搜索比较
     */
    private String lowerName;

    /**
     * 英文缩写名称，全大写
     */
    private String countryCode;

    /**
     * 英文标准名称全称
     */
    private String fullName;

    /**
     * 中文常用标准名称，通常简称
     */
    private String cname;

    /**
     * 中文全称名称，非缩写
     */
    private String fullCname;

    /**
     * 七大洲
     */
    private String continent;

    /**
     * 备注字段，保留
     */
    private String remark;

}