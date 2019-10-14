package cn.scj.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * au_authority
 * @author 
 */
@Setter
@Getter
public class Authority implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 功能ID
     */
    private Long functionId;

    /**
     * 用户类型ID
     */
    private Long userTypeId;

    /**
     * 创建时间
     */
    private LocalDateTime creationTime;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 权限类
     */
    private Function function;

}