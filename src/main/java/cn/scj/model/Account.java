package cn.scj.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * stg_d_account
 * @author 
 */
@Setter
@Getter
public class Account implements Serializable {
    private Long id;

    /**
     * 关联用户ID
     */
    private Long userId;

    /**
     * 账户名称
     */
    private String name;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}