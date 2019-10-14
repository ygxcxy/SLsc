package cn.scj.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * au_function
 * @author 
 */
@Setter
@Getter
public class Function implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 功能编码
     */
    private String functionCode;

    /**
     * 功能名称
     */
    private String functionName;

    /**
     * 功能URL
     */
    private String funcUrl;

    private Long parentId;

    /**
     * 创建时间
     */
    private LocalDateTime creationTime;

}