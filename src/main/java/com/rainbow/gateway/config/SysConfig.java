package com.rainbow.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yanzhihao
 * @since 2022/3/18
 */
@Data
@Component
@ConfigurationProperties(prefix = "sys.config")
public class SysConfig {

    private List<String> ignoreUrlList;
}
