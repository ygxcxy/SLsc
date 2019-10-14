package cn.scj.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user_account_201404
 * @author 
 */
@Setter
@Getter
public class UserAccount implements Serializable {
    /**
     * 主键ID
     */
    private Integer accountId;

    /**
     * 用户主键ID
     */
    private Integer userId;

    //摘要
    private String actionDesc;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime accountDate;

    /**
     * 总账状态
     */
    private Integer stat;

    /**
     * 基本入账
     */
    private Double baseIn;

    /**
     * 基本出账
     */
    private Double baseOut;

    /**
     * 基本余额
     */
    private Double baseBalance;

    /**
     * 重消入账
     */
    private Double repeatIn;

    /**
     * 重消出账
     */
    private Double repeatOut;

    /**
     * 重消余额
     */
    private Double repeatBalance;

    /**
     * 未分红重消PV
     */
    private Integer freePv;

    /**
     * 已分红未领货重消PV
     */
    private Integer alreadyPv;

    /**
     * 已领货重消PV
     */
    private Integer buyPv;

    private static final long serialVersionUID = 1L;

    public String getAccountDateStr(){
        if(accountDate == null){
            return "";
        }
        return accountDate.toLocalDate().toString();
    }

    public String getAccountTimeStr(){
        if(accountDate == null){
            return "";
        }
        return accountDate.toLocalTime().toString().equals("00:00")?"00:00:00":accountDate.toLocalTime().toString();
    }
}