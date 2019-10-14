package cn.scj.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * au_role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createDate;

    /**
     * 是否启用（0、未启用1、启用）
     */
    private Integer isStart;

    /**
     * 创建者
     */
    private String createdBy;

    public String getLastTime1(){
        String i = "";
        if(getCreateDate()!=null){
            i = getCreateDate().toLocalDate().toString()+" "+(getCreateDate().toLocalTime().toString().equals("00:00")?"00:00:00":getCreateDate().toLocalTime().toString());
        }else{
            i="--";
        }
        return i;
    }
}