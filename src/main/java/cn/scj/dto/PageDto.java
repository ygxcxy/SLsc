package cn.scj.dto;

import lombok.Data;

/**
 * @author by Shaochenjie
 * @Classname PageDto
 * @Description TODO
 * @Date 2019/10/9 15:47
 */
@Data
public class PageDto {

    private Integer page=1;

    private Integer limit=5;

    private Long count;
}
