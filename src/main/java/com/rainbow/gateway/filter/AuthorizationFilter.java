package com.rainbow.gateway.filter;

import com.rainbow.gateway.common.ResultCode;
import com.rainbow.gateway.config.SysConfig;
import com.rainbow.gateway.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * @author yanzhihao
 * @since 2022/3/17
 */
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {

    @Autowired
    private SysConfig sysConfig;

    /**
     * JWT令牌的服务
     */
    @Autowired
    private TokenStore tokenStore;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        //白名单放行
        if (checkUrls(sysConfig.getIgnoreUrlList(), requestUrl)) {
            return chain.filter(exchange);
        }

        //检查token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(token)) {
            return Mono.error(new InvalidTokenException(ResultCode.INVALID_TOKEN.getMessage()));
        }
        token = token.split(" ")[1];
        if (StringUtils.isBlank(token)) {
            return Mono.error(new InvalidTokenException(ResultCode.INVALID_TOKEN.getMessage()));
        }


        OAuth2AccessToken oAuth2AccessToken;
        try {
            //解析
            oAuth2AccessToken = tokenStore.readAccessToken(token);
            Map<String, Object> tokenInfo = oAuth2AccessToken.getAdditionalInformation();
        } catch (InvalidTokenException e) {
            e.printStackTrace();
            return Mono.error(new InvalidTokenException(ResultCode.INVALID_TOKEN.getMessage()));
        }

        return null;
    }

    private boolean checkUrls(List<String> ignoreUrlList, String requestUrl) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String e : ignoreUrlList) {
            if (pathMatcher.match(e, requestUrl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
