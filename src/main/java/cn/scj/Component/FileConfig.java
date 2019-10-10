package cn.scj.Component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author by Shaochenjie
 * @Classname FileConfig
 * @Description TODO
 * @Date 2019/10/7 20:53
 */

@Component
@Data
@PropertySource(value = "classpath:config/file.properties")
public class FileConfig {

    @Value("${uploadRootPath}")
    private String uploadRootPath;
}
