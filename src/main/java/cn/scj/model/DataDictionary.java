package cn.scj.model;

import lombok.Data;

import java.io.Serializable;

/**
 * data_dictionary
 * @author 
 */
@Data
public class DataDictionary implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 类型编码
     */
    private String typeCode;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 类型值ID
     */
    private Integer valueId;

    /**
     * 类型值Name
     */
    private String valueName;


}