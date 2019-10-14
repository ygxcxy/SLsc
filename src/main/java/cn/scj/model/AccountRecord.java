package cn.scj.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * stg_d_account_record
 * @author 
 */
@Getter
@Setter
public class AccountRecord implements Serializable {
    private Long id;

    /**
     * 账户id
     */
    private Long accountId;

    /**
     * 交易时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 交易后余额
     */
    private BigDecimal balance;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}