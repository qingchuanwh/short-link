package cn.sequoiacap.shortlink;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Bob Chin
 * created on 2022/1/11
 */
@Configuration
@PropertySource(value="classpath:/application.properties")
@Getter
public class Config {
    /**
     * length of short link generated
     */
    @Value("${app.link.length}")
    private int linkLength;
}
